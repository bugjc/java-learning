package com.bugjc.java.libs.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Timer其实是 Histogram 和 Meter 的结合， histogram 某部分代码/调用的耗时， meter统计TPS。
 *
 * @author aoki
 * @date 2019/12/17
 **/
public class TimerExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 直方图
     */
    private static final Timer TIMER = REGISTRY.timer("requests");

    /**
     * 控制台打印监控数据
     */
    private static final ConsoleReporter REPORT = ConsoleReporter.forRegistry(REGISTRY)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES)
            .build();

    /**
     * 处理请求的方法
     */
    private static void handleRequest() {
        Timer.Context time = TIMER.time();
        ;
        try {
            randomSleep();
        } catch (Exception ignore) {
        } finally {
            time.stop();
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
    public static void main(String[] args) throws InterruptedException {
        REPORT.start(5, TimeUnit.SECONDS);

        //模拟请求
        for (; ; ) {
            handleRequest();
            randomSleep();
        }
    }
}
