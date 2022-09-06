package com.bugjc.java;

import cn.hutool.core.util.RandomUtil;

public class Main {

    private final static String ORDER_STATUS = "1";

    private int m;

    public int inc() {
        if (RandomUtil.randomBoolean()){
            throw new NullPointerException();
        }
        return m + 1;
    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.inc();
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }

    }
}
