package com.bugjc.java.basics.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者结构对象
 *
 * @author aoki
 * @date 2022/1/19
 **/
public class VisitorStructureObject {
    private final List<ResourceFile> resourceFiles = new ArrayList<>();

    public void attach(ResourceFile resourceFile) {
        resourceFiles.add(resourceFile);
    }

    public void detach(ResourceFile resourceFile) {
        resourceFiles.remove(resourceFile);
    }

    public void accept(Visitor visitor) {
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(visitor);
        }
    }

}
