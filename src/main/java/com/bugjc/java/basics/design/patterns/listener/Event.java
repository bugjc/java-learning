package com.bugjc.java.basics.design.patterns.listener;

/**
 * 事件对象
 * @author qingyang
 * @date 2018/9/16 09:48
 */
public class Event {

    /**

     * @Field: source

     * 事件源(Person就是事件源)

     */

    private Person source;



    public Event() {}



    public Event(Person source) {

        this.source = source;

    }



    public Person getSource() {

        return source;

    }



    public void setSource(Person source) {

        this.source = source;

    }
}
