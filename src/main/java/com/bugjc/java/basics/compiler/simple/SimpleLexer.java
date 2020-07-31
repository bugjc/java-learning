package com.bugjc.java.basics.compiler.simple;

import com.bugjc.java.basics.compiler.simple.token.Token;
import com.bugjc.java.basics.compiler.simple.token.TokenReader;
import com.bugjc.java.basics.compiler.simple.token.TokenType;
import com.bugjc.java.basics.compiler.simple.token.impl.SimpleToken;
import com.bugjc.java.basics.compiler.simple.token.impl.SimpleTokenReader;
import lombok.extern.slf4j.Slf4j;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个简单的手写的词法分析器
 * 能够为后面的简单计算器、简单脚本语言产生Token。
 *
 * @author aoki
 * @date 2020/7/28
 **/
@Slf4j
public class SimpleLexer {
    /**
     * //临时保存token的文本
     */
    private StringBuffer tokenText = null;

    /**
     * //保存解析出来的Token
     */
    private List<Token> tokens = null;

    /**
     * //当前正在解析的Token
     */
    private SimpleToken token = null;

    /**
     * 是否是字母
     *
     * @param ch
     * @return
     */
    private boolean isAlpha(int ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    /**
     * 是否是数字
     *
     * @param ch
     * @return
     */
    private boolean isDigit(int ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * 是否是空白字符
     *
     * @param ch
     * @return
     */
    private boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    /**
     * 有限状态机进入初始状态。
     * 这个初始状态其实并不做停留，它马上进入其他状态。
     * 开始解析的时候，进入初始状态；某个Token解析完毕，也进入初始状态，在这里把Token记下来，然后建立一个新的Token。
     *
     * @param ch
     * @return
     */
    private DfaState initToken(char ch) {
        if (tokenText.length() > 0) {
            token.setText(tokenText.toString());
            tokens.add(token);

            tokenText = new StringBuffer();
            token = new SimpleToken();
        }

        DfaState newState;
        //第一个字符是 字母
        if (isAlpha(ch)) {
            if (ch == 'i') {
                newState = DfaState.Id_int1;
            } else {
                //进入Id状态
                newState = DfaState.Id;
            }
            token.setType(TokenType.Identifier);
            tokenText.append(ch);

            //第一个字符是 数字
        } else if (isDigit(ch)) {
            newState = DfaState.IntLiteral;
            token.setType(TokenType.IntLiteral);
            tokenText.append(ch);

            //第一个字符是 >
        } else if (ch == '>') {
            newState = DfaState.GT;
            token.setType(TokenType.GT);
            tokenText.append(ch);
        } else if (ch == '+') {
            newState = DfaState.Plus;
            token.setType(TokenType.Plus);
            tokenText.append(ch);
        } else if (ch == '-') {
            newState = DfaState.Minus;
            token.setType(TokenType.Minus);
            tokenText.append(ch);
        } else if (ch == '*') {
            newState = DfaState.Star;
            token.setType(TokenType.Star);
            tokenText.append(ch);
        } else if (ch == '/') {
            newState = DfaState.Slash;
            token.setType(TokenType.Slash);
            tokenText.append(ch);
        } else if (ch == ';') {
            newState = DfaState.SemiColon;
            token.setType(TokenType.SemiColon);
            tokenText.append(ch);
        } else if (ch == '(') {
            newState = DfaState.LeftParen;
            token.setType(TokenType.LeftParen);
            tokenText.append(ch);
        } else if (ch == ')') {
            newState = DfaState.RightParen;
            token.setType(TokenType.RightParen);
            tokenText.append(ch);
        } else if (ch == '=') {
            newState = DfaState.Assignment;
            token.setType(TokenType.Assignment);
            tokenText.append(ch);
        } else {
            // skip all unknown patterns
            newState = DfaState.Initial;
        }
        return newState;
    }

    /**
     * 解析字符串，形成Token。
     * 这是一个有限状态自动机，在不同的状态中迁移。
     *
     * @param code
     * @return
     */
    public SimpleTokenReader tokenize(String code) {
        tokens = new ArrayList<Token>();
        CharArrayReader reader = new CharArrayReader(code.toCharArray());
        tokenText = new StringBuffer();
        token = new SimpleToken();
        int ich = 0;
        char ch = 0;
        DfaState state = DfaState.Initial;
        try {
            while ((ich = reader.read()) != -1) {
                ch = (char) ich;
                switch (state) {
                    case Initial:
                    case GE:
                    case Assignment:
                    case Plus:
                    case Minus:
                    case Star:
                    case Slash:
                    case SemiColon:
                    case LeftParen:
                    case RightParen:
                        //退出当前状态，并保存Token
                        //重新确定后续状态
                        state = initToken(ch);
                        break;
                    case Id:
                        if (isAlpha(ch) || isDigit(ch)) {
                            //保持标识符状态
                            tokenText.append(ch);
                        } else {
                            //退出标识符状态，并保存Token
                            state = initToken(ch);
                        }
                        break;
                    case GT:
                        if (ch == '=') {
                            //转换成 GE
                            token.setType(TokenType.GE);
                            state = DfaState.GE;
                            tokenText.append(ch);
                        } else {
                            //退出 GT 状态，并保存 Token
                            state = initToken(ch);
                        }
                        break;
                    case IntLiteral:
                        if (isDigit(ch)) {
                            //继续保持在数字字面量状态
                            tokenText.append(ch);
                        } else {
                            //退出当前状态，并保存Token
                            state = initToken(ch);
                        }
                        break;
                    case Id_int1:
                        if (ch == 'n') {
                            state = DfaState.Id_int2;
                            tokenText.append(ch);
                        } else if (isDigit(ch) || isAlpha(ch)) {
                            //切换回Id状态
                            state = DfaState.Id;
                            tokenText.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case Id_int2:
                        if (ch == 't') {
                            state = DfaState.Id_int3;
                            tokenText.append(ch);
                        } else if (isDigit(ch) || isAlpha(ch)) {
                            //切换回 id 状态
                            state = DfaState.Id;
                            tokenText.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case Id_int3:
                        if (isBlank(ch)) {
                            token.setType(TokenType.Int);
                            state = initToken(ch);
                        } else {
                            //切换回 Id 状态
                            state = DfaState.Id;
                            tokenText.append(ch);
                        }
                        break;
                    default:

                }

            }
            // 把最后一个token送进去
            if (tokenText.length() > 0) {
                initToken(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SimpleTokenReader(tokens);
    }

    /**
     * 打印所有的 Token
     *
     * @param tokenReader
     */
    public void dump(TokenReader tokenReader) {
        tokenReader.dump();
    }

    /**
     * 有限状态机的各种状态。
     */
    private enum DfaState {
        //初始态
        Initial,
        //比较状态
        If, Id_if1, Id_if2, Else, Id_else1, Id_else2, Id_else3, Id_else4, Int, Id_int1, Id_int2, Id_int3, Id, GT, GE,
        //
        Assignment,
        //算术运算
        Plus, Minus, Star, Slash,
        //冒号
        SemiColon,
        //左括号
        LeftParen,
        //右括号
        RightParen,
        //整型字面量
        IntLiteral
    }


}
