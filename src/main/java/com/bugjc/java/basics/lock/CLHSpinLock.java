package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CLHSpinLock（排队自旋锁）
 *
 * @author aoki
 * @date 2022/1/7
 **/
public class CLHSpinLock {
    public static class QNode {
        volatile boolean locked;
    }

    /**
     * 尾巴，是所有线程共有的一个。所有线程进来后，把自己设置为tail
     */
    private final AtomicReference<QNode> tail;

    /**
     * 前驱节点，每个线程独有一个。
     * 前驱节点
     */
    private final ThreadLocal<QNode> preNodeThreadLocal;

    /**
     * 当前节点，表示自己，每个线程独有一个。
     */
    private final ThreadLocal<QNode> currentNodeThreadLocal;

    public CLHSpinLock() {
        this.tail = new AtomicReference<QNode>(new QNode());
        this.currentNodeThreadLocal = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };
        this.preNodeThreadLocal = new ThreadLocal<QNode>();
    }

    public void lock() {
        // 获取当前线程的代表节点
        QNode node = currentNodeThreadLocal.get();
        // 将自己的状态设置为true表示获取锁。
        node.locked = true;
        // 将自己放在队列的尾巴，并且返回以前的值。第一次进将获取构造函数中的那个 new QNode
        QNode preNode = tail.getAndSet(node);
        // 把旧的节点放入前驱节点。
        preNodeThreadLocal.set(preNode);
        // 判断前驱节点的状态，然后走掉。
        while (preNode.locked) {
        }
    }

    public void unlock() {
        // unlock. 获取自己的 node。把自己的 locked 设置为 false。
        QNode node = currentNodeThreadLocal.get();
        node.locked = false;
        preNodeThreadLocal.remove();
        currentNodeThreadLocal.remove();
    }

    public static void main(String[] args) {
        CLHSpinLock clhSpinLock = new CLHSpinLock();
        ThreadPoolExecutorUtil.execute(1, new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                clhSpinLock.lock();
                try {
                    System.out.println("已获取到锁");
                    System.out.println("执行业务");
                    Thread.sleep(100);
                }finally {
                    System.out.println("释放锁");
                    clhSpinLock.unlock();
                }
            }
        });
    }
}
