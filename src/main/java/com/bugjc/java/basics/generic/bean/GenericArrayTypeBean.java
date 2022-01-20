package com.bugjc.java.basics.generic.bean;

import com.rometools.rome.feed.atom.Person;

import java.util.List;

/**
 * 数组类型
 *
 * @author aoki
 * @date 2020/8/21
 **/
public class GenericArrayTypeBean<T> {

    /**
     * 属于 GenericArrayType
     */
    public List<String>[] stringListArray;

    /**
     * 属于 GenericArrayType
     */
    T[] typeArray;

    /**
     * 不属于 GenericArrayType
     */
    List<String> stringList;

    /**
     * 不属于 GenericArrayType
     */
    String[] stringArray;

    /**
     * 不属于 GenericArrayType
     */
    Person[] peopleArray;
}
