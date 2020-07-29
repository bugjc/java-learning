package com.bugjc.java.basics.compiler.node.impl;

import cn.hutool.core.lang.Console;
import com.bugjc.java.basics.compiler.node.ASTNode;
import com.bugjc.java.basics.compiler.node.ASTNodeType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 一个简单的AST节点的实现。
 * 属性包括：类型、文本值、父节点、子节点。
 *
 * @author aoki
 */
@Slf4j
public class SimpleASTNode implements ASTNode {
    private SimpleASTNode parent = null;
    private List<ASTNode> children = new ArrayList<ASTNode>();
    private List<ASTNode> readonlyChildren = Collections.unmodifiableList(children);
    private ASTNodeType nodeType = null;
    private String text = null;


    public SimpleASTNode(ASTNodeType nodeType, String text) {
        this.nodeType = nodeType;
        this.text = text;
    }

    @Override
    public ASTNode getParent() {
        return parent;
    }

    @Override
    public List<ASTNode> getChildren() {
        return readonlyChildren;
    }

    @Override
    public ASTNodeType getType() {
        return nodeType;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void dump() {
        Console.log("\nPrint: {}", "AST");
        Console.log("\n----------------------------------------------------------\n");
        StringBuilder indent = new StringBuilder("\t");
        System.out.println(indent.toString() + parent.getType() + " " + parent.getText());
        for (ASTNode child : parent.getChildren()) {
            indent.append("\t");
            Console.log(indent.toString() + child.getType() + " " + child.getText());
        }
        Console.log("\n----------------------------------------------------------\n");
    }

    public void addChild(SimpleASTNode child) {
        children.add(child);
        child.parent = this;
    }
}