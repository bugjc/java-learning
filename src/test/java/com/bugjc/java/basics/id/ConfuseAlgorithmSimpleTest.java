package com.bugjc.java.basics.id;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ConfuseAlgorithmSimpleTest {

    @Test
    void testGarbleId() {
        ConfuseAlgorithmSimple confuseAlgorithmSimple = new ConfuseAlgorithmSimple();
        int id = 123456;
        long confuseId = confuseAlgorithmSimple.confuse(id);
        log.info("原始 ID:{}", id);
        log.info("混淆后的 ID：{}", confuseId);
        log.info("恢复混淆后的 ID：{}",confuseAlgorithmSimple.restore(confuseId));
    }
}