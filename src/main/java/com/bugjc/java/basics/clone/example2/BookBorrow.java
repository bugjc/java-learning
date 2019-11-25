package com.bugjc.java.basics.clone.example2;


import lombok.Data;

/**
 * 借书类
 * @author aoki
 * @date 2019/11/22
 * **/
@Data
public class BookBorrow implements Cloneable{

    private int id;

    private int borState;


    public BookBorrow(int id, int borstate) {
        this.id = id;
        this.borState = borstate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (BookBorrow)super.clone();
    }

    @Override
    public String toString() {
        return "BookBorrow[id="+id+",borState="+borState+"]";
    }

}
