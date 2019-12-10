package com.bugjc.java.basics.di;

import com.bugjc.java.basics.di.config.JobModule;
import com.bugjc.java.basics.di.service.JobService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobAppMain {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new JobModule());
        JobService jobService = injector.getInstance(JobService.class);
        log.info(String.valueOf(jobService.hashCode()));
        JobService jobService2 = injector.getInstance(JobService.class);
        log.info(String.valueOf(jobService.hashCode()));
        if (jobService.equals(jobService2)){
            log.info("单例BEAN");
        }
        jobService.create("1001","清空缓存任务");

    }
}
