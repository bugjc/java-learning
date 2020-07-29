package com.bugjc.java.basics.compiler;


import cn.hutool.core.lang.Console;
import com.bugjc.java.basics.compiler.token.impl.SimpleTokenReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 测试词法分析器
 */
@Slf4j
class SimpleLexerTest {

    @Test
    void testSimpleLexicalAnalyzerTest() {
        SimpleLexer lexer = new SimpleLexer();

        //测试
        String script = "int age = 45;";
        Console.log("Parse: {}", script);
        SimpleTokenReader tokenReader = lexer.tokenize(script);
        lexer.dump(tokenReader);

        //测试 inta 的解析
        script = "inta age = 45;";
        Console.log("Parse: {}", script);
        tokenReader = lexer.tokenize(script);
        lexer.dump(tokenReader);

        //测试 in 的解析
        script = "in age = 45;";
        Console.log("Parse: {}", script);
        tokenReader = lexer.tokenize(script);
        lexer.dump(tokenReader);

        //测试 >= 的解析
        script = "age >= 45;";
        Console.log("Parse: {}", script);
        tokenReader = lexer.tokenize(script);
        lexer.dump(tokenReader);

        //测试 > 的解析
        script = "age > 45;";
        Console.log("Parse: {}", script);
        tokenReader = lexer.tokenize(script);
        lexer.dump(tokenReader);
    }

}