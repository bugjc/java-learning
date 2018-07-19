package com.bugjc.java.basics.design.patterns.event;

class DemoEvent extends java.util.EventObject {

    DemoEvent(Object source) {
        super(source);//source—事件源对象—如在界面上发生的点击按钮事件中的按钮
        //所有 Event 在构造时都引用了对象 "source"，在逻辑上认为该对象是最初发生有关 Event 的对象
    }

    void say() {
        System.out.println("回调方法");
    }
}
