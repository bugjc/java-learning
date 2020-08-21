package com.bugjc.java.basics.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class GenericMethodTest {

    @Test
    void compare() {
        GenericMethod.Pair<Integer, String> p1 = new GenericMethod.Pair<>(1, "apple");
        GenericMethod.Pair<Integer, String> p2 = new GenericMethod.Pair<>(2, "orange");
        boolean flag = GenericMethod.compare(p1, p2);
        log.info("result:{}", flag);
    }
}