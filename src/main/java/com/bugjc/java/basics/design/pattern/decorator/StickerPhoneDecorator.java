package com.bugjc.java.basics.design.pattern.decorator;

/**
 * 贴膜，具体装饰者
 *
 * @author aoki
 * @date 2022/1/20
 * **/
public class StickerPhoneDecorator extends PhoneDecorator{

    private Phone phone;
    public StickerPhoneDecorator(Phone phone){
        super(phone);
    }

    @Override
    public void print() {
        super.print();
        //添加贴膜行为
        System.out.println("给苹果手机加上了新膜");
    }
}
