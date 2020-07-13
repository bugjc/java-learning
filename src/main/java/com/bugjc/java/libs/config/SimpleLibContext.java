package com.bugjc.java.libs.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SimpleLibContext {
    private final Config config;

    public SimpleLibContext(Config config) {
        this.config = config;
        config.checkValid(ConfigFactory.defaultReference(), "simple-lib");
    }

    public SimpleLibContext() {
        this(ConfigFactory.load());
    }

    public void printSetting(String path) {
        System.out.println("The setting '" + path + "' is: " + config.getString(path));
    }
}
