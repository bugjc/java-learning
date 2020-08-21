package com.bugjc.java.basics.generic;

import com.bugjc.java.basics.generic.bean.GenericArrayTypeBean;
import com.bugjc.java.basics.generic.bean.ParameterizedTypeBean;
import com.bugjc.java.basics.generic.bean.TypeVariableBean;
import com.bugjc.java.basics.generic.bean.WildcardTypeBean;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.jar.JarInputStream;

class GenericFieldTest {

    @Test
    void getAllFieldInfo() {
        GenericField.getAllFieldInfo(ParameterizedTypeBean.class);
    }

    @Test
    void getAllFieldParameterizedTypeInfo() {
        GenericField.getAllFieldParameterizedTypeInfo(ParameterizedTypeBean.class);
    }

    @Test
    void getParameterizedTypeInfoByFieldName() throws NoSuchFieldException {
        Field field = ParameterizedTypeBean.class.getDeclaredField("map");
        GenericField.getParameterizedTypeInfoByField(field);
    }

    @Test
    void getAllFieldTypeVariableInfo() {
        TypeVariableBean typeVariableBean = new TypeVariableBean<JarInputStream, String>();
        GenericField.getAllFieldTypeVariableInfo(typeVariableBean.getClass());
    }

    @Test
    void getTypeVariableInfoByField() throws NoSuchFieldException {
        TypeVariableBean typeVariableBean = new TypeVariableBean<JarInputStream, String>();
        Field field = typeVariableBean.getClass().getDeclaredField("key");
        GenericField.getTypeVariableInfoByField(field);
    }

    @Test
    void getAllArrayTypeVariableInfo(){
        GenericField.getAllArrayTypeVariableInfo(GenericArrayTypeBean.class);
    }

    @Test
    void getAllWildcardTypeVariableInfo(){
        GenericField.getAllWildcardTypeVariableInfo(WildcardTypeBean.class);
    }
}