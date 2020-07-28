package com.bugjc.java.basics.compiler.token;

/**
 * Token 的类型
 *
 * @author aoki
 * @date 2020/7/28
 **/
public enum TokenType {
    // +
    Plus,
    // -
    Minus,
    // *
    Star,
    // /
    Slash,

    // >=
    GE,
    // >
    GT,
    // ==
    EQ,
    // <=
    LE,
    // <
    LT,

    // ;
    SemiColon,
    // (
    LeftParen,
    // )
    RightParen,

    // =
    Assignment,

    If,
    Else,

    Int,

    //标识符
    Identifier,
    //整型字面量
    IntLiteral,
    //字符串字面量
    StringLiteral
}