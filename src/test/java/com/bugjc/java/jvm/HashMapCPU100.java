package com.bugjc.java.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class HashMapCPU100 {
    private final HashMap<Integer,Integer> map = new HashMap<>(2);
    private final Map<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<>(2);

    public HashMapCPU100() {

        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        };
        t1.start();
        t2.start();
    }

    public HashMapCPU100(boolean type) {

        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    concurrentHashMap.put(i, i);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    concurrentHashMap.put(i, i);
                }
            }
        };
        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new HashMapCPU100();
        new HashMapCPU100(true);
        TimeUnit.SECONDS.sleep(1000);
    }
}
