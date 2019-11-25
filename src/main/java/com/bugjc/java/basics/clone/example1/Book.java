package com.bugjc.java.basics.clone.example1;


import lombok.Data;

/**
 * 图书类
 * @author aoki
 * @date 2019/11/22
 * **/
@Data
public class Book implements Cloneable {

    private int id;

    private String name;

    public Book() {}

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
