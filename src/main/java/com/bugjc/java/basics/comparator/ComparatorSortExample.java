package com.bugjc.java.basics.comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序
 */
public class ComparatorSortExample {

    static class Dog{
        int age;
        public String name;
        Dog(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        @Override
        public String toString() {
            return "Dog [age=" + age + ", name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        List<Dog> list= new ArrayList<>();
        list.add(new Dog(5, "DogB"));
        list.add(new Dog(6, "DogA"));
        list.add(new Dog(7, "DogC"));
        list.sort((Dog o1, Dog o2) -> o2.age - o1.age);
        System.out.println("给狗狗按照年龄倒序："+list);
        list.sort((Dog o1, Dog o2) -> o1.name.compareTo(o2.name));
        System.out.println("给狗狗按名字字母顺序排序："+list);
    }
}
