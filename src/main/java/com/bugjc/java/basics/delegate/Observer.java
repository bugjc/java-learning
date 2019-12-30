package com.bugjc.java.basics.delegate;

/**
 * 被委托者接口
 * @author aoki
 * @date 2019/12/30
 * **/
public interface Observer {
    /**
     * 被委托者所要执行的事（方法即处理程序）
     * @param s:委托者对象
     * @param data:委托需要做的事情数据
     */
    void doEvent(Subject s,Object data);
}
