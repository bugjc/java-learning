package com.bugjc.java.libs.print.console;

import com.rometools.rome.feed.atom.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 控制台打印表格
 *
 * @author aoki
 * @date 2020/8/21
 **/
public class PrintConsole {
    /**
     * 要打印的对象
     */
    public static class PrintBean {
        Map<String, Person> map;
        Set<String> stringSet;
        Class<?> aClass;
        List<String> stringList;
        Map.Entry<String, String> entry;
        String string;
        Integer integer;
        Set set;
        List aList;
    }
}
