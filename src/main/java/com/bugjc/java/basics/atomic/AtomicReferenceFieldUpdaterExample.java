package com.bugjc.java.basics.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicReferenceFieldUpdater 示例
 * @author aoki
 * @date 2019/11/13
 * **/
@Slf4j
public class AtomicReferenceFieldUpdaterExample {

    /**
     * 操作的字段名称
     */
    private static final String FIELD_NAME = "name";

    /**
     * 初始化一个公共对象
     */
    private static Person person = new Person();

    static class Person {
        //一个基于反射的工具类，它能对指定类的指定的volatile引用字段进行原子更新。(注意这个字段不能是private的)
        volatile String name;
    }

    /**
     * 获取对象名称
     * @return
     */
    public static String getName(){
        return person.name;
    }

    /**
     * 比较并交换
     */
    public static void compareAndSet(String name){
        AtomicReferenceFieldUpdater<Person, String> at = AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, FIELD_NAME);
        boolean flag;
        do {
            flag = at.compareAndSet(person, person.name, name);
        }while (!flag);
    }
}
