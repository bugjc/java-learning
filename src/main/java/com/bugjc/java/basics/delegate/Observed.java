package com.bugjc.java.basics.delegate;

/**
 * 被委托的对象的类
 * @author aoki
 * @date 2019/12/30
 * **/
public class Observed implements Observer {
    
    /**
     * 被委托的对象的唯一标识
     */
    private String name;

    Observed(String name) {
        this.name = name;
    }

    @Override
    public void doEvent(Subject s, Object data) {
        System.out.println(s.getName() + "你好，" + "我是" + name + "，你让我" + data + "的事已经做完了！");
    }
}
