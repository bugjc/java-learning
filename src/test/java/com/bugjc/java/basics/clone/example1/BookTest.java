package com.bugjc.java.basics.clone.example1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class BookTest {

    @Test
    void testClone() throws CloneNotSupportedException {
        Book book1 = new Book();
        book1.setName("基础系列1");
        Book book2 = (Book) book1.clone();
        log.info("# 图书2 克隆自 图书1");
        log.info("图书1:" + book1.getName());
        log.info("图书2:" + book2.getName());
        log.info("----------------------------------------");
        log.info("# 修改 图书2 名称后, 图书1 和 图书2 各自的图书名称分别是：");
        book2.setName("基础系列2");

        log.info("图书1:" + book1.getName());
        log.info("图书2:" + book2.getName());
    }
}