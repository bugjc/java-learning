package com.bugjc.java.basics.container.queue;

/**
 * 数组实现的循环队列
 * @author aoki
 * @date 2019/11/25
 * **/
public class CircularQueue {
    /**
     * 数组：items，数组大小：n
     */
    private String[] items;
    private int n = 0;
    /**
     * head 表示队头下标，tail 表示队尾下标
     */
    private int head = 0;
    private int tail = 0;

    /**
     * 申请一个大小为 capacity 的数组
     * @param capacity
     */
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}

