package com.bugjc.java.basics.stream;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListStreamExample {

    @Data
    private static class Region{
        private String code;
        private String name;
    }

    /**
     * 集合转换
     * @param regions
     * @return
     */
    public List<String> transform(List<Region> regions){
        if (regions == null || regions.isEmpty()){
            return Collections.emptyList();
        }
        return regions.stream().map(Region::getName).collect(Collectors.toList());
    }
}
