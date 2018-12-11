package com.bugjc.java.queue;

import com.bugjc.java.basics.queue.CircularQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CircularQueueTest {


    @Test
    public void testCircularQueue(){
        CircularQueue circularQueue = new CircularQueue(3);
        log.info("入队状态：{}",circularQueue.enqueue("1"));
        log.info("入队状态：{}",circularQueue.enqueue("2"));
        //最后空一位
        log.info("入队状态：{}",circularQueue.enqueue("3"));
        log.info("出队值：{}",circularQueue.dequeue());
        log.info("出队值：{}",circularQueue.dequeue());
        log.info("出队值：{}",circularQueue.dequeue());
        log.info("入队状态：{}",circularQueue.enqueue("4"));
        log.info("入队状态：{}",circularQueue.enqueue("5"));
    }

}
