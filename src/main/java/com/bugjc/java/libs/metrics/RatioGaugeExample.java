package com.bugjc.java.libs.metrics;

/**
 * 度量事件成功率的计算。  例：度量缓存命中率、接口调用率等等。
 *
 * @author aoki
 * @date 2019/12/17
 **/

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RatioGaugeExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 统计总数
     */
    private static final Meter TOTAL_METER = REGISTRY.meter("totalCount");

    /**
     * 统计成功总数
     */
    private static final Meter SUCC_METER = REGISTRY.meter("succCount");

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
        try {
            TOTAL_METER.mark();
            randomSleep();
            int x = 10 / ThreadLocalRandom.current().nextInt(6);
            SUCC_METER.mark();
        } catch (Exception ignore) {
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
     * @param args
     */
    public static void main(String[] args) {
        //每5秒发送一次到控制台
        REPORT.start(5, TimeUnit.SECONDS);

        //第一个参数：分子 第二个参数：分母
        REGISTRY.gauge("succ-ratio", () -> new RatioGauge() {
            @Override
            protected Ratio getRatio() {
                return Ratio.of(SUCC_METER.getCount(), TOTAL_METER.getCount());    //第一个参数：分子 第二个参数：分母
            }
        });

        //调用
        for (; ; ) {
            handleRequest();
            randomSleep();
        }

    }
}
