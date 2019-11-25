package com.bugjc.java.basics.classs;

import lombok.Getter;

import java.util.Date;

/**
 * 不可变类
 * 1、类final修饰
 * 2、类属性无set
 * @author aoki
 */
@Getter
public final class MutableExample {
    /**
     * 所有的属性private且final
     */
    private final String name;
    private final int age;
    private final Date birthday;

    /**
     * 构造方法
     * @param name
     * @param age
     */
    public MutableExample(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        //对于可变类型的属性，初始化的时候应该重新生成一个
        //this.birthday = new Date(birthday.getTime());
        this.birthday = birthday;
    }

    /**
     * 将年龄增加10岁
     * @param newAge
     * @return
     */
    public MutableExample addAge(int newAge) {
        /**
         * 重新返回一个对象
         */
        return new MutableExample(this.getName(), newAge + this.getAge(), this.birthday);
    }



}
