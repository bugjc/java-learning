package com.bugjc.java.basics.design.pattern.filter;

/**
 * 目标类
 * @author aoki
 * @date 2019/11/29
 * **/
public class Target {

    /**
     * 执行方法
     * @param request
     */
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }

}
