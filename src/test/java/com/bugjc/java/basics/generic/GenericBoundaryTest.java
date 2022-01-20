package com.bugjc.java.basics.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class GenericBoundaryTest {

    @Data
    @AllArgsConstructor
    static class StringArray implements GenericBoundary.Comparable<String> {

        private String value;

        @Override
        public int compareTo(String value) {
            return this.value.equals(value) ? 1 : 0;
        }
    }

    @Data
    @AllArgsConstructor
    static class IntegerArray implements GenericBoundary.Comparable<Integer> {

        private Integer value;

        @Override
        public int compareTo(Integer value) {
            return this.value > value ? 1 : 0;
        }
    }

    @Test
    void countGreaterThan() {
        StringArray[] array = new StringArray[]{new StringArray("1"), new StringArray("2"), new StringArray("3")};
        int count = GenericBoundary.countGreaterThan(array, "3");
        log.info("result : {}", count);


        IntegerArray[] array1 = new IntegerArray[]{new IntegerArray(1), new IntegerArray(2), new IntegerArray(3)};
        int count1 = GenericBoundary.countGreaterThan(array1, 1);
        log.info("result : {}", count1);
    }
}