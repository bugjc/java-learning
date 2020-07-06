package com.bugjc.java.basics.id;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import Test;

@Slf4j
class IDObfuscationTest {

    @Test
    void testGarbleId() {
        long randomKey = RandomUtil.randomInt(0,10000);
        for (int i = 1; i < 100; i++) {
            long id = i;
            log.info("原始 ID:{}", id);
            IDObfuscation ob = new IDObfuscation(randomKey);
            String obfuscatedId = ob.obfuscate(id);
            log.info("混淆后的 ID:{}",obfuscatedId);
            id = (int) ob.restore(obfuscatedId);
            log.info("恢复混淆后的 ID:{}",id);
        }
    }
}