package com.bugjc.java.basics.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class GeneratorIdTest {

    @Test
    void getUniqueId() {
        Id id = new UniqueSequenceGenerator();
        // 为每个线程生成一个序列号
        TaskThread t1 = new TaskThread<>("custom-thread-1", id);
        TaskThread t2 = new TaskThread<>("custom-thread-2", id);
        TaskThread t3 = new TaskThread<>("custom-thread-3", id);
        t1.start();
        t2.start();
        t3.start();

        id = new UniqueThreadIdGenerator();
        // 为每个线程生成一个唯一的局部标识
        t1 = new TaskThread<>("custom-thread-1", id);
        t2 = new TaskThread<>("custom-thread-2", id);
        t3 = new TaskThread<>("custom-thread-3", id);
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 异步获取唯一ID
     * @author aoki
     * @date 2019/11/25
     * **/
    @Slf4j
    public static class TaskThread<T> extends Thread {

        private T t;

        TaskThread(String threadName, T t) {
            this.setName(threadName);
            this.t = t;
        }

        @Override
        public void run() {
            try {
                Class[] argsClass = new Class[0];
                Method method = t.getClass().getMethod("getUniqueId",argsClass);
                Object value = method.invoke(t);
                log.info("thread[" + Thread.currentThread().getName() + "] --> uniqueId["+value+ "]");

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.info("请确认实现了 getUniqueId() 接口");
            }
        }

    }
}