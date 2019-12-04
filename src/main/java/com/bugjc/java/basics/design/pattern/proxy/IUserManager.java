package com.bugjc.java.basics.design.pattern.proxy;

/**
 * 用户管理接口(真实主题和代理主题的共同接口，这样在任何可以使用真实主题的地方都可以使用代理主题代理。)
 * --被代理接口定义
 * @author aoki
 * @date 2019/11/29
 * **/
public interface IUserManager {

    /**
     * 添加用户
     * @param id
     * @param name
     */
    public String addUser(String id, String name);
}
