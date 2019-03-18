package com.neatlife.jframework.util;

/**
 * @author suxiaolin
 */
public final class DateUtil {

    public Long currentSecond() {
        return (Long) (System.currentTimeMillis() / 1000);
    }

}
