package com.bugjc.java.basics.di.service.factory;

import com.bugjc.java.basics.di.service.JobService;
import com.bugjc.java.basics.di.service.UserService;
import com.bugjc.java.basics.di.service.impl.JobServiceImpl;
import com.google.inject.Inject;
import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobServiceFactory implements Provider<JobService> {

    @Inject
    private UserService userService;

    @Override
    public JobService get() {
        return new JobServiceImpl(userService);
    }
}
