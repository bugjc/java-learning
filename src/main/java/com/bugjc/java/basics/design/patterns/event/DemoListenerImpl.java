package com.bugjc.java.basics.design.patterns.event;

public class DemoListenerImpl implements DemoListener {

    @Override
    public void handleEvent(DemoEvent dm) {
        System.out.println("自定义监听器");
        dm.say();//回调
    }

}
