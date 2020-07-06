package com.bugjc.java.basics.clone.example2;

import lombok.extern.slf4j.Slf4j;
import Test;

@Slf4j
class BookTest {

    @Test
    void testClone() throws CloneNotSupportedException {

        BookBorrow bookBorrow = new BookBorrow(1,1);
        Book book1 = new Book(1,"基础系列1",bookBorrow);
        Book book2 = (Book) book1.clone();

        System.out.println("图书1:" + book1.toString());
        System.out.println("图书2:" + book2.toString());

        book2.setName("基础系列2");
        book2.setBookBorrow(new BookBorrow(5,5));

        System.out.println("图书1:" + book1.toString());
        System.out.println("图书2:" + book2.toString());
    }
}