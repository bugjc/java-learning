package com.bugjc.java.libs.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 读取配置
 * @author aoki
 * @date 2020/8/6
 * **/
public class ConfigMain {
    public static void main(String[] args) {

        System.setProperty("simple-lib.whatever", "This value comes from a system property");

        Config conf = ConfigFactory.load();
        System.out.println("The answer is: " + conf.getString("simple-app.answer"));

        Config newConf = ConfigFactory.load("myapp").withFallback(conf);
        System.out.println("The key2 is: " + conf.getString("test.key2"));
        System.out.println("The key1 is: " + newConf.getString("test.key1"));

    }
}
