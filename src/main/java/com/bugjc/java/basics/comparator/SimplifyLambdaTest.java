package com.bugjc.java.basics.comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 简化lambda
 */
public class SimplifyLambdaTest {

    @Getter
    @Setter
    @AllArgsConstructor
    class Student{
        private int age;
    }


    @Test
    public void test(){
        List<Student> students = new ArrayList(){{
            add(new Student(2));
            add(new Student(1));
            add(new Student(3));
            add(new Student(4));
            add(new Student(5));
        }};

        //1、基本lambda
        students.sort((Student s1, Student s2) -> {return Integer.compare(s1.getAge(), s2.getAge());});
        //2、省略参数类型
        students.sort((s1, s2) -> {return Integer.compare(s1.getAge(), s2.getAge());});
        //3、省略return和大括号
        students.sort((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
        //4、使用静态方法简化
        students.sort(Comparator.comparingInt((student) -> student.getAge()));
        //5、一个参数省略小括号
        students.sort(Comparator.comparingInt(student -> student.getAge()));
        //6、只有一个方法，通过类::方法调用
        students.sort(Comparator.comparingDouble(Student::getAge));
        students.forEach(student -> {
            System.out.println(student.getAge());
        });

        //参数作为system.out.println方法的输出可以写成system.out:println

    }
}
