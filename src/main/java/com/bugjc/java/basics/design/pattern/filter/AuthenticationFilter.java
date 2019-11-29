package com.bugjc.java.basics.design.pattern.filter;

/**
 * 授权过滤
 * @author aoki
 * @date 2019/11/29
 * **/
public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
