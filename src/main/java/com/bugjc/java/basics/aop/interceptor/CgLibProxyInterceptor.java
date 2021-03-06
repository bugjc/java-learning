package com.bugjc.java.basics.aop.interceptor;

import cn.hutool.core.util.ClassUtil;
import com.bugjc.java.basics.aop.aspect.Aspect;
import com.bugjc.java.basics.aop.aspect.DefaultAspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib 动态代理
 * @author aoki
 * @date 2019/11/29
 * **/
public class CgLibProxyInterceptor implements MethodInterceptor {
    /** 需要代理的目标对象 */
    private Object target;
    /** 切面对象 */
    private Aspect aspect;

    /**
     * 将目标对象传入进行代理
     */
    public Object newProxy(Object target) {
        this.target = target;
        this.aspect = new DefaultAspect();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }

    /**
     * 将目标对象传入进行代理
     */
    public Object newProxy(Object target, Aspect aspect) {
        this.target = target;
        if (aspect == null){
            return newProxy(target);
        }
        this.aspect = aspect;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }



    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        try {
            if (aspect.before(target, method, args)){
                Object returnVal = method.invoke(ClassUtil.isStatic(method) ? null : target, args);
                aspect.afterReturning(target, method, args, returnVal);
                return returnVal;
            }
            return null;
        } catch (Exception ex){
            aspect.afterThrowing(target, method, args, ex.getCause());
            throw ex.getCause();
        } finally {
            aspect.after();
        }
    }
}
