package com.bugjc.java.basics.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤链
 * @author aoki
 * @date 2019/11/29
 * **/
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private Target target;

    /**
     * 添加一个过滤器
     * @param filter
     */
    public void addFilter(Filter filter){
        filters.add(filter);
    }

    /**
     * 执行过滤器
     * @param request
     */
    public void execute(String request){
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    /**
     * 设置目标执行对象
     * @param target
     */
    public void setTarget(Target target){
        this.target = target;
    }
}
