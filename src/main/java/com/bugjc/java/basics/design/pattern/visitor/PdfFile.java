package com.bugjc.java.basics.design.pattern.visitor;

/**
 * PDF 文件
 * @author aoki
 * @date 2022/1/18
 * **/
public class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
