package com.bugjc.java.basics.container;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import BeforeEach;
import Test;

@Slf4j
class CharacterExampleTest {

    private String[] srcArray = new String[]{"A", "D", "a", "B", "c", "b", "2", "8"};

    @BeforeEach
    void setUp() {
        log.info("Character 测试数组数据：{}", JSON.toJSONString(srcArray));
    }

    @Test
    void isLowerCase() {
        for (String s : srcArray) {
            if (CharacterExample.isLowerCase(s.charAt(0))) {
                //小写桶
                log.info("小写桶：{}", s);
            }
        }
    }

    @Test
    void isUpperCase() {
        for (String s : srcArray) {
            if (CharacterExample.isUpperCase(s.charAt(0))) {
                //大写桶
                log.info("大写桶：{}", s);
            }
        }
    }

    @Test
    void isDigit() {
        for (String s : srcArray) {
            if (CharacterExample.isDigit(s.charAt(0))) {
                //数字桶
                log.info("数字桶：{}", s);
            }
        }
    }
}