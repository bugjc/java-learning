package com.bugjc.java.libs.retry4j;

import Test;

import static Assertions.*;

class Retry4jExampleTest {

    @Test
    void getUserInfo() {
        Retry4jExample<UserInfo> retry4jExample = new Retry4jExample<>();
        UserInfo userInfo = ((UserInfo)retry4jExample.getUserInfo().getResult());
        assertNotNull(userInfo);
    }
}