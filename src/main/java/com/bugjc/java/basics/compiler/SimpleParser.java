package com.bugjc.java.basics.compiler;

import com.bugjc.java.basics.compiler.node.ASTNode;
import com.bugjc.java.basics.compiler.node.ASTNodeType;
import com.bugjc.java.basics.compiler.node.impl.SimpleASTNode;
import com.bugjc.java.basics.compiler.token.Token;
import com.bugjc.java.basics.compiler.token.TokenReader;
import com.bugjc.java.basics.compiler.token.TokenType;
import lombok.extern.slf4j.Slf4j;

/**
 * 一个简单的语法解析器。
 * 能够解析简单的表达式、变量声明和初始化语句、赋值语句。
 * 它支持的语法规则为：
 * <p>
 * program -> intDeclare | expressionStatement | assignmentStatement
 * intDeclare -> 'int' Id ( = additive) ';'
 * expressionStatement -> additive ';'
 * additive -> multiplicative ( (+ | -) multiplicative)*
 * multiplicative -> primary ( (* | /) primary)*
 * primary -> IntLiteral | Id | (additive)
 *
 * @author aoki
 */
@Slf4j
public class SimpleParser {
    private SimpleASTNode node;

    /**
     * 解析脚本
     *
     * @param script
     * @return
     * @throws Exception
     */
    public ASTNode parse(String script) throws Exception {
        SimpleLexer lexer = new SimpleLexer();
        TokenReader tokens = lexer.tokenize(script);
        lexer.dump(tokens);
        return program(tokens);
    }

