package com.bugjc.java.business.stm;

@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}

