package com.bugjc.java.basics.immutable;

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

    public static void main(String[] args) {
        Date birthday = new Date();
        MutableExample mutableExample = new MutableExample("小明", 21, birthday);
        System.err.println("小明的生日为 ： " + mutableExample.getBirthday());

        //我设置下我的生日，你会发现我的生日居然可以改变
        birthday.setTime(System.currentTimeMillis() + 1000000000);
        System.err.println("小明的生日为 ： " + mutableExample.getBirthday());
    }

}
