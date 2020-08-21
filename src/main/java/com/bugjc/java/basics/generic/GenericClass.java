package com.bugjc.java.basics.generic;

/**
 * 泛型类定义
 *
 * @author aoki
 * @date 2020/8/20
 **/
public class GenericClass<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
