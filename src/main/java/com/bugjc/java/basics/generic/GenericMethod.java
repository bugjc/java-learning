package com.bugjc.java.basics.generic;

/**
 * 泛型方法定义
 *
 * @author aoki
 * @date 2020/8/20
 **/
public class GenericMethod {

    /**
     * 比较泛型方法
     * @param p1
     * @param p2
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
