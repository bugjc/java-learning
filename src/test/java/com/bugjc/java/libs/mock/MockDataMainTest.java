package com.bugjc.java.libs.mock;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

@Slf4j
class MockDataMainTest {

    @Test
    void initialization() {
        log.info("{}", JSON.toJSONString(new MockDataMain().initialization(MockDataParent.class)));
    }


    @Data
    public static class MockDataParent {
        //基本类型
        private int integerNum;
        //基本包装类型
        private Double doubleBoxing;
        //基本类型数组
        private byte[] byteNumArray;
        //基本包装类型数组
        private Integer[] integerBoxingArray;
        private Date date;
        //LIST
        private List<MockDataChild> byteBoxingList;
    }

    @Data
    public static class MockDataChild {
        private String string;
    }
}