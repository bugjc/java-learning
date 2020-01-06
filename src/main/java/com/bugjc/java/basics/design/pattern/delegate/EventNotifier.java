package com.bugjc.java.basics.design.pattern.delegate;

import lombok.extern.slf4j.Slf4j;

/**
 * 事件通知者
 * @author aoki
 * @date 2020/1/6
 * **/
@Slf4j
public class EventNotifier extends AbstractNotifier {

    /**
     * 被委托人姓名
     */
    private String name;
    public EventNotifier(String name){
        this.name = name;
    }


    @Override
    public void addListener(Object object, String methodName, Object... args) {
        log.info("被委托人：{} 收到委托请求", name);
        EventHandler handler = this.getEventHandler();
        handler.addEvent(object, methodName, args);
    }

    @Override
    public void notifyAllObject() {
        try{
            this.getEventHandler().notifyAllObject();
        }catch(Exception ignore){}
    }
}
