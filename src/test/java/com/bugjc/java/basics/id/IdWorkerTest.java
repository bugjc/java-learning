package com.bugjc.java.basics.id;

import org.junit.jupiter.api.Test;

class IdWorkerTest {

    @Test
    void getNextId() {
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            System.out.println(idWorker.getNextId());
            System.out.println(idWorker.getTimeSortId());
        }
    }
}