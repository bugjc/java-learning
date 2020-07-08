package com.bugjc.java.basics.classs;


import org.junit.jupiter.api.Test;

class ImmutableExampleTest {

    @Test
    void test() {
        ImmutableExample immutable = new ImmutableExample("a", 12);
        System.err.println(immutable.getAge());

        ImmutableExample newImmutable = immutable.addAge(10);
        System.err.println(immutable.getAge());

        System.err.println(newImmutable.getAge());
    }
}