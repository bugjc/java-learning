package com.bugjc.java.business.stm;

/**
 * 事务接口
 *
 * @author aoki
 * @date 2021/4/8
 **/
public interface Txn {
    <T> T get(TxnRef<T> ref);

    <T> void set(TxnRef<T> ref, T value);
}
