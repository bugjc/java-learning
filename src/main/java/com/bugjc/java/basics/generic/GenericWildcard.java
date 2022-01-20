package com.bugjc.java.basics.generic;

/**
 * 泛型通配符定义
 *
 * @author aoki
 * @date 2020/8/20
 **/
public class GenericWildcard {

    public static Object getData(Field<?> temp) {
        return temp.t;
    }

    public static class Field<T> {
        private T t;

        public Field(T t) {
            this.t = t;
        }
    }

}
