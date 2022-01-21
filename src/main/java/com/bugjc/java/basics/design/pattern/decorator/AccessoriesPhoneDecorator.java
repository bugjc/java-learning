package com.bugjc.java.basics.design.pattern.decorator;

/**
 * 手机挂件，即具体装饰者
 *
 * @author aoki
 * @date 2022/1/20
 * **/
public class AccessoriesPhoneDecorator extends PhoneDecorator{
    public AccessoriesPhoneDecorator(Phone phone) {
        super(phone);
        //添加行为
        System.out.println("给苹果手机加上了漂亮的挂件");
    }
}
