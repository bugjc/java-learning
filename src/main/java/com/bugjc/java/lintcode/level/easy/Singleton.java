package com.bugjc.java.lintcode.level.easy;

/**
 * @Author aoki
 * @Description 单例模式
 * @Date Create in 16:17 2017/8/8
 */
public class Singleton {

    private final static Singleton SOLUTION = new Singleton();

    private Singleton() {
    }

    /**
     * @return: The same instance of this class every time
     */
    public static Singleton getInstance() {
        // write your code here
        return SOLUTION;
    }

}
