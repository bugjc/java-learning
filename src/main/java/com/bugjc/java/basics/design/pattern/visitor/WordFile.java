package com.bugjc.java.basics.design.pattern.visitor;

/**
 * Word 文件
 * @author aoki
 * @date 2022/1/18
 * **/
public class WordFile extends ResourceFile{
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
