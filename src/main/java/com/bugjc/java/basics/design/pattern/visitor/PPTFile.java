package com.bugjc.java.basics.design.pattern.visitor;

/**
 * PPT 文件
 * @author aoki
 * @date 2022/1/18
 * **/
public class PPTFile extends ResourceFile{
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
