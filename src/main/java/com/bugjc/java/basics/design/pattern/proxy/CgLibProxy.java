package com.bugjc.java.basics.design.pattern.proxy;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * CGLib 动态代理
 * @author aoki
 * @date 2019/11/29
 * **/
public class CgLibProxy implements MethodInterceptor {
    /** CGLib需要代理的目标对象 */
    private Object targetObject;

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        try {
            // 检查权限
            before(args);
            Object obj = around(method, targetObject, args);
            afterReturning(obj);
            return obj;
        } catch (Exception ex){
            afterThrowing(args, ex.getCause());
            throw ex.getCause();
        } finally {
            targetObject = null;
            after();
        }
    }

    /**
     * 前置通知： method表示：方法名，pointcut-ref表示：所有的通知共享，（pointcut表示：只有当前通知可用，其他的不能用）
     * @param args
     */
    private void before(Object[] args) {
        System.out.println("======before()======");
        if (Optional.ofNullable(args).isPresent()){
            System.out.println(JSON.toJSONString(args));
        }
    }

    /**
     * 后置通知：returning表示：后置通知的第二个参数名，内容是方法的返回值
     * @param ret
     */
    private void afterReturning(Object ret) {
        System.out.println("======afterReturning()======");
        if (Optional.ofNullable(ret).isPresent()){
            System.out.println(JSON.toJSONString(ret));
        }
    }


    /**
     * 环绕通知
     * @param args
     */
    private Object around(Method method, Object targetObject, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("======around()======");
        return method.invoke(targetObject, args);
    }

    /**
     * 异常通知：throwing表示：异常通知的第二个参数，内容是异常信息
     * @param args
     * @param ex
     */
    private void afterThrowing(Object[] args, Throwable ex) {
        System.out.println("======afterThrowing()======");
        System.out.println(ex.toString());
        if (Optional.ofNullable(args).isPresent()){
            System.out.println(JSON.toJSONString(args));
        }
    }

    /**
     * 最终通知
     */
    private void after() {
        System.out.println("======after()======");
    }
}
