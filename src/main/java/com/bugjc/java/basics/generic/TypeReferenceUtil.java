package com.bugjc.java.basics.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * TypeReference 机制
 * @author 杨青 2023/6/8
 **/
public class TypeReferenceUtil {

    /**
     * 获取泛型运行时类型
     * @param typeReference 类型包装类
     * @param <T>           运行时类型
     * @return
     */
    public static <T> Type getGenericRuntimeType(TypeReference<T> typeReference) {
        Type type = typeReference.getClass().getGenericSuperclass();
        if (type == null) {
            return null;
        }

        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            return types[0];
        }
        return null;
    }

    public static void main(String[] args) {
        Type type2 = TypeReferenceUtil.getGenericRuntimeType(new TypeReference<List<String>>() {
        });
        System.out.println(type2);
    }
}
