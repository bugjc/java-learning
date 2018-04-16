package com.bugjc.java.basics.clone.example3;


import java.io.Serializable;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookBorrow getBookBorrow() {
        return bookBorrow;
    }

    public void setBookBorrow(BookBorrow bookBorrow) {
        this.bookBorrow = bookBorrow;
    }

    @Override
    public String toString() {
        return "BOOK[id="+id+",name="+name+",bookBorrow:"+bookBorrow+"]";
    }


    public static void main(String[] args) throws CloneNotSupportedException {

        BookBorrow bookBorrow = new BookBorrow(1,1);
        Book book1 = new Book(1,"基础系列1",bookBorrow);
        Book book2 = CloneUtils.clone(book1);

        System.out.println("图书1:" + book1.toString());
        System.out.println("图书2:" + book2.toString());

        book2.setName("基础系列2");
        book2.setBookBorrow(new BookBorrow(5,5));

        System.out.println("图书1:" + book1.toString());
        System.out.println("图书2:" + book2.toString());

    }

}
