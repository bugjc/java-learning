package com.bugjc.java.basics.design.pattern.visitor;

/**
 * 访问者接口（定义不同资源的访问接口）
 * @author aoki
 * @date 2022/1/18
 * **/
public interface Visitor {
    /**
     * PdfFile
     * @param pdfFile
     */
    void visit(PdfFile pdfFile);

    /**
     * PPTFile
     * @param pptFile
     */
    void visit(PPTFile pptFile);

    /**
     * WordFile
     * @param wordFile
     */
    void visit(WordFile wordFile);
}