    /**
     * AST的根节点，解析的入口。
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode program(TokenReader tokens) throws Exception {
        SimpleASTNode node = new SimpleASTNode(ASTNodeType.Program, "一个简单的语法解析程序");

        while (tokens.peek() != null) {
            SimpleASTNode child = intDeclare(tokens);

            if (child == null) {
                child = expressionStatement(tokens);
            }

            if (child == null) {
                child = assignmentStatement(tokens);
            }

            if (child != null) {
                node.addChild(child);
            } else {
                throw new Exception("unknown statement");
            }
        }

        return node;
    }

    /**
     * 表达式语句，即表达式后面跟个分号。
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode expressionStatement(TokenReader tokens) throws Exception {
        int pos = tokens.getPosition();
        node = additive(tokens);
        if (node != null) {
            Token token = tokens.peek();
            if (token != null && token.getType() == TokenType.SemiColon) {
                tokens.read();
            } else {
                node = null;
                // 回溯
                tokens.setPosition(pos);
            }
        }
        //直接返回子节点，简化了 AST。
        return node;
    }

    /**
     * 赋值语句，如age = 10*2;
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode assignmentStatement(TokenReader tokens) throws Exception {
        SimpleASTNode node = null;
        //预读，看看下面是不是标识符
        Token token = tokens.peek();
        if (token != null && token.getType() == TokenType.Identifier) {
            //读入标识符
            token = tokens.read();
            node = new SimpleASTNode(ASTNodeType.AssignmentStmt, token.getText());
            //预读，看看下面是不是等号
            token = tokens.peek();
            if (token != null && token.getType() == TokenType.Assignment) {
                //取出等号
                tokens.read();
                SimpleASTNode child = additive(tokens);
                //出错，等号右面没有一个合法的表达式
                if (child == null) {
                    throw new Exception("invalide assignment statement, expecting an expression");
                } else {
                    //添加子节点
                    node.addChild(child);
                    //预读，看看后面是不是分号
                    token = tokens.peek();
                    if (token != null && token.getType() == TokenType.SemiColon) {
                        //消耗掉这个分号
                        tokens.read();

                        //报错，缺少分号
                    } else {
                        throw new Exception("invalid statement, expecting semicolon");
                    }
                }
            } else {
                //回溯，吐出之前消化掉的标识符
                tokens.unread();
                node = null;
            }
        }
        return node;
    }

    /**
     * 整型变量声明，如：
     * int a;
     * int b = 2*3;
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode intDeclare(TokenReader tokens) throws Exception {
        SimpleASTNode node = null;
        Token token = tokens.peek();
        if (token != null && token.getType() == TokenType.Int) {
            token = tokens.read();
            if (tokens.peek().getType() == TokenType.Identifier) {
                token = tokens.read();
                node = new SimpleASTNode(ASTNodeType.IntDeclaration, token.getText());
                token = tokens.peek();
                if (token != null && token.getType() == TokenType.Assignment) {
                    //取出等号
                    tokens.read();
                    SimpleASTNode child = additive(tokens);
                    if (child == null) {
                        throw new Exception("invalide variable initialization, expecting an expression");
                    } else {
                        node.addChild(child);
                    }
                }
            } else {
                throw new Exception("variable name expected");
            }

            token = tokens.peek();
            if (token != null && token.getType() == TokenType.SemiColon) {
                tokens.read();
            } else {
                throw new Exception("invalid statement, expecting semicolon");
            }
        }
        return node;
    }

    /**
     * 加法表达式
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode additive(TokenReader tokens) throws Exception {
        SimpleASTNode child1 = multiplicative(tokens);  //应用add规则
        SimpleASTNode node = child1;
        if (child1 != null) {
            while (true) {                              //循环应用add'规则
                Token token = tokens.peek();
                if (token != null && (token.getType() == TokenType.Plus || token.getType() == TokenType.Minus)) {
                    token = tokens.read();              //读出加号
                    SimpleASTNode child2 = multiplicative(tokens);  //计算下级节点
                    if (child2 != null) {
                        node = new SimpleASTNode(ASTNodeType.Additive, token.getText());
                        node.addChild(child1);              //注意，新节点在顶层，保证正确的结合性
                        node.addChild(child2);
                        child1 = node;
                    } else {
                        throw new Exception("invalid additive expression, expecting the right part.");
                    }
                } else {
                    break;
                }
            }
        }
        return node;
    }

    /**
     * 乘法表达式
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode multiplicative(TokenReader tokens) throws Exception {
        SimpleASTNode child1 = primary(tokens);
        SimpleASTNode node = child1;

        while (true) {
            Token token = tokens.peek();
            if (token != null && (token.getType() == TokenType.Star || token.getType() == TokenType.Slash)) {
                token = tokens.read();
                SimpleASTNode child2 = primary(tokens);
                if (child2 != null) {
                    node = new SimpleASTNode(ASTNodeType.Multiplicative, token.getText());
                    node.addChild(child1);
                    node.addChild(child2);
                    child1 = node;
                } else {
                    throw new Exception("invalid multiplicative expression, expecting the right part.");
                }
            } else {
                break;
            }
        }

        return node;
    }

    /**
     * 基础表达式
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode primary(TokenReader tokens) throws Exception {
        SimpleASTNode node = null;
        Token token = tokens.peek();
        if (token != null) {
            if (token.getType() == TokenType.IntLiteral) {
                token = tokens.read();
                node = new SimpleASTNode(ASTNodeType.IntLiteral, token.getText());
            } else if (token.getType() == TokenType.Identifier) {
                token = tokens.read();
                node = new SimpleASTNode(ASTNodeType.Identifier, token.getText());
            } else if (token.getType() == TokenType.LeftParen) {
                tokens.read();
                node = additive(tokens);
                if (node != null) {
                    token = tokens.peek();
                    if (token != null && token.getType() == TokenType.RightParen) {
                        tokens.read();
                    } else {
                        throw new Exception("expecting right parenthesis");
                    }
                } else {
                    throw new Exception("expecting an additive expression inside parenthesis");
                }
            }
        }
        //这个方法也做了AST的简化，就是不用构造一个primary节点，直接返回子节点。因为它只有一个子节点。
        return node;
    }

    /**
     * 打印输出AST的树状结构
     */
    void dumpAST() {
        node.dump();
    }
}
