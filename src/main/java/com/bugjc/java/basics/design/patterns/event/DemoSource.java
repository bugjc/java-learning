package com.bugjc.java.basics.design.patterns.event;

import java.util.Enumeration;
import java.util.Vector;

class DemoSource {

    private Vector<DemoListener> repository = new Vector<>();//监听自己的监听器队列

    DemoSource(){}

    void addDemoListener(DemoListener dl) {
        repository.addElement(dl);
    }

    void notifyDemoEvent() {//通知所有的监听器
        Enumeration<DemoListener> enums = repository.elements();
        while(enums.hasMoreElements()) {
            DemoListener dl = enums.nextElement();
            dl.handleEvent(new DemoEvent(this));
        }
    }

}
