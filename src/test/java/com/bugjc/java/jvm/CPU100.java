package com.bugjc.java.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.LockSupport;

public class CPU100 {
    static Thread threadA;
    static Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {
        test2();
    }
    public static void  test2() {
        boolean isTrue = true;
        while (isTrue) {
            String str = new String("afdsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssfasdfsadfsdafasdfasdfsdafdsafsadfsdaf");
            str += UUID.randomUUID().toString();
            map.put(UUID.randomUUID().toString(),str);
        }
    }
    public static void test1(){
        new Thread(()->{
            LockSupport.park();
        }).start();
    }
}
