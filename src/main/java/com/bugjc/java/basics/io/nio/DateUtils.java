package com.bugjc.java.basics.io.nio;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 类描述
 * @author aoki
 * @date 2020/1/14
 * **/
public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String now(){
        return DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
    }
}
