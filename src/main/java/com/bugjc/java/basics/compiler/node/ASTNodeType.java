package com.bugjc.java.basics.compiler.node;

/**
 * AST节点的类型。
 *
 * @author aoki
 */
public enum ASTNodeType {
    //程序入口，根节点
    Program,
    //整型变量声明
    IntDeclaration,
    //表达式语句，即表达式后面跟个分号
    ExpressionStmt,
    //赋值语句
    AssignmentStmt,
    //基础表达式
    Primary,
    //乘法表达式
    Multiplicative,
    //加法表达式
    Additive,
    //标识符
    Identifier,
    //整型字面量
    IntLiteral
}