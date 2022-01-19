package com.bugjc.java.basics.design.pattern.visitor;

/**
 * 资源压缩器访问者（定义了压缩器对不同资源的处理实现）
 * @author aoki
 * @date 2022/1/19
 * **/
public class Compressor implements Visitor{
    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Compress PPT.");
    }

    @Override
    public void visit(PPTFile pdfFile) {
        System.out.println("Compress PDF.");
    }

    @Override
    public void visit(WordFile pdfFile) {
        System.out.println("Compress WORD.");
    }
}
