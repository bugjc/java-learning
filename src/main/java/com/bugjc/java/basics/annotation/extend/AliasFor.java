package com.bugjc.java.basics.annotation.extend;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AliasFor {

    String attribute() default "";

    Class<? extends Annotation> annotation();
}
