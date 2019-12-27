package com.bugjc.java.basics.spi.java.impl;

import com.bugjc.java.basics.spi.java.Robot;

/**
 * 大黄蜂机器人
 * @author aoki
 * @date 2019/12/27
 * **/
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
