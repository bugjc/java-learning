package com.bugjc.java.basics.spi.java;

import Test;

import java.util.ServiceLoader;

class JavaSPITest {
    @Test
    void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}