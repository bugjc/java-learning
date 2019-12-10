package com.bugjc.java.basics.di.service;

/**
 * 任务接口
 * @author aoki
 * @date 2019/12/10
 * **/
public interface JobService {

    /**
     * 创建任务
     * @param jobId
     * @param name
     */
    void create(String jobId, String name);

    /**
     * 打印日志
     */
    void log();
}
