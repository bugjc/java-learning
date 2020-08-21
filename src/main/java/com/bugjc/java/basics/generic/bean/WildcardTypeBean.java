package com.bugjc.java.basics.generic.bean;

import java.util.List;

/**
 * 通配符类型
 * @author aoki
 * @date 2020/8/21
 * **/
public class WildcardTypeBean {
    /**
     * 没有下界
     */
    private List<? extends Number> aList;

    /**
     * 没有指定的话，上边界默认是 Object ,下边界是  String
     */
    private List<? super String> b;

    private List<String> cClass;

    private Class<?> aClass;
}
