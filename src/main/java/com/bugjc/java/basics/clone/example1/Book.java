package com.bugjc.java.basics.clone.example1;


public class Book implements Cloneable {

    private int id;

    private String name;

    public Book() {}

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Book)super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Book book1 = new Book();
        book1.setName("基础系列1");
        Book book2 = (Book) book1.clone();

        System.out.println("图书1:" + book1.getName());
        System.out.println("图书2:" + book2.getName());

        book2.setName("基础系列2");

        System.out.println("图书1:" + book1.getName());
        System.out.println("图书2:" + book2.getName());

    }
}
