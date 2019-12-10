package com.bugjc.java.basics.di.service.impl;

import com.bugjc.java.basics.di.service.JobService;
import com.bugjc.java.basics.di.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务实现
 * @author aoki
 * @date 2019/12/10
 * **/
@Slf4j
public class JobServiceImpl implements JobService {


    private UserService userService;

    public JobServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void create(String jobId, String name) {
        if (!userService.check()){
            return;
        }

        log.info("创建任务！任务ID={},任务名={}", jobId, name);
    }

    @Override
    public void log() {
        log.info("任务检查完毕。");
    }
}
