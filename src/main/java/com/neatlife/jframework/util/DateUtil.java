package com.neatlife.jframework.util;

/**
 * @author suxiaolin
 */
public final class DateUtil {

    public static Integer currentSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }

}
