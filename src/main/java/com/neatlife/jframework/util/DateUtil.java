package com.neatlife.jframework.util;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author suxiaolin
 */
public final class DateUtil {

    public static Integer currentSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }

}
