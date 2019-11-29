package com.bugjc.java.basics.design.pattern.filter;

/**
 * 过滤器接口
 * @author aoki
 * @date 2019/11/29
 * **/
public interface Filter {

    /**
     * 执行方法
     * @param request
     */
    void execute(String request);
}
