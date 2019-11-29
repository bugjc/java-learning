package com.bugjc.java.basics.design.pattern.filter;

/**
 * 过滤器管理
 * @author aoki
 * @date 2019/11/29
 * **/
public class FilterManager {

    private FilterChain filterChain;

    /**
     * 初始化过滤对象和过滤链
     * @param target
     */
    public FilterManager(Target target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    /**
     * 设置过滤器
     * @param filter
     */
    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    /**
     * 执行过滤
     * @param request
     */
    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
