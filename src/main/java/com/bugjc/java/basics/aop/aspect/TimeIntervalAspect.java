package com.bugjc.java.basics.aop.aspect;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 打印调用方法耗时切面
 * @author aoki
 * @date 2019/12/3
 * **/
@Slf4j
public class TimeIntervalAspect implements Aspect, Serializable {

    private TimeInterval interval = new TimeInterval();

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        interval.start();
        System.out.println(JSON.toJSONString(args));
        return true;
    }

    @Override
    public void afterReturning(Object target, Method method, Object[] args, Object returnVal) {

        returnVal = RandomUtil.randomNumber();

        log.info("Method [{}.{}] execute spend [{}]ms return value [{}]",
                target.getClass().getName(),
                method.getName(),
                interval.intervalMs(),
                returnVal);
    }

    @Override
    public void afterThrowing(Object target, Method method, Object[] args, Throwable e) {

    }

    @Override
    public void after() {
    }
}
