package com.bugjc.java.basics.compiler.simple;

import cn.hutool.core.lang.Console;
import com.bugjc.java.basics.compiler.simple.node.ASTNode;
import com.bugjc.java.basics.compiler.simple.node.ASTNodeType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 一个简单的脚本解释器。
 * 所支持的语法，请参见 SimpleParser.java
 * <p>
 * 运行脚本：
 * 在命令行下，键入：java SimpleScript
 * 则进入一个 REPL界面。你可以依次敲入命令。比如：
 * > 2+3;
 * > int age = 10;
 * > int b;
 * > b = 10*2;
 * > age = age + b;
 * > exit();  //退出 REPL界面。
 * <p>
 * 你还可以使用一个参数 -v，让每次执行脚本的时候，都输出 AST 和整个计算过程。
 *
 * @author aoki
 */
public class SimpleScript {
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();

    /**
     * 实现一个简单的REPL
     */
    public static void main(String[] args) {

        SimpleParser parser = new SimpleParser();
        SimpleScript script = new SimpleScript();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder scriptText = new StringBuilder();
        //提示符
        Console.log("\n> ");

        while (true) {
            try {
                String line = reader.readLine().trim();
                if ("exit();".equals(line)) {
                    Console.log("good bye!");
                    break;
                }
                scriptText.append(line).append("\n");
                if (line.endsWith(";")) {
                    ASTNode tree = parser.parse(scriptText.toString());
                    parser.dumpAST();
                    script.evaluate(tree, "");
                    //提示符
                    Console.log("\n> ");
                    scriptText = new StringBuilder();
                }

            } catch (Exception e) {
                Console.log(e.getLocalizedMessage());
                //提示符
                Console.log("\n> ");
                scriptText = new StringBuilder();
            }
        }
    }

    /**
     * 遍历AST，计算值。
     *
     * @param node
     * @param indent
     * @return
     * @throws Exception
     */
    private Integer evaluate(ASTNode node, String indent) throws Exception {
        Integer result = null;
        Console.log(indent + "Calculating: " + node.getType());

        switch (node.getType()) {
            case Program:
                for (ASTNode child : node.getChildren()) {
                    result = evaluate(child, indent);
                }
                break;
            case Additive:
                ASTNode child1 = node.getChildren().get(0);
                Integer value1 = evaluate(child1, indent + "\t");
                ASTNode child2 = node.getChildren().get(1);
                Integer value2 = evaluate(child2, indent + "\t");
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
                result = Integer.valueOf(node.getText());
                break;
            case Identifier:
                String varName = node.getText();
                if (variables.containsKey(varName)) {
                    Integer value = variables.get(varName);
                    if (value != null) {
                        result = value;
                    } else {
                        throw new Exception("variable " + varName + " has not been set any value");
                    }
                } else {
                    throw new Exception("unknown variable: " + varName);
                }
                break;
            case AssignmentStmt:
                varName = node.getText();
                if (!variables.containsKey(varName)) {
                    throw new Exception("unknown variable: " + varName);
                }   //接着执行下面的代码
            case IntDeclaration:
                varName = node.getText();
                Integer varValue = null;
                if (node.getChildren().size() > 0) {
                    ASTNode child = node.getChildren().get(0);
                    result = evaluate(child, indent + "\t");
                    varValue = result;
                }
                variables.put(varName, varValue);
                break;

            default:
        }

        Console.log(indent + "Result: " + result);
        if ("".equals(indent)) {
            if (node.getType() == ASTNodeType.IntDeclaration || node.getType() == ASTNodeType.AssignmentStmt) {
                Console.log(node.getText() + ": " + result);
            }
        }
        return result;
    }

}
