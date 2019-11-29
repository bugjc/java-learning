package com.bugjc.java.basics.design.pattern.proxy;

/**
 * 代理模式[[ 客户端--》代理对象--》目标对象 ]
 * @author aoki
 * @date 2019/11/29
 * **/
public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("**********************CGLibProxy**********************");
        CgLibProxy cgLibProxy = new CgLibProxy();
        IUserManager userManager = (IUserManager) cgLibProxy.createProxyObject(new UserManagerImpl());
        userManager.addUser("1", "aoki");

        System.out.println("**********************JDKProxy**********************");
        JdkProxy jdkPrpxy = new JdkProxy();
        IUserManager iUserManager = (IUserManager) jdkPrpxy.newProxy(new UserManagerImpl());
        iUserManager.addUser("2", "aoki");
    }
}
