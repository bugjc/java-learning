package com.bugjc.java.basics;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qingyang
 * @date 2018/11/6 22:34
 */
@Slf4j
public class DemoExample {


    private int f(int n){
        if (n == 1){
            return 1;
        }
        return f(n - 1) + 1;
    }

    public static void main(String[] args) {
        int n = new DemoExample().f(9);
        log.info(n + "");

    }
}
