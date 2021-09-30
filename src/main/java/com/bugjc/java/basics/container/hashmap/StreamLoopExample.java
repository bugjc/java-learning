package com.bugjc.java.basics.container.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aoki
 * @date 2021/9/28
 * **/
public class StreamLoopExample {

    public static void main(String[] args) {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }
}
