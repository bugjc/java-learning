package com.bugjc.java.basics.design.pattern.delegate;

import java.util.ArrayList;
import java.util.List;
/**
 * 事件处理人
 * @author aoki
 * @date 2020/1/6
 * **/
public class EventHandler {

    private List<Event> objects;

    public EventHandler() {
        objects = new ArrayList<Event>();
    }

    /**
     * 添加某个对象要执行的事件以及需要的参数
     *
     * @param object
     * @param methodName
     * @param args
     */
    public void addEvent(Object object, String methodName, Object... args) {
        objects.add(new Event(object, methodName, args));
    }

    /**
     * 通知所有的对象执行指定的事件
     *
     * @throws Exception
     */
    public void notify(Event e) throws Exception {
        e.invoke();
    }

    /**
     * 通知所有的对象执行指定的事件
     *
     * @throws Exception
     */
    public void notifyAllObject() throws Exception {
        for (Event e : objects) {
            e.invoke();
        }
    }
}
