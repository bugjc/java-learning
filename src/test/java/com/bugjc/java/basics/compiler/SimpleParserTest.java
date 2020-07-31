package com.bugjc.java.basics.compiler;

import cn.hutool.core.lang.Console;
import com.bugjc.java.basics.compiler.simple.SimpleParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SimpleParserTest {


    @Test
    void testSimpleParser() {
        SimpleParser parser = new SimpleParser();
        String script = null;
        try {
            script = "int age = 45+2; age= 20; age+10*2;";
            Console.log("Parse：{}", script);
            parser.parse(script);
            parser.dumpAST();
        } catch (Exception e) {
            Console.error("解析错误！异常信息：{}", e.getMessage());
        }

        //测试异常语法
        try {
            script = "2+3+;";
            Console.log("Parse：{}", script);
            parser.parse(script);
            parser.dumpAST();
        } catch (Exception e) {
            Console.error("解析错误！异常信息：{}", e.getMessage());
        }

        //测试异常语法
        try {
            script = "2+3*;";
            Console.log("Parse：{}", script);
            parser.parse(script);
            parser.dumpAST();
        } catch (Exception e) {
            Console.error("解析错误！异常信息：{}", e.getMessage());
        }
    }
}