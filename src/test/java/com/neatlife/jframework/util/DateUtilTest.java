package com.neatlife.jframework.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void currentSecond() {
        Integer timestamp = DateUtil.currentSecond();
        Assert.assertTrue(timestamp > 0);

        System.out.println(timestamp);
    }
}