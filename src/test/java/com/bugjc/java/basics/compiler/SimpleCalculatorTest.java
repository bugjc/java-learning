package com.bugjc.java.basics.compiler;


import com.bugjc.java.basics.compiler.node.SimpleASTNode;
import com.bugjc.java.basics.compiler.token.TokenReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SimpleCalculatorTest {

    @Test
    void testSimpleCalculator() throws Exception {
        SimpleCalculator calculator = new SimpleCalculator();

        //测试变量声明语句的解析
        String script = "int a = b+3;";
        log.info("Parse: {}", script);
        SimpleLexer lexer = new SimpleLexer();
        TokenReader tokens = lexer.tokenize(script);
        SimpleASTNode simpleASTNode = calculator.intDeclare(tokens);
        calculator.dumpAST(simpleASTNode, "");
        calculator.evaluate(simpleASTNode, "");
    }

    @Test
    void testSimpleCalculator1() {
        SimpleCalculator calculator = new SimpleCalculator();
        //测试表达式
        String script = "2+3*5";
        log.info("\n计算: " + script + "，看上去一切正常。\n");
        calculator.evaluate(script);
    }

    @Test
    void testSimpleCalculator2() {
        SimpleCalculator calculator = new SimpleCalculator();

        //测试语法错误
        String script = "2+";
        log.info("\n" + script + "，应该有语法错误。\n");
        calculator.evaluate(script);
    }

    @Test
    void testSimpleCalculator3() {
        SimpleCalculator calculator = new SimpleCalculator();

        String script = "2+3+4";
        log.info("\n计算: " + script + "，结合性出现错误。\n");
        calculator.evaluate(script);
    }
}