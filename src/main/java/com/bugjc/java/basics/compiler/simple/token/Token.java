package com.bugjc.java.basics.compiler.simple.token;

/**
 * 一个简单的 Token。
 * 只有类型和文本值两个属性。
 * @author aoki
 * @date 2020/7/28
 * **/
public interface Token{

    /**
     * Token的类型
     * @return  TokenType
     */
    TokenType getType();

    /**
     * Token的文本值
     * @return  String
     */
    String getText();

}