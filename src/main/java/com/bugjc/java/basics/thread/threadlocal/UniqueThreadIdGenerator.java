package com.bugjc.java.basics.thread.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 获取当前应用内唯一的序列号
 * @author aoki
 * @date 2019/11/25
 * **/
public class UniqueThreadIdGenerator implements Id{

    /**
     * 原子整型
     */
    private static final AtomicInteger UNIQUE_ID = new AtomicInteger(0);

    /**
     * 变量值
     * @return int
     */
    @Override
    public int getUniqueId() {
        return UNIQUE_ID.incrementAndGet();
    }

}
