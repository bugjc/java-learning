package com.bugjc.java.basics;

import org.junit.jupiter.api.Test;

public class Decimal {

    @Test
    void test(){
        String hex = "0xb8000 ".trim();
        Long x = Long.parseLong(hex.substring(2),16);
        System.out.println(x);
    }
}
