package com.bugjc.java.basics.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatFactory {

    public enum DatePattern {

        TimePattern("yyyy-MM-dd HH:mm:ss"),
        DatePattern("yyyy-MM-dd");

        public String pattern;

        private DatePattern(String pattern) {
            this.pattern = pattern;
        }
    }

    private static final Map<DatePattern, ThreadLocal<DateFormat>> pattern2ThreadLocal;

    static {
        DatePattern[] patterns = DatePattern.values();
        int len = patterns.length;
        pattern2ThreadLocal = new HashMap<DatePattern, ThreadLocal<DateFormat>>(len);

        for (int i = 0; i < len; i++) {
            DatePattern datePattern = patterns[i];
            final String pattern = datePattern.pattern;

            pattern2ThreadLocal.put(datePattern, new ThreadLocal<DateFormat>() {
                @Override
                protected DateFormat initialValue() {
                    return new SimpleDateFormat(pattern);
                }
            });
        }
    }

    //获取DateFormat
    public static DateFormat getDateFormat(DatePattern pattern) {
        ThreadLocal<DateFormat> threadDateFormat = pattern2ThreadLocal.get(pattern);
        //不需要判断threadDateFormat是否为空
        return threadDateFormat.get();
    }

    public static void main(String[] args) {
        System.out.println(DatePattern.TimePattern);
        String dateStr = DateFormatFactory.getDateFormat(DatePattern.TimePattern).format(new Date());
        System.out.println(dateStr);
    }


}
