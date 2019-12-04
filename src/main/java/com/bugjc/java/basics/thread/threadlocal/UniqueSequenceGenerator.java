package com.bugjc.java.basics.thread.threadlocal;

/**
 * 获取当前线程唯一的序列号
 * @author aoki
 * @date 2019/11/25
 * **/
public class UniqueSequenceGenerator implements Id {

    /**
     * 线程局部整型变量
     */
    private static final ThreadLocal <Integer> UNIQUE_NUM = ThreadLocal.withInitial(() -> 0);

    /**
     * 变量值
     * @return int
     */
    @Override
    public int getUniqueId() {
        UNIQUE_NUM.set(UNIQUE_NUM.get() + 1);
        return UNIQUE_NUM.get();
    }
}
