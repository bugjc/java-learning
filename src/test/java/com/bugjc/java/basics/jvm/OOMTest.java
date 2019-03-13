package com.bugjc.java.basics.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OOMTest {

    /**
     * 内存溢出测试
     */
    @Test
    public void testOom(){
        List<int[]> list = new ArrayList<int[]>();   // 持有“大对象”的引用，防止垃圾回收
        while(true){
            int[] tmp = new int[10000000];  // 不断创建“大对象”
            list.add(tmp);
        }
    }

    /**
     * 本地方法溢出测试
     */
    @Test
    public void testSof(){
        localMethod();
    }

    private static void localMethod(){
        localMethod();
    }
}
