package com.bugjc.java.basics.design.pattern.factory.method.button;

/**
 * HTML 按钮产品实现
 * @author 杨青 2022/11/14
 **/
public class HtmlButton implements Button {

    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
