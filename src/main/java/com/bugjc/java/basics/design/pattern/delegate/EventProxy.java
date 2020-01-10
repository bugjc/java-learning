package com.bugjc.java.basics.design.pattern.delegate;

import lombok.extern.slf4j.Slf4j;

/**
 * 实际代理人
 * @author aoki
 * @date 2020/1/6
 * **/
@Slf4j
public class EventProxy extends AbstractProxy {

    /**
     * 被委托人姓名
     */
    private String name;
    public EventProxy(String name){
        this.name = name;
    }


    @Override
    public void addEvent(Object object, String methodName, Object... args) {
        log.info("被委托人：{} 收到委托请求", name);
        EventHandler handler = this.getEventHandler();
        handler.addEvent(object, methodName, args);
    }

    @Override
    public void invokeAll() {
        try{
            this.getEventHandler().invokeAll();
        }catch(Exception ignore){}
    }
}
