package com.bugjc.java.basics.clone.example3;


import java.io.Serializable;

/**
 * 借书类
 * @author aoki
 * @date 2019/11/22
 * **/
public class BookBorrow implements Serializable{

    private int id;

    private int borState;


    public BookBorrow(int id, int borState) {
        this.id = id;
        this.borState = borState;
    }

    @Override
    public String toString() {
        return "BookBorrow[id="+id+",borState="+borState+"]";
    }

}
