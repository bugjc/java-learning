package com.bugjc.java.basics.delegate;

/**
 * 测试入口
 * @author aoki
 * @date 2019/12/30
 * **/
public class DelegationExample {

    public static void main(String[] args) {
        Delegation d = new Delegation("张三");
        Observed a = new Observed("李四");
        d.addObserver(a);
        d.event(d, a, "买早餐");
        Observed b = new Observed("王五");
        d.addObserver(b);
        d.eventAll(d, "要美女的联系方式");
    }
}
