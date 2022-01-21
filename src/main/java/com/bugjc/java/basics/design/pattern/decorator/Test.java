package com.bugjc.java.basics.design.pattern.decorator;

public class Test {
    public static void main(String[] args) {
        //买了一部新的苹果手机
        Phone phone = new ApplePhone();
        System.out.println("买了一部新的苹果手机");

        //给苹果手机加上了新
        PhoneDecorator phoneDecorator = new StickerPhoneDecorator(phone);
        phoneDecorator.print();

        //给苹果手机加上了漂亮的挂件
        PhoneDecorator phoneDecorator1 = new AccessoriesPhoneDecorator(phone);
        phoneDecorator1.print();
        System.out.println("-------------------------------------------------------------");

        //给苹果手机挂件然后贴膜
        PhoneDecorator phoneDecorator2 = new StickerPhoneDecorator(phone);
        PhoneDecorator phoneDecorator3 = new AccessoriesPhoneDecorator(phoneDecorator2);
        phoneDecorator3.print();
    }
}
