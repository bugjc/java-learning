package com.bugjc.java.basics.reflective;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectiveExample {


    public static void main(String[] args) {

        try {
            String methodName="findByUser";
            //反射获取类
            Class formatter =  Class.forName("com.bugjc.java.basics.reflective.UserService");//UserService.class;
            //反射获取方法
            Method cMethod = formatter.getMethod(methodName, new Class[]{String.class});
            //反射执行方法
            String result = (String) cMethod.invoke(formatter.newInstance(), new Object[]{"1001"});
            System.out.println(result);
        }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


class UserService{
    public String findByUser(String userId){
        return "获取"+userId+"对象的信息。";
    }
}
