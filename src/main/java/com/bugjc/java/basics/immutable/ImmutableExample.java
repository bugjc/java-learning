package com.bugjc.java.basics.immutable;


import lombok.Getter;

/**
 * 不可变类
 * 1、类final修饰
 * 2、类属性无set
 * 参考至：https://juejin.im/post/5ac1cbde518825558723b6e9#comment
 * @author aoki
 */
@Getter
public final class ImmutableExample {
    /**
     * 所有的属性private且final
     */

    private final String name;
    private final int age;

    /**
     * 构造方法
     * @param name
     * @param age
     */
    public ImmutableExample(String name, int age) {
        this.name = name;
        this.age = age;
    }


    /**
     * 将年龄增加10岁
     * @param newAge
     * @return
     */
    public ImmutableExample addAge(int newAge) {
        /**
         * 重新返回一个对象
         */
        return new ImmutableExample(this.getName(), newAge + this.getAge());
    }

    public static void main(String[] args) {
        ImmutableExample immutable = new ImmutableExample("a", 12);
        System.err.println(immutable.getAge());
        ImmutableExample newImmutable = immutable.addAge(10);
        System.err.println(immutable.getAge());
        System.err.println(newImmutable.getAge());
    }
}
