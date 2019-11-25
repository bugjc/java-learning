package com.bugjc.java.basics.clone.example3;


import lombok.Data;

import java.io.Serializable;

/**
 * 图书类
 * @author aoki
 * @date 2019/11/22
 * **/
@Data
public class Book implements Serializable {

    private int id;

    private String name;

    private BookBorrow bookBorrow;

    public Book() {}

    public Book(int id, String name, BookBorrow bookBorrow) {
        this.id = id;
        this.name = name;
        this.bookBorrow = bookBorrow;
    }

    @Override
    public String toString() {
        return "BOOK[id="+id+",name="+name+",bookBorrow:"+bookBorrow+"]";
    }



}
