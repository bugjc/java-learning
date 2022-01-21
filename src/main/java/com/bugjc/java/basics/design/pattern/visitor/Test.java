package com.bugjc.java.basics.design.pattern.visitor;

public class Test {
    public static void main(String[] args) {
        ObjectStructure visitorStructureObject = new ObjectStructure();
        //添加资源
        PdfFile pdfFile = new PdfFile("a.pdf");
        WordFile wordFile = new WordFile("b.word");
        PPTFile pptFile = new PPTFile("c.ppt");
        visitorStructureObject.add(pdfFile);
        visitorStructureObject.add(wordFile);
        visitorStructureObject.add(pptFile);

        //接受访问者 Extractor 和 Compressor 访问资源
        visitorStructureObject.accept(new Extractor());

        visitorStructureObject.remove(pptFile);
        visitorStructureObject.accept(new Compressor());
    }
}
