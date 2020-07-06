package com.bugjc.java.libs.metrics;


import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Meter(测量)是一种只能自增的计数器，通常用来度量一系列事件发生的概率。
 * 它提供了平均速率，以及指数平滑平均速率，以及采样后的1分钟，5分钟，15分钟的样例。
 *
 * @author aoki
 * @date 2019/12/17
 **/
public class MeterExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 创建 TPS(每秒钟的请求数) 测量表
     */
    private final static Meter REQUESTS = REGISTRY.meter("requests");

    /**
     * 创建异常测量表
     */
    private final static Meter REQUESTS_ERR = REGISTRY.meter("err_requests");

    /**
     * 处理请求的方法
     */
    private static void handleRequest() {
        try {
            REQUESTS.mark();
            randomSleep();
            int x = 10 / ThreadLocalRandom.current().nextInt(6);
        } catch (Exception ignore) {
            REQUESTS_ERR.mark();
        }
    }

    /**
     * 模拟处理请求耗时
     */
    private static void randomSleep() {
        try {
            //随机休眠时间
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) {
        //数据生成报告
        ConsoleReporter report = ConsoleReporter.forRegistry(REGISTRY)
                .convertRatesTo(TimeUnit.MINUTES)
                .convertDurationsTo(TimeUnit.MINUTES)
                .build();
        //每10秒将数据打印到控制台上
        report.start(10, TimeUnit.SECONDS);

        //模拟一直调用请求
        for (; ; ) {
            //发送请求
            handleRequest();
            randomSleep();
        }
    }
}
