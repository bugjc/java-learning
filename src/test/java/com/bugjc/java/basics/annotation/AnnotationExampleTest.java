package com.bugjc.java.basics.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class AnnotationExampleTest {

    @Test
    void getAnnotationValue() {
        log.info("@Id 注解值: {}", AnnotationExample.getAnnotationValue());
    }

    @Test
    void updAnnotationValue() {
        log.info("当前 @Id 注解值: {}", AnnotationExample.getAnnotationValue());
        AnnotationExample.updAnnotationValue("1002");
        log.info("修改 @Id 注解值为 1002,再一次查询 @Id 注解值: {}", AnnotationExample.getAnnotationValue());
    }
}