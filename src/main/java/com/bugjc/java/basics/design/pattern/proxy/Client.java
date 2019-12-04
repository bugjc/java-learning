package com.bugjc.java.basics.design.pattern.proxy;

import cn.hutool.core.lang.Singleton;
import com.bugjc.java.basics.aop.ProxyUtil;
import com.bugjc.java.basics.aop.aspect.TimeIntervalAspect;

/**
 * 代理模式[[ 客户端--》代理对象--》目标对象 ]
 * @author aoki
 * @date 2019/11/29
 * **/
public class Client {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

                for (int j = 0; j < 100; j++) {
                    IUserManager userManager = (IUserManager) ProxyUtil.createProxy(Singleton.get(UserManagerImpl.class), Singleton.get(TimeIntervalAspect.class));
                    userManager.addUser(j + "","aoki");
                }

            }).start();

        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
