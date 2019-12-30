package com.bugjc.java.basics.delegate;

/**
 * 委托者接口
 * @author aoki
 * @date 2019/12/30
 * **/
public interface Subject {
    /**
     * 添加被委托对象
     * @param obj:被委托对象
     */
    void addObserver(Observer obj);
    /**
     * 移除所有对象
     */
    void removeAll();
    /**
     * 委托的事件
     * @param s:委托对象
     * @param obj:被委托对象
     * @param o：传给被委托者的数据
     */
    void event(Subject s,Observer obj,Object o);
    /**
     * 委托的很多事件
     * @param s:委托对象
     * @param obj:被委托对象
     */
    void eventAll(Subject s,Object obj);
    /**
     * 获取唯一标识name
     * @return name:委托对象的唯一标识（名字）
     */
    String getName();
}
