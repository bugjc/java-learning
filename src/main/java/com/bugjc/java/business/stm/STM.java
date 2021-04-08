package com.bugjc.java.business.stm;


/**
 * STM
 *
 * @author aoki
 * @date 2021/4/8
 **/
public final class STM {
    public static final Object commitLock = new Object();

    private STM() {
    }

    public static void atomic(TxnRunnable action) {
        boolean committed = false;
        //如果没有提交成功，则一直重试
        while (!committed) {
            //创建新的事务
            STMTxn txn = new STMTxn();
            //执行业务逻辑
            action.run(txn);
            //提交事务
            committed = txn.commit();
        }
    }
}