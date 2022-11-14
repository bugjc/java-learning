package com.bugjc.java.basics.design.pattern.factory.method.button;

/**
 * 通用产品接口
 * @author 杨青 2022/11/14
 **/
public interface Button {
    /**
     * 按钮初始化
     */
    void render();

    /**
     * 按钮点击事件
     */
    void onClick();
}