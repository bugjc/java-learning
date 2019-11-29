package com.bugjc.java.basics.design.pattern.filter;

/**
 * 使用客户端演示拦截过滤器设计模式
 * @author aoki
 * @date 2019/11/29
 * **/
public class InterceptingFilterDemo {

    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }

}
