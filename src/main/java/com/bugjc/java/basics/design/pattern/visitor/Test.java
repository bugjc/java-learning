package com.bugjc.java.basics.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        VisitorStructureObject visitorStructureObject = new VisitorStructureObject();
        //添加资源
        PdfFile pdfFile = new PdfFile("a.pdf");
        WordFile wordFile = new WordFile("b.word");
        PPTFile pptFile = new PPTFile("c.ppt");
        visitorStructureObject.attach(pdfFile);
        visitorStructureObject.attach(wordFile);
        visitorStructureObject.attach(pptFile);

        //接受访问者 Extractor 和 Compressor 访问资源
        visitorStructureObject.accept(new Extractor());

        visitorStructureObject.detach(pptFile);
        visitorStructureObject.accept(new Compressor());
    }
}
