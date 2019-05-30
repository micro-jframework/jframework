package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void currentSecond() {
        Long timestamp = DateUtil.currentSecond();
        Assert.assertTrue(timestamp > 0);

        System.out.println(timestamp);
    }
}