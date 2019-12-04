package com.bugjc.java.basics.aop;


import com.bugjc.java.basics.aop.aspect.Aspect;
import com.bugjc.java.basics.aop.interceptor.CgLibProxyInterceptor;
import com.bugjc.java.basics.aop.interceptor.JdkProxyInterceptor;

public class ProxyUtil {

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public static Object createProxy(Object target){
        try {
            return new CgLibProxyInterceptor().newProxy(target);
        } catch (NoClassDefFoundError e) {
            // ignore
        }
        return new JdkProxyInterceptor().newProxy(target);
    }


    /**
     * 创建代理对象
     * @param target
     * @param aspect
     * @return
     */
    public static Object createProxy(Object target, Aspect aspect){
        try {
            //return new CgLibProxyInterceptor().newProxy(target, aspect);
        } catch (NoClassDefFoundError e) {
            // ignore
        }
        return new JdkProxyInterceptor().newProxy(target, aspect);
    }


}
