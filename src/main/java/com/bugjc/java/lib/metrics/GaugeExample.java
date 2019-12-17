package com.bugjc.java.lib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Gauge是一个最简单的计量，一般用来统计瞬时状态的数据信息.例：某一时刻的集合中的大小
 *
 * @author aoki
 * @date 2019/12/17
 **/
public class GaugeExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 控制台打印监控数据
     */
    private static final ConsoleReporter REPORT = ConsoleReporter.forRegistry(REGISTRY)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES)
            .build();

    /**
     * 定义队列
     */
    private static Queue<Integer> queue = new LinkedBlockingQueue<>();

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        REPORT.start(3, TimeUnit.SECONDS);
        Gauge<Integer> gauge = () -> queue.size();
        //将定义过的gauge 注册到注册中心
        REGISTRY.register(MetricRegistry.name(GaugeExample.class, "queue-size"), gauge);

        //模拟添加队列数据
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

}
