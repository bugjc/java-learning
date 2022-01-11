package com.bugjc.java.basics.design.pattern.template.method;

/**
 * 模板方法模式
 * @author aoki
 * @date 2022/1/6
 * **/
public class Test {
    public static void main(String[] args) {
        StealingTemplate template = new StealingShopKeeper();
        template.steal();
    }
}
