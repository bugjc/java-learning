package com.bugjc.java.basics.container.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * 优先队列 - 按字符串首字母排列
 * @author qingyang
 * @date 2018/9/17 16:14
 */
@Slf4j
public class CreatePriorityQueueStringExample {

    public static void main(String[] args) {
        // Create a Priority Queue
        PriorityQueue<String> namePriorityQueue = new PriorityQueue<>();

        // Add items to a Priority Queue (ENQUEUE)
        namePriorityQueue.add("Lisa");
        namePriorityQueue.add("Robert");
        namePriorityQueue.add("John");
        namePriorityQueue.add("Chris");
        namePriorityQueue.add("Angelina");
        namePriorityQueue.add("Joe");

        // Remove items from the Priority Queue (DEQUEUE)
        while (!namePriorityQueue.isEmpty()) {
            String name = namePriorityQueue.remove();
            log.info("name {}",name);
        }

    }
}
