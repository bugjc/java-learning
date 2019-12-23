package com.bugjc.java.lib.junitperf;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;

class HtmlReporterTest {

    @JunitPerfConfig(duration = 1000, reporter = {HtmlReporter.class})
    void helloTest() throws InterruptedException {
        Thread.sleep(1);
        System.out.println("Hello Junit5");
    }
}
