package com.bugjc.java.basics.compiler.token.impl;

import com.bugjc.java.basics.compiler.token.Token;
import com.bugjc.java.basics.compiler.token.TokenReader;
import dnl.utils.text.table.TextTable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 一个简单的 Token 流。是把一个 Token 列表进行了封装。
 *
 * @author aoki
 * @date 2020/7/28
 **/
@Slf4j
public class SimpleTokenReader implements TokenReader {
    private List<Token> tokens = null;
    private int pos = 0;

    public SimpleTokenReader(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public Token read() {
        if (pos < tokens.size()) {
            return tokens.get(pos++);
        }
        return null;
    }

    @Override
    public Token peek() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }
        return null;
    }

    @Override
    public void unread() {
        if (pos > 0) {
            pos--;
        }
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public void setPosition(int position) {
        if (position >= 0 && position < tokens.size()) {
            pos = position;
        }
    }

    /**
     * 打印 Tokens
     */
    public void print() {
        String[] titles = new String[]{"text", "type"};
        Object[][] values = new Object[tokens.size()][2];
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            values[i][0] = token.getText();
            values[i][1] = token.getType();
        }

        TextTable tt = new TextTable(titles, values);
        tt.printTable();
        System.out.println("\n");
    }

}