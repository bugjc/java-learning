package com.bugjc.java.basics.designpatterns.singleton;

public class MyObject {

    private enum MyEnumSingleton{
        INSTANCE;
        private String pointcatDao;
        private MyEnumSingleton(){
            pointcatDao = "'";
        }

        public String getPointcatDao() {
            return pointcatDao;
        }
    }

    public static String getResource(){
        return MyEnumSingleton.INSTANCE.getPointcatDao();
    }
}
