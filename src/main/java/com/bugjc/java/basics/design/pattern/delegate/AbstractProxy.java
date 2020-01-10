package com.bugjc.java.basics.design.pattern.delegate;

/**
 * 被委托者（代理人）
 * @author aoki
 * @date 2020/1/6
 * **/
public abstract class AbstractProxy {
    private EventHandler eventHandler = new EventHandler();

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    /**
     * 添加事件
     *
     * @param object     要执行方法的对象
     * @param methodName 执行方法 的方法名
     * @param args       执行方法的参数
     */
    public abstract void addEvent(Object object, String methodName, Object... args);

    /**
     * 执行事件
     */
    public abstract void invokeAll();

}
