package com.bugjc.java.libs.bloom.filter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class BloomFilterMainTest {

    @Test
    void mightContain() {
        String element = "100";
        BloomFilterMain.addElement(element);
        log.info("将元素 {} 添加到布隆过滤器中。", element);
        log.info("元素 {} 使用布隆过滤器。", element);
        boolean flag = BloomFilterMain.mightContain(element);
        log.info("可能包含元素吗？{}", flag);

        element = "101";
        log.info("元素 {} 使用布隆过滤器。", element);
        flag = BloomFilterMain.mightContain(element);
        log.info("可能包含元素吗？{}", flag);
    }
}