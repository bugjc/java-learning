package com.bugjc.java.basics.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GenericWildcardTest {

    @Test
    void getData() {
        GenericWildcard.Field<Number> number = new GenericWildcard.Field<>(100);
        log.info("result : {}", GenericWildcard.getData(number));
        GenericWildcard.Field<Integer> integer = new GenericWildcard.Field<>(200);
        log.info("result : {}", GenericWildcard.getData(integer));
        GenericWildcard.Field<String> string = new GenericWildcard.Field<>("hello");
        log.info("result : {}", GenericWildcard.getData(string));
    }
}