package com.bugjc.java.basics.spi.java;


import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

class JavaSPITest {
    @Test
    void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        serviceLoader.forEach((Robot) -> {
            System.out.println(this.getClass().getName());
        });
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}