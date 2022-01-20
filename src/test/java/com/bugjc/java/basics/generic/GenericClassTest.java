package com.bugjc.java.basics.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class GenericClassTest {

    @Test
    void test() {
        GenericClass<Integer> genericClass = new GenericClass<>();
        genericClass.set(100);
        log.info("get integer value : {}", genericClass.get());

        GenericClass<String> genericClass1 = new GenericClass<>();
        genericClass1.set("hello");
        log.info("get string value : {}", genericClass1.get());
    }
}