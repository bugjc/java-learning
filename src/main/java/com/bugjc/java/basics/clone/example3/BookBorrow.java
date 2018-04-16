package com.bugjc.java.basics.clone.example3;


import java.io.Serializable;

public class BookBorrow implements Serializable{

    private int id;

    private int borstate;


    public BookBorrow(int id, int borstate) {
        this.id = id;
        this.borstate = borstate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorstate() {
        return borstate;
    }

    public void setBorstate(int borstate) {
        this.borstate = borstate;
    }

    @Override
    public String toString() {
        return "BookBorrow[id="+id+",borstate="+borstate+"]";
    }

}
