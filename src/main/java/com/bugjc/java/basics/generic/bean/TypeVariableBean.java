package com.bugjc.java.basics.generic.bean;

import java.io.Closeable;
import java.io.InputStream;
import java.util.List;

/**
 * 类型变量
 *
 * @author aoki
 * @date 2020/8/21
 **/
public class TypeVariableBean<K extends InputStream & Closeable, V> {
    /**
     * K 的上边界是 InputStream
     */
    K key;

    /**
     * 没有指定的话 ，V 的 上边界 属于 Object
     */
    V value;

    /**
     * 不属于 TypeTypeVariable
     */
    V[] values;
    String str;
    List<K> kList;
}
