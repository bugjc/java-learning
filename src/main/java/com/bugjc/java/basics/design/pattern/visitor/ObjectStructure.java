package com.bugjc.java.basics.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者对象结构
 *
 * @author aoki
 * @date 2022/1/19
 **/
public class ObjectStructure {
    private final List<ResourceFile> resourceFiles = new ArrayList<>();

    /**
     * 添加资源文件
     * @param resourceFile
     */
    public void add(ResourceFile resourceFile) {
        resourceFiles.add(resourceFile);
    }

    /**
     * 删除资源文件
     * @param resourceFile
     */
    public void remove(ResourceFile resourceFile) {
        resourceFiles.remove(resourceFile);
    }

    /**
     * 接受访问者访问资源文件
     * @param visitor
     */
    public void accept(Visitor visitor) {
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(visitor);
        }
    }

}
