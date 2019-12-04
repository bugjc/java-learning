package com.bugjc.java.basics.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
class DateFormatFactoryTest {

    @Test
    void getDateFormat() {
        log.info("{}", DateFormatFactory.DatePattern.TimePattern);
        String dateStr = DateFormatFactory.getDateFormat(DateFormatFactory.DatePattern.TimePattern).format(new Date());
        log.info("{}", dateStr);
    }
}