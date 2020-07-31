package com.bugjc.java.basics.compiler.simple.token.impl;

import cn.hutool.core.lang.Console;
import com.bugjc.java.basics.compiler.simple.token.Token;
import com.bugjc.java.basics.compiler.simple.token.TokenReader;
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
     * dump
     */
    @Override
    public void dump() {
        Console.log("\nPrint: {}", "Tokens");
        String[] titles = new String[]{"text", "type"};
        Object[][] values = new Object[tokens.size()][2];
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            values[i][0] = token.getText();
            values[i][1] = token.getType();
        }

        TextTable tt = new TextTable(titles, values);
        Console.log("\n----------------------------------------------------------\n");
        tt.printTable(System.out, 1);
        Console.log("\n----------------------------------------------------------\n");
    }

}