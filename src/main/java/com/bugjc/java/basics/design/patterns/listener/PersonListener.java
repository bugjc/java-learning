package com.bugjc.java.basics.design.patterns.listener;

/**
 * 事件监听器
 * @author qingyang
 * @date 2018/9/16 09:47
 */
public interface PersonListener {

    /**

     * @Method: doeat

     * @Description: 这个方法是用来监听Person对象eat(吃)这个行为动作，

     * 当实现类实现doeat方法时就可以监听到Person类对象eat(吃)这个动作

     * @Anthor:孤傲苍狼

     *

     * @param e

     */

    void doeat(Event e);



    /**

     * @Method: dorun

     * @Description: 这个方法是用来监听Person对象run(跑)这个行为动作，

     * 当实现类实现dorun方法时就可以监听到Person类对象run(跑)这个动作

     * @Anthor:孤傲苍狼

     *

     * @param e

     */

    void dorun(Event e);

}
