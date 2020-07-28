package com.bugjc.java.basics.compiler.node;

import java.util.List;

/**
 * AST 的节点。
 * 属性包括 AST 的类型、文本值、下级子节点和父节点
 * @author aoki
 */
public interface ASTNode {
    /**
     * 父节点
     *
     * @return ASTNode
     */
    ASTNode getParent();

    /**
     * 子节点
     *
     * @return List
     */
    List<ASTNode> getChildren();

    /**
     * AST类型
     *
     * @return ASTNodeType
     */
    ASTNodeType getType();

    /**
     * 文本值
     *
     * @return String
     */
    String getText();
}