package com.bugjc.java.basics.id;

import Test;

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