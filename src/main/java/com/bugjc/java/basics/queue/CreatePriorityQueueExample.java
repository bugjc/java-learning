package com.bugjc.java.basics.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * 优先队列 - 按整数大小排列
 * @author qingyang
 * @date 2018/9/17 16:08
 */
@Slf4j
public class CreatePriorityQueueExample {
    public static void main(String[] args) {
        // Create a Priority Queue
        PriorityQueue<Integer> numbers = new PriorityQueue<>();

        // Add items to a Priority Queue (ENQUEUE)
        numbers.add(750);
        numbers.add(500);
        numbers.add(900);
        numbers.add(100);

        // Remove items from the Priority Queue (DEQUEUE)
        while (!numbers.isEmpty()) {
            Integer num = numbers.remove();
            log.info("num {}",num);
        }

    }
}
