package com.bugjc.java.basics.design.pattern.filter;

/**
 * 客户端程序
 * @author aoki
 * @date 2019/11/29
 * **/
public class Client {

    private FilterManager filterManager;

    /**
     * 设置过滤器管理对象
     * @param filterManager
     */
    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    /**
     * 发送请求，使用过滤器管理对象执行
     * @param request
     */
    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }

}
