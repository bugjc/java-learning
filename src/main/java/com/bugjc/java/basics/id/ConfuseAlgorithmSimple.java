package com.bugjc.java.basics.id;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单的混淆算法
 */
@Slf4j
public class ConfuseAlgorithmSimple {

    /**
     * 混淆方法
     * @param id
     * @return
     */
    public long confuse(long id) {
        return id^Integer.MAX_VALUE-1024;
    }

    /**
     * 恢复方法
     * @param confuseId
     * @return
     */
    public long restore(long confuseId) {
        return (confuseId+1024)^Integer.MAX_VALUE;
    }

}
