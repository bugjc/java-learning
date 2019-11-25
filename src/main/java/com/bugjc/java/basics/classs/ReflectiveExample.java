package com.bugjc.java.basics.classs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectiveExample {


    public static void main(String[] args) {

        try {
            String methodName="findByUser";
            //反射获取类
            Class<?> formatter = Class.forName("com.bugjc.java.reflective.UserService");
            //反射获取方法
            Method cMethod = formatter.getMethod(methodName, String.class);
            //反射执行方法
            String result = (String) cMethod.invoke(formatter.newInstance(), new Object[]{"1001"});
            System.out.println(result);
        }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


class UserService{
    public String findByUser(String userId){
        return "获取"+userId+"对象的信息。";
    }
}
