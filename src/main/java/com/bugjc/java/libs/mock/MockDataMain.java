package com.bugjc.java.libs.mock;

import com.github.jsonzou.jmockdata.JMockData;

/**
 * 随机模拟对象数据
 *
 * @author aoki
 * @date 2020/8/6
 **/
public class MockDataMain {

    /**
     * 初始化对象
     *
     * @param t
     * @return
     */
    public <T> T initialization(Class<T> t) {
        //调用模拟数据的方法模拟Java对象
        return JMockData.mock(t);
    }
}
