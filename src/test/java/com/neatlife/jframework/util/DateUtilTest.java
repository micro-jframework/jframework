package com.neatlife.jframework.util;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void currentSecond() {
        Long timestamp =  $.date.currentSecond();
        Assert.assertTrue(timestamp > 0);

        System.out.println(timestamp);
    }
}