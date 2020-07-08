package com.bugjc.java.basics.container.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ArrayQueueTest {


    @Test
    void testArrayQueue(){
        ArrayQueue arrayQueue = new ArrayQueue(3);
        log.info("入队状态：{}",arrayQueue.enqueue("1"));
        log.info("入队状态：{}",arrayQueue.enqueue("2"));
        log.info("入队状态：{}",arrayQueue.enqueue("3"));
        log.info("出队值：{}",arrayQueue.dequeue());
        log.info("入队操作，当tail=n 时不受理，入队状态：{}",arrayQueue.enqueue("4"));
        log.info("入队操作，当tail=n 执行数据搬迁操作，入队状态：{}",arrayQueue.enqueue2("4"));
    }
}
