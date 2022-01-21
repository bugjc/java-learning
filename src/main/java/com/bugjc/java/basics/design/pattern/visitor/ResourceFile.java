package com.bugjc.java.basics.design.pattern.visitor;


/**
 * 资源抽象类
 * @author aoki
 * @date 2022/1/18
 * **/
public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 可接收的访问者
     * @param visitor
     */
    abstract public void accept(Visitor visitor);
}
