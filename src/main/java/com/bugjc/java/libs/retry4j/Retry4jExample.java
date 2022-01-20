package com.bugjc.java.libs.retry4j;

import cn.hutool.core.util.RandomUtil;
import com.evanlennick.retry4j.CallExecutor;
import com.evanlennick.retry4j.CallExecutorBuilder;
import com.evanlennick.retry4j.Status;
import com.evanlennick.retry4j.config.RetryConfig;
import com.evanlennick.retry4j.config.RetryConfigBuilder;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

public class Retry4jExample<T> {

    private final CallExecutor callExecutor;

    public Retry4jExample(){
        RetryConfigBuilder retryConfigBuilder = new RetryConfigBuilder(true);
        RetryConfig retryConfig = retryConfigBuilder
                .retryOnCausedBy()
                .retryOnSpecificExceptions(CustomTestException.class)
                .withMaxNumberOfTries(3)
                .withDelayBetweenTries(0, ChronoUnit.SECONDS)
                .withFixedBackoff()
                .build();
        this.callExecutor = new CallExecutorBuilder().config(retryConfig).build();
    }


    /**
     * 获取用户信息（test）
     * @return
     */
    public Status getUserInfo(){

        Callable<T> callable = () -> {
            if (RandomUtil.randomBoolean()){
                //执行业务逻辑
                throw new Exception(new CustomTestException("message", 3));
            }

            return (T) new UserInfo("aoki",18);
        };

        return this.callExecutor.execute(callable);
    }
}
