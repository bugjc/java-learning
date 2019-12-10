package com.bugjc.java.basics.di.config;

import com.bugjc.java.basics.di.service.JobService;
import com.bugjc.java.basics.di.service.UserService;
import com.bugjc.java.basics.di.service.factory.JobServiceFactory;
import com.bugjc.java.basics.di.service.impl.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * 定义依赖绑定的基本单元
 * @author aoki
 * @date 2019/12/9
 * **/
public class JobModule extends AbstractModule {

    @Override
    protected void configure() {
        // 表明：当需要 HttpService 这个变量时，通过 HttpServiceFactory 创建单例的 HttpServiceImpl 实例作为依赖。
        this.bind(JobService.class).toProvider(JobServiceFactory.class);
        this.bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
    }
}
