package com.bugjc.java.basics.design.pattern.decorator;

/**
 * 装饰抽象类，让装饰完全取代抽象组件，所以必须继承 Phone
 *
 * @author aoki
 * @date 2022/1/20
 **/
public class PhoneDecorator extends Phone {

    /**
     * 装饰的实体对象
     */
    private final Phone p;

    public PhoneDecorator(Phone p) {
        this.p = p;
    }

    @Override
    public void print() {
        if (p != null){
            p.print();
        }
    }
}
