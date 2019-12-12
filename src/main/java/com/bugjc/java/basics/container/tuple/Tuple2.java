package com.bugjc.java.basics.container.tuple;

import lombok.ToString;

/**
 * 二元组
 * @author aoki
 * @date 2019/12/12
 * **/
@ToString
public class Tuple2<A,B> {

    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }
}
