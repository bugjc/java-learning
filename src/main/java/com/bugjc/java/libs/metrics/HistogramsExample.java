package com.bugjc.java.libs.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Histogram统计数据的分布情况。比如最小值，最大值，中间值，还有中位数，75百分位, 90百分位, 95百分位, 98百分位, 99百分位, 和 99.9百分位的值(percentiles)。
 * @author aoki
 * @date 2019/12/17
 * **/
public class HistogramsExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 直方图
     */
    private static final Histogram HISTOGRAM = REGISTRY.histogram(MetricRegistry.name(HistogramsExample.class,"histogram"));

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
        long startTime = System.currentTimeMillis();
        try {
            randomSleep();
        } catch (Exception ignore){
        } finally {
            long time = System.currentTimeMillis() - startTime;
            HISTOGRAM.update(time);
        }
    }

    /**
     * 模拟处理请求耗时
     */
    private static void randomSleep(){
        try {
            //随机休眠时间
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException ignore) {}

    }

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        REPORT.start(5, TimeUnit.SECONDS);

        //模拟请求
        for (;;){
            handleRequest();
            randomSleep();
        }
    }
}
