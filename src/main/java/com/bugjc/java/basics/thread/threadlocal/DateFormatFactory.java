package com.bugjc.java.basics.thread.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式工厂
 * @author aoki
 * @date 2019/11/25
 * **/
public class DateFormatFactory {

    public enum DatePattern {
        /**
         * 日期格式
         */
        TimePattern("yyyy-MM-dd HH:mm:ss"),
        DatePattern("yyyy-MM-dd");

        public String pattern;

        DatePattern(String pattern) {
            this.pattern = pattern;
        }
    }

    private static final Map<DatePattern, ThreadLocal<DateFormat>> PATTERN_TO_THREAD_LOCAL;

    static {
        DatePattern[] patterns = DatePattern.values();
        int len = patterns.length;
        PATTERN_TO_THREAD_LOCAL = new HashMap<>(len);

        for (DatePattern datePattern : patterns) {
            final String pattern = datePattern.pattern;
            PATTERN_TO_THREAD_LOCAL.put(datePattern, ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern)));
        }
    }

    /**
     * 获取DateFormat
     * @param pattern
     * @return
     */
    public static DateFormat getDateFormat(DatePattern pattern) {
        ThreadLocal<DateFormat> threadDateFormat = PATTERN_TO_THREAD_LOCAL.get(pattern);
        //不需要判断threadDateFormat是否为空
        return threadDateFormat.get();
    }

}
