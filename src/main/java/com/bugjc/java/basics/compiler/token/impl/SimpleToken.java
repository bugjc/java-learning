package com.bugjc.java.basics.compiler.token.impl;

import com.bugjc.java.basics.compiler.token.Token;
import com.bugjc.java.basics.compiler.token.TokenType;

/**
 * Token的一个简单实现。只有类型和文本值两个属性。
 *
 * @author aoki
 * @date 2020/7/28
 **/
public class SimpleToken implements Token {

    /**
     * Token类型
     */
    private TokenType type = null;

    /**
     * 文本值
     */
    private String text = null;


    @Override
    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
