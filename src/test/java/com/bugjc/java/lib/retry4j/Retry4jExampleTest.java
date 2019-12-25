package com.bugjc.java.lib.retry4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Retry4jExampleTest {

    @Test
    void getUserInfo() {
        Retry4jExample<UserInfo> retry4jExample = new Retry4jExample<>();
        UserInfo userInfo = ((UserInfo)retry4jExample.getUserInfo().getResult());
        assertNotNull(userInfo);
    }
}