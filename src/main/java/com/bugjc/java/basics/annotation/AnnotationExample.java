package com.bugjc.java.basics.annotation;

import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 注解示例
 * @author aoki
 * @date 2019/11/13
 * **/
public class AnnotationExample {


    /**
     * 获取自定义注解值
     * @return
     */
    public static String getAnnotationValue(){
        Field field = null;
        try {
            field = Table.class.getDeclaredField("id");
            //获取val字段上的Foo注解实例
            Id id = field.getAnnotation(Id.class);
            return id.value();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改自定义注解值
     * @param val       --要修改的注解值
     */
    public static void updAnnotationValue(String val){
        Field field = null;
        try {
            field = Table.class.getDeclaredField("id");
            //获取val字段上的Foo注解实例
            Id id = field.getAnnotation(Id.class);
            //获取 id 这个代理实例所持有的 InvocationHandler
            InvocationHandler h = Proxy.getInvocationHandler(id);
            // 获取 AnnotationInvocationHandler 的 memberValues 字段
            Field hField = h.getClass().getDeclaredField("memberValues");
            // 因为这个字段事 private final 修饰，所以要打开权限
            hField.setAccessible(true);
            // 获取 memberValues
            Map memberValues = (Map) hField.get(h);
            // 修改 value 属性值
            memberValues.put("value", val);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }


    /**
     * 元数据
     * @author aoki
     * @date 2019/11/13
     * **/
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Id {
        String value();
    }

    /**
     * 表格对象
     */
    @Data
    public static class Table {
        @Id(value = "1001")
        private String id;
        private String title;
    }
}
