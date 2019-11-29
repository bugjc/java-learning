package com.bugjc.java.basics.design.pattern.filter;

/**
 * debug 过滤
 * @author aoki
 * @date 2019/11/29
 * **/
public class DebugFilter implements Filter{
    @Override
    public void execute(String request) {
        System.out.println("request log: " + request);
    }
}
