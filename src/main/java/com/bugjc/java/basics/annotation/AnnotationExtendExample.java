package com.bugjc.java.basics.annotation;

import com.bugjc.java.basics.annotation.extend.AliasFor;
import com.bugjc.java.basics.annotation.extend.AnnotationUtils;

import java.lang.annotation.*;

/**
 * 注解扩展示例
 * 自定义注解继承注解
 */
public class AnnotationExtendExample {
    public static void main(String[] args) {
        //自定义注解继承，解决注解不能继承的问题。
        Component component = AnnotationUtils.getAnnotation(UserServiceImpl.class, Component.class);
        System.out.println(component.value());
    }


    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Component {
        String value() default "";
    }

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Component
    public @interface Service {
        @AliasFor(annotation = Component.class)
        String value() default "";
    }

    public interface IUserService {
    }

    @Service("UserServiceImpl")
    public class UserServiceImpl implements IUserService {

    }
}
