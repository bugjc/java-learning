package com.bugjc.java.basics.design.pattern.delegate;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 事件类
 * @author aoki
 * @date 2020/1/6
 * **/
@Data
public class Event {
    /**
     * 要执行方法的对象
     */
    private Object object;
    /**
     * 要执行的方法名称
     */
    private String methodName;
    /**
     * 要执行方法的参数
     */
    private Object[] params;
    /**
     * 要执行方法的参数类型
     */
    private Class[] paramTypes;

    public Event() {}

    public Event(Object object, String methodName, Object... args) {
        this.object = object;
        this.methodName = methodName;
        this.params = args;
        contractParamTypes(this.params);
    }

    /**
     * 根据参数数组生成参数类型数组
     *
     * @param params
     */
    private void contractParamTypes(Object[] params) {
        this.paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            this.paramTypes[i] = params[i].getClass();
        }
    }


    public Object getObject() {
        return object;
    }

    /**
     * 根据该对象的方法名，方法参数，利用反射机制，执行该方法
     *
     * @throws Exception
     */
    public void invoke() throws Exception {
        Method method = object.getClass().getMethod(this.getMethodName(), this.getParamTypes());
        if (null == method) {
            return;
        }
        method.invoke(this.getObject(), this.getParams());
    }
}
