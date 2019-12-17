package com.bugjc.java.lib.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 度量值可以通过 HTTP，SLF4J，JMX，CSV ，Ganglia，Graphite 打印（展示）
 *
 * @author aoki
 * @date 2019/12/17
 **/
public class ReportingExample {

    /**
     * 度量注册中心
     */
    private static final MetricRegistry REGISTRY = new MetricRegistry();

    /**
     * 度量计数器
     */
    private static final Counter COUNTER = REGISTRY.counter(MetricRegistry.name(CounterExample.class, "request"));

    /**
     * 监控数据以csv文件格式存储到 ~/projects/data/ 文件夹里面。
     */
    private static final CsvReporter CSV_REPORTER = CsvReporter.forRegistry(REGISTRY)
            .formatFor(Locale.US)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build(new File("d://data/"));


    private static final Slf4jReporter SLF4J_REPORTER = Slf4jReporter.forRegistry(REGISTRY)
            .outputTo(LoggerFactory.getLogger("com.example.metrics"))
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        CSV_REPORTER.start(1, TimeUnit.SECONDS);
        SLF4J_REPORTER.start(2, TimeUnit.SECONDS);

        for (int i = 0; i < 1000; i++) {
            COUNTER.inc();
        }

        Thread.sleep(10000);
    }
}
