package com.bugjc.java;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        //System.out.println(loader);
        ////使用ClassLoader.loadClass()来加载类，不会执行初始化块
        //loader.loadClass("Test2");
        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("Test2.class");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
 //       Class.forName("Test2", false, loader);


    }

    public static class Test2 {
        static {
            System.out.println("静态初始化块执行了！");
        }
    }
}
