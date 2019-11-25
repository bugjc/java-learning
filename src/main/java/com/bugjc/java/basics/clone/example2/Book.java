package com.bugjc.java.basics.clone.example2;


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

    private BookBorrow bookBorrow;

    public Book() {}

    public Book(int id, String name, BookBorrow bookBorrow) {
        this.id = id;
        this.name = name;
        this.bookBorrow = bookBorrow;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Book book = (Book)super.clone();
        //这里注释掉就是浅克隆，否则就是深克隆
        book.bookBorrow = (BookBorrow)bookBorrow.clone();
        return book;
    }


    @Override
    public String toString() {
        return "BOOK[id="+id+",name="+name+",bookBorrow:"+bookBorrow+"]";
    }

}
