package com.bugjc.java.basics.generic.bean;

import com.rometools.rome.feed.atom.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 参数化类型
 *
 * @author aoki
 * @date 2020/8/21
 **/
public class ParameterizedTypeBean {
    /**
     * 下面的 field 的 Type 属于 ParameterizedType
     */
    Map<String, Person> map;
    Set<String> stringSet;
    Class<?> aClass;
    Holder<String> holder;
    List<String> stringList;

    /**
     * Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
     * 而 Map.Entry<String, String> entry 的 getOwnerType() 为 Map 所属于的 Type。
     */
    Map.Entry<String, String> entry;

    /**
     * 下面的 field 的 Type 不属于ParameterizedTyp
     */
    String string;
    Integer integer;
    Set set;
    List aList;

    private static class Holder<V> {
    }
}
