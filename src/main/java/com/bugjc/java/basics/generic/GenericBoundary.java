package com.bugjc.java.basics.generic;

/**
 * 泛型边界符定义
 *
 * @author aoki
 * @date 2020/8/20
 **/
public class GenericBoundary {

    /**
     * 查找一个泛型数组中大于某个特定元素的个数
     *
     * @param <C>
     * @param anArray
     * @param elem
     * @return
     */
    public static <C extends Comparable<T>, T> int countGreaterThan(C[] anArray, T elem) {
        int count = 0;
        for (C e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }

    /**
     * 定义一个边界符
     *
     * @param <T>
     */
    public interface Comparable<T> {
        int compareTo(T o);
    }

}
