package com.bugjc.java.lib.metrics;

import cn.hutool.core.util.RandomUtil;
import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 计数器使用示例
 *
 * @author aoki
 * @date 2019/12/17
 **/
@Slf4j
public class CounterExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 度量计数器
     */
    private static final Counter COUNTER = REGISTRY.counter(MetricRegistry.name(CounterExample.class, "request"));

    /**
     * 控制台打印监控数据
     */
    private static final ConsoleReporter REPORT = ConsoleReporter.forRegistry(REGISTRY)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES)
            .build();

    /**
     * 测试队列
     */
    private static Queue<String> queue = new LinkedList<String>();

    /**
     * 生产队列数据
     *
     * @param data
     */
    private static void production(String data) {
        COUNTER.inc();
        queue.offer(data);
    }

    private static void consume() {
        COUNTER.dec();
        queue.poll();
    }

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //每 1 秒将数据打印到控制台上
        REPORT.start(2, TimeUnit.SECONDS);

        //生产数据
        ThreadPoolExecutorUtil.execute(100, () -> production(RandomUtil.randomNumbers(10)));

        //消费数据
        ThreadPoolExecutorUtil.execute(20, CounterExample::consume);
    }
}
