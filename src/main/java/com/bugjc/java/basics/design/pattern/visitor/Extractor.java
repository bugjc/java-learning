package com.bugjc.java.basics.design.pattern.visitor;

/**
 * 资源提取器访问者（定义了提取器对不同资源的处理实现）
 * @author aoki
 * @date 2022/1/19
 * **/
public class Extractor implements Visitor {
    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Extract PPT.");
    }

    @Override
    public void visit(PPTFile pdfFile) {
        System.out.println("Extract PDF.");
    }

    @Override
    public void visit(WordFile pdfFile) {
        System.out.println("Extract WORD.");
    }
}
