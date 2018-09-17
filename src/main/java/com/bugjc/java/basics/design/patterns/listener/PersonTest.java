package com.bugjc.java.basics.design.patterns.listener;

/**
 * @author qingyang
 * @date 2018/9/16 09:50
 */
public class PersonTest {

    public static void main(String[] args) {

        //

        Person p = new Person();

        //注册监听p对象行为的监听器

        p.registerListener(new PersonListener() {

            //监听p吃东西这个行为

            public void doeat(Event e) {

                Person p = e.getSource();

                System.out.println(p + "在吃东西");

            }

            //监听p跑步这个行为

            public void dorun(Event e) {

                Person p = e.getSource();

                System.out.println(p + "在跑步");

            }

        });

        //p在吃东西

        p.eat();

        //p在跑步

        p.run();

    }

}
