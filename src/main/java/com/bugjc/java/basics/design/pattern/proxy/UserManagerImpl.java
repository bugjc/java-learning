package com.bugjc.java.basics.design.pattern.proxy;

import cn.hutool.core.util.RandomUtil;

/**
 * 用户管理实现类
 * @author aoki
 * @date 2019/11/29
 * **/
public class UserManagerImpl implements IUserManager {

    @Override
    public String addUser(String id, String name) {

        System.out.println("======调用了UserManagerImpl.addUser()方法======");
        try {
            Thread.sleep(RandomUtil.randomInt(100,5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }
}
