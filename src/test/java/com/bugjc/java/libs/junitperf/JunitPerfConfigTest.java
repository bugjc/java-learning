package com.bugjc.java.libs.junitperf;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;

class JunitPerfConfigTest {
    /**
     * 2个线程运行。
     * 准备时间：1000ms
     * 运行时间: 2000ms
     * @throws InterruptedException if any
     */
    @JunitPerfConfig(threads = 2, warmUp = 1000, duration = 2000)
    void junitPerfConfigTest() throws InterruptedException {
        System.out.println("junitPerfConfigTest");
        Thread.sleep(200);
    }
}
