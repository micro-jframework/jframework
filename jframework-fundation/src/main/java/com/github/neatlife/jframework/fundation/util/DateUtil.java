package com.github.neatlife.jframework.fundation.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @author suxiaolin
 */
@Slf4j
public final class DateUtil {

    public static Long currentSecond() {
        return (System.currentTimeMillis() / 1000);
    }

    public static String format(Long ms, String pattern) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            result = sdf.format(ms * 1000);
        } catch (Exception e) {
            log.warn("", e);
        }
        return result;
    }
}
