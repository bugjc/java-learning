package com.bugjc.java.basics.di.service.impl;

import com.bugjc.java.basics.di.service.JobService;
import com.bugjc.java.basics.di.service.UserService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户实现
 * @author aoki
 * @date 2019/12/10
 * **/
@Slf4j
public class UserServiceImpl implements UserService {

    @Inject
    private JobService jobService;

    @Override
    public boolean check() {
        log.info("检查用户是否存在");
        jobService.log();
        return true;
    }
}
