package com.bugjc.java.basics.classs;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MutableExampleTest {

    @Test
    void test() {

        Date birthday = new Date();
        MutableExample mutableExample = new MutableExample("小明", 21, birthday);
        System.err.println("小明的生日为 ： " + mutableExample.getBirthday());

        //我设置下我的生日，你会发现我的生日居然可以改变
        birthday.setTime(System.currentTimeMillis() + 1000000000);
        System.err.println("小明的生日为 ： " + mutableExample.getBirthday());

    }
}