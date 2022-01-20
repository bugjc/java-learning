package com.bugjc.java.basics.generic;

import dnl.utils.text.table.TextTable;

import java.lang.reflect.*;

/**
 * 参数化类型接口
 *
 * @author aoki
 * @date 2020/8/20
 **/
class GenericField {

    /**
     * 获取所有的字段信息并打印
     */
    static <T> void getAllFieldInfo(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        String[] titles = new String[]{"Field.getName()", "Field.getGenericType()", "Field.getGenericType() instanceof ParameterizedType"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            boolean isParameterizedType = field.getGenericType() instanceof ParameterizedType;
            values[i][0] = field.getName();
            values[i][1] = field.getGenericType();
            values[i][2] = isParameterizedType;
        }

        // 打印
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }

    /**
     * 获取所有的参数化类型信息
     *
     * @param tClass
     * @param <T>
     */
    static <T> void getAllFieldParameterizedTypeInfo(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        String[] titles = new String[]{"ParameterizedType.getRawType()", "ParameterizedType.getOwnerType()", "ParameterizedType.getActualTypeArguments()"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {

            values[i][0] = "N/A";
            values[i][1] = "N/A";
            values[i][2] = "N/A";

            Field field = fields[i];
            boolean isParameterizedType = field.getGenericType() instanceof ParameterizedType;
            if (!isParameterizedType) {
                continue;
            }

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            values[i][0] = parameterizedType.getRawType() == null ? "N/A" : parameterizedType.getRawType();
            values[i][1] = parameterizedType.getOwnerType() == null ? "N/A" : parameterizedType.getOwnerType();

            StringBuilder actualTypeArguments = new StringBuilder();
            for (int j = 0; j < parameterizedType.getActualTypeArguments().length; j++) {
                actualTypeArguments.append("[").append(j).append("] = ").append(parameterizedType.getActualTypeArguments()[j]).append(" ");
            }
            values[i][2] = actualTypeArguments.length() == 0 ? "N/A" : actualTypeArguments.toString();
        }

        // 打印
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }

    /**
     * 获取指定字段的参数化类型信息
     *
     * @param field
     */
    static void getParameterizedTypeInfoByField(Field field) {
        System.out.println();
        System.out.println("-------------------------------Begin------------------------------------------------------");
        field.setAccessible(true);
        boolean isParameterizedType = field.getGenericType() instanceof ParameterizedType;
        if (isParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            System.out.println("ParameterizedType.getRawType() = " + parameterizedType.getRawType());
            for (int i = 0; i < parameterizedType.getActualTypeArguments().length; i++) {
                System.out.println("ParameterizedType.getActualTypeArguments()[" + i + "] = " + parameterizedType.getActualTypeArguments()[i]);
            }
            System.out.println("ParameterizedType.getOwnerType() = " + parameterizedType.getOwnerType());
        }
        System.out.println("-------------------------------End------------------------------------------------------");
        System.out.println();
    }


    /**
     * 获取所有的类型变量信息
     *
     * @param tClass
     * @param <T>
     */
    static <T> void getAllFieldTypeVariableInfo(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        String[] titles = new String[]{"TypeVariable.getName()", "TypeVariable.getGenericDeclaration()", "TypeVariable.getBounds()", "TypeVariable.getAnnotatedBounds()"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {

            values[i][0] = "N/A";
            values[i][1] = "N/A";
            values[i][2] = "N/A";
            values[i][3] = "N/A";

            Field field = fields[i];
            boolean isTypeVariable = field.getGenericType() instanceof TypeVariable;
            if (!isTypeVariable) {
                continue;
            }

            TypeVariable typeVariable = (TypeVariable) field.getGenericType();
            values[i][0] = typeVariable.getName();
            values[i][1] = typeVariable.getGenericDeclaration();

            StringBuilder bounds = new StringBuilder();
            for (int j = 0; j < typeVariable.getBounds().length; j++) {
                bounds.append("[").append(j).append("] = ").append(typeVariable.getBounds()[j]).append(" ");
            }
            values[i][2] = bounds.length() == 0 ? "N/A" : bounds.toString();

            StringBuilder annotatedBounds = new StringBuilder();
            for (int j = 0; j < typeVariable.getBounds().length; j++) {
                annotatedBounds.append("[").append(j).append("] = ").append(typeVariable.getAnnotatedBounds()[j]).append(" ");
            }
            values[i][3] = annotatedBounds.length() == 0 ? "N/A" : annotatedBounds.toString();
        }

        // 打印
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }

    /**
     * 获取指定字段的类型变量信息
     *
     * @param field
     */
    static void getTypeVariableInfoByField(Field field) {
        System.out.println();
        System.out.println("-------------------------------Begin------------------------------------------------------");
        field.setAccessible(true);
        boolean isTypeVariable = field.getGenericType() instanceof TypeVariable;
        if (isTypeVariable) {
            TypeVariable typeVariable = (TypeVariable) field.getGenericType();
            System.out.println("TypeVariable.getName() = " + typeVariable.getName());
            System.out.println("TypeVariable.getGenericDeclaration() = " + typeVariable.getGenericDeclaration());
            for (int i = 0; i < typeVariable.getBounds().length; i++) {
                System.out.println("TypeVariable.getBounds()[" + i + "] = " + typeVariable.getBounds()[i]);
            }
            for (int i = 0; i < typeVariable.getAnnotatedBounds().length; i++) {
                System.out.println("TypeVariable.getAnnotatedBounds()[" + i + "] = " + typeVariable.getAnnotatedBounds()[i]);
            }
        }
        System.out.println("-------------------------------End------------------------------------------------------");
        System.out.println();
    }


    /**
     * 获取所有的数组类型信息
     *
     * @param tClass
     * @param <T>
     */
    static <T> void getAllArrayTypeVariableInfo(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        String[] titles = new String[]{"GenericArrayType.getTypeName()", "GenericArrayType.getGenericComponentType()"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {

            values[i][0] = "N/A";
            values[i][1] = "N/A";

            Field field = fields[i];
            boolean isGenericArrayType = field.getGenericType() instanceof GenericArrayType;
            if (!isGenericArrayType) {
                continue;
            }

            GenericArrayType arrayType = (GenericArrayType) field.getGenericType();
            values[i][0] = arrayType.getTypeName();
            values[i][1] = arrayType.getGenericComponentType();

        }

        // 打印
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }

    /**
     * 获取所有的通配符类型信息
     *
     * @param tClass
     * @param <T>
     */
    static <T> void getAllWildcardTypeVariableInfo(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        String[] titles = new String[]{"GenericArrayType.getLowerBounds()", "GenericArrayType.getUpperBounds()"};
        Object[][] values = new Object[fields.length][titles.length];
        for (int i = 0; i < fields.length; i++) {

            values[i][0] = "N/A";
            values[i][1] = "N/A";

            Field field = fields[i];
            boolean isParameterizedType = field.getGenericType() instanceof ParameterizedType;
            if (!isParameterizedType) {
                continue;
            }

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type : types) {
                boolean isWildcardType = type instanceof WildcardType;
                if (!isWildcardType) {
                    continue;
                }

                WildcardType wildcardType = (WildcardType) type;
                StringBuilder lowerBounds = new StringBuilder();
                for (int j = 0; j < wildcardType.getLowerBounds().length; j++) {
                    lowerBounds.append("[").append(j).append("] = ").append(wildcardType.getLowerBounds()[j]).append(" ");
                }
                values[i][0] = lowerBounds.length() == 0 ? "N/A" : lowerBounds.toString();

                StringBuilder upperBounds = new StringBuilder();
                for (int j = 0; j < wildcardType.getUpperBounds().length; j++) {
                    upperBounds.append("[").append(j).append("] = ").append(wildcardType.getUpperBounds()[j]).append(" ");
                }
                values[i][1] = upperBounds.length() == 0 ? "N/A" : upperBounds.toString();
            }

        }

        // 打印
        TextTable textTable = new TextTable(titles, values);
        textTable.printTable();
    }

}
