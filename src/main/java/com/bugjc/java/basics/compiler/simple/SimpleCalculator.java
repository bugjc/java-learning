package com.bugjc.java.basics.compiler.simple;

import com.bugjc.java.basics.compiler.simple.node.ASTNode;
import com.bugjc.java.basics.compiler.simple.node.ASTNodeType;
import com.bugjc.java.basics.compiler.simple.node.impl.SimpleASTNode;
import com.bugjc.java.basics.compiler.simple.token.Token;
import com.bugjc.java.basics.compiler.simple.token.TokenReader;
import com.bugjc.java.basics.compiler.simple.token.TokenType;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现一个计算器，但计算的结合性是有问题的。因为它使用了下面的语法规则：
 * <p>
 * additive -> multiplicative | multiplicative + additive
 * multiplicative -> primary | primary * multiplicative
 * <p>
 * 递归项在右边，会自然的对应右结合。我们真正需要的是左结合。
 *
 * @author aoki
 */
@Slf4j
public class SimpleCalculator {

    /**
     * 执行脚本，并打印输出 AST 和求值过程。
     *
     * @param script --脚本代码
     */
    public void evaluate(String script) {
        try {
            ASTNode tree = parse(script);
            dumpAST(tree, "");
            evaluate(tree, "");
        } catch (Exception e) {
            log.info("执行脚本失败！错误信息：{}", e.getMessage());
        }
    }

    /**
     * 解析脚本，并返回根节点
     *
     * @param code
     * @return
     * @throws Exception
     */
    public ASTNode parse(String code) throws Exception {
        SimpleLexer lexer = new SimpleLexer();
        TokenReader tokens = lexer.tokenize(code);
        return program(tokens);
    }

    /**
     * 对某个 AST 节点求值，并打印求值过程。
     *
     * @param node
     * @param indent 打印输出时的缩进量，用tab控制
     * @return
     */
    public int evaluate(ASTNode node, String indent) {
        int result = 0;
        log.info(indent + "Calculating: " + node.getType());
        switch (node.getType()) {
            case Program:
                for (ASTNode child : node.getChildren()) {
                    result = evaluate(child, indent + "\t");
                }
                break;
            case Additive:
                ASTNode child1 = node.getChildren().get(0);
                int value1 = evaluate(child1, indent + "\t");
                ASTNode child2 = node.getChildren().get(1);
                int value2 = evaluate(child2, indent + "\t");
                if ("+".equals(node.getText())) {
                    result = value1 + value2;
                } else {
                    result = value1 - value2;
                }
                break;
            case Multiplicative:
                child1 = node.getChildren().get(0);
                value1 = evaluate(child1, indent + "\t");
                child2 = node.getChildren().get(1);
                value2 = evaluate(child2, indent + "\t");
                if ("*".equals(node.getText())) {
                    result = value1 * value2;
                } else {
                    result = value1 / value2;
                }
                break;
            case IntLiteral:
                result = Integer.parseInt(node.getText());
                break;
            default:
        }
        log.info(indent + "Result: {}", result);
        return result;
    }

    /**
     * 语法解析：根节点
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode program(TokenReader tokens) throws Exception {
        SimpleASTNode node = new SimpleASTNode(ASTNodeType.Program, "Calculator");

        SimpleASTNode child = additive(tokens);

        if (child != null) {
            node.addChild(child);
        }
        return node;
    }

    /**
     * 整型变量声明语句，如：
     * int a;
     * int b = 2*3;
     *
     * @return
     * @throws Exception
     */
    public SimpleASTNode intDeclare(TokenReader tokens) throws Exception {
        SimpleASTNode node = null;
        //预读
        Token token = tokens.peek();
        //匹配Int
        if (token != null && token.getType() == TokenType.Int) {
            //消耗掉int
            token = tokens.read();
            //匹配标识符
            if (tokens.peek().getType() == TokenType.Identifier) {
                //消耗掉标识符
                token = tokens.read();
                //创建当前节点，并把变量名记到AST节点的文本值中，这里新建一个变量子节点也是可以的
                node = new SimpleASTNode(ASTNodeType.IntDeclaration, token.getText());
                //预读
                token = tokens.peek();
                if (token != null && token.getType() == TokenType.Assignment) {
                    //消耗掉等号
                    tokens.read();
                    //匹配一个表达式
                    SimpleASTNode child = additive(tokens);
                    if (child == null) {
                        throw new Exception("invalid variable initialization, expecting an expression");
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
     * 语法解析：加法表达式
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode additive(TokenReader tokens) throws Exception {
        SimpleASTNode child1 = multiplicative(tokens);
        SimpleASTNode node = child1;

        Token token = tokens.peek();
        if (child1 != null && token != null) {
            if (token.getType() == TokenType.Plus || token.getType() == TokenType.Minus) {
                token = tokens.read();
                SimpleASTNode child2 = additive(tokens);
                if (child2 != null) {
                    node = new SimpleASTNode(ASTNodeType.Additive, token.getText());
                    node.addChild(child1);
                    node.addChild(child2);
                } else {
                    throw new Exception("invalid additive expression, expecting the right part.");
                }
            }
        }
        return node;
    }

    /**
     * 语法解析：乘法表达式
     *
     * @return
     * @throws Exception
     */
    private SimpleASTNode multiplicative(TokenReader tokens) throws Exception {
        SimpleASTNode child1 = primary(tokens);
        SimpleASTNode node = child1;

        Token token = tokens.peek();
        if (child1 != null && token != null) {
            if (token.getType() == TokenType.Star || token.getType() == TokenType.Slash) {
                token = tokens.read();
                SimpleASTNode child2 = multiplicative(tokens);
                if (child2 != null) {
                    node = new SimpleASTNode(ASTNodeType.Multiplicative, token.getText());
                    node.addChild(child1);
                    node.addChild(child2);
                } else {
                    throw new Exception("invalid multiplicative expression, expecting the right part.");
                }
            }
        }
        return node;
    }

    /**
     * 语法解析：基础表达式
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
        //这个方法也做了AST的简化，就是不用构造一个 primary节点，直接返回子节点。因为它只有一个子节点。
        return node;
    }

    /**
     * 打印输出AST的树状结构
     *
     * @param node   --抽象树
     * @param indent 缩进字符，由 tab 组成，每一级多一个tab
     */
    public void dumpAST(ASTNode node, String indent) {
        log.info(indent + node.getType() + " " + node.getText());
        for (ASTNode child : node.getChildren()) {
            dumpAST(child, indent + "\t");
        }
    }
}
