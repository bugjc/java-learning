package com.bugjc.java.basics.lock;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * MCSSpinLock（排队自旋锁）
 * @author aoki
 * @date 2022/1/7
 * **/
public class MCSSpinLock {

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = true;
    }

    private static final ThreadLocal<MCSNode> NODE = new ThreadLocal<>();

    /**
     * 队列
     */
    private volatile MCSNode queue;


    private static final AtomicReferenceFieldUpdater<MCSSpinLock, MCSNode> UPDATE = AtomicReferenceFieldUpdater.newUpdater(MCSSpinLock.class, MCSNode.class,"queue");

    public void lock(){
        // 创建节点并保存到ThreadLocal中
        MCSNode currentNode = new MCSNode();
        NODE.set(currentNode);

        // 将 queue 设置为当前节点，并且返回之前的节点
        MCSNode preNode = UPDATE.getAndSet(this, currentNode);
        if (preNode != null) {
            // 如果之前节点不为null，表示锁已经被其他线程持有
            preNode.next = currentNode;
            // 循环判断，直到当前节点的锁标志位为false
            while (currentNode.isLocked) {
            }
        }
    }

    public void unlock() {
        MCSNode currentNode = NODE.get();
        // next 为 null 表示没有正在等待获取锁的线程
        if (currentNode.next == null) {
            // 更新状态并设置queue为null
            if (UPDATE.compareAndSet(this, currentNode, null)) {
                // 如果成功了，表示queue==currentNode,即当前节点后面没有节点了
            } else {
                // 如果不成功，表示queue!=currentNode,即当前节点后面多了一个节点，表示有线程在等待
                // 如果当前节点的后续节点为null，则需要等待其不为null（参考加锁方法）
                while (currentNode.next == null) {
                }
            }
        } else {
            // 如果不为 null，表示有线程在等待获取锁，此时将等待线程对应的节点锁状态更新为 false，同时将当前线程的后继节点设为 null
            currentNode.next.isLocked = false;
            currentNode.next = null;
        }
        NODE.remove();
    }

    public static void main(String[] args) {
        MCSSpinLock mcsSpinLock = new MCSSpinLock();
        ThreadPoolExecutorUtil.execute(2, new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                mcsSpinLock.lock();
                try {
                    System.out.println("已获取到锁");
                    System.out.println("执行业务");
                    Thread.sleep(100);
                }finally {
                    System.out.println("释放锁");
                    mcsSpinLock.unlock();
                }


            }
        });
    }

}
