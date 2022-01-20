package com.bugjc.java.libs.print.console;

import dnl.utils.text.table.TextTable;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

class PrintConsoleTest {

    @Test
    void testPrintTable(){
        Field[] fields = PrintConsole.PrintBean.class.getDeclaredFields();
        String[] titles = new String[]{"Field.getName()", "Field.getGenericType()", "Field.getGenericType() instanceof ParameterizedType"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            boolean isParameterizedType = field.getGenericType() instanceof ParameterizedType;
            values[i][0] = field.getName();
            values[i][1] = field.getGenericType();
            values[i][2] = isParameterizedType;
        }

        // 打印出所有的 Field 的 type 是否属于 ParameterizedType
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }
}