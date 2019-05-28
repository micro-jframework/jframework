package com.github.neatlife.jframework.util;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class PrecisionUtilTest {

    @Test
    public void testBigDecimalAdd() {
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.add(null, BigDecimal.TEN));
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.add(BigDecimal.TEN, null));
        Assert.assertEquals(new BigDecimal("7"),
                PrecisionUtil.add(new BigDecimal("3"), new BigDecimal("4")));
    }

    @Test
    public void testBigDecimalSubtract() {
        Assert.assertEquals(new BigDecimal("-10"),
                PrecisionUtil.subtract(null, BigDecimal.TEN));
        Assert.assertEquals(BigDecimal.ONE,
                PrecisionUtil.subtract(BigDecimal.ONE, null));
        Assert.assertEquals(new BigDecimal("3"),
                PrecisionUtil.subtract(new BigDecimal("7"), new BigDecimal("4")));
    }

    @Test
    public void testBigDecimalMultiply() {
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.multiply(null, BigDecimal.TEN));
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.multiply(BigDecimal.TEN, null));
        Assert.assertEquals(new BigDecimal("8"),
                PrecisionUtil.multiply(new BigDecimal("2"), new BigDecimal("4")));
    }

    @Test
    public void testBigDecimalDivide() {
        Assert.assertEquals(new BigDecimal("0.10"),
                PrecisionUtil.divide(BigDecimal.ONE, BigDecimal.TEN));
        Assert.assertEquals(new BigDecimal("0.10"),
                PrecisionUtil.divide(BigDecimal.ONE, BigDecimal.TEN));
        Assert.assertEquals(new BigDecimal("0"),
                PrecisionUtil.divide(null, BigDecimal.TEN, 1));
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.divide(BigDecimal.TEN, null, 0));
        Assert.assertEquals(new BigDecimal("5"),
                PrecisionUtil.divide(BigDecimal.TEN, new BigDecimal("2"), 0));
    }

    @Test
    public void testStringAdd() {
        Assert.assertEquals("1", PrecisionUtil.add("1", null));
        Assert.assertEquals("2", PrecisionUtil.add(null, "2"));
        Assert.assertEquals("5", PrecisionUtil.add("3", "2"));
    }

    @Test
    public void testStringSubtract() {
        Assert.assertEquals("1", PrecisionUtil.subtract("1", null));
        Assert.assertEquals("-2", PrecisionUtil.subtract(null, "2"));
        Assert.assertEquals("3", PrecisionUtil.subtract("5", "2"));
    }

    @Test
    public void testStringMultiply() {
        Assert.assertEquals("1", PrecisionUtil.multiply("1", null));
        Assert.assertEquals("2", PrecisionUtil.multiply(null, "2"));
        Assert.assertEquals("6", PrecisionUtil.multiply("3", "2"));
    }

    @Test
    public void testStringDivide() {
        Assert.assertEquals("1.00", PrecisionUtil.divide("1", null));
        Assert.assertEquals("0", PrecisionUtil.divide(null, "2"));
        Assert.assertEquals("2.00", PrecisionUtil.divide("6", "3"));
    }

    @Test
    public void testBigDecimalSum() {
        Assert.assertEquals(new BigDecimal("11"), PrecisionUtil.sum(null,
                BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN));
        Assert.assertEquals(BigDecimal.TEN,
                PrecisionUtil.sum(BigDecimal.TEN, null));
        Assert.assertEquals(new BigDecimal("14"),
                PrecisionUtil.sum(BigDecimal.TEN, new BigDecimal("4")));
    }

    @Test
    public void testStringSum() {
        Assert.assertEquals("1", PrecisionUtil.sum("1", null));
        Assert.assertEquals("6", PrecisionUtil.sum(null, "1", "2", "3"));
        Assert.assertEquals("8", PrecisionUtil.sum("2", "1", "2", "3"));
    }

    @Test
    public void testBigDecimalAvg() {
        Assert.assertEquals(new BigDecimal("3.67"), PrecisionUtil.avg(
                BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN));
    }

    @Test
    public void testStringAvg() {
        Assert.assertEquals("2.00", PrecisionUtil.avg("1", "2", "3"));
    }

    @Test
    public void testBigDecimalMaxArr() {
        Assert.assertEquals(BigDecimal.TEN, PrecisionUtil.maxArr(
                BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ONE));
    }

    @Test
    public void testBigDecimalMinArr() {
        Assert.assertEquals(BigDecimal.ZERO, PrecisionUtil.minArr(
                BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN));
    }

    //
    @Test
    public void testStringMaxArr() {
        Assert.assertNull(PrecisionUtil.max(null, "1", "2", "3"));
        Assert.assertEquals("4", PrecisionUtil.maxArr("2", "4", "1", "3"));
        Assert.assertEquals("2", PrecisionUtil.maxArr("2", "", "", ""));
        Assert.assertEquals("2", PrecisionUtil.maxArr("2", null, null, null));
    }

    @Test
    public void testStringMinArr() {
        Assert.assertNull(PrecisionUtil.minArr(null, "1", "2", "3"));
        Assert.assertEquals("1", PrecisionUtil.minArr("2", "4", "1", "3"));
        Assert.assertEquals("2", PrecisionUtil.minArr("2", "", "", ""));
        Assert.assertEquals("2", PrecisionUtil.minArr("2", null, null, null));
    }

    @Test
    public void testDoubleRound() {
        Assert.assertEquals(3.0, PrecisionUtil.round(2.567, 0), 0);
        Assert.assertEquals(2.6, PrecisionUtil.round(2.567, 1), 0);
        Assert.assertEquals(2.57, PrecisionUtil.round(2.567, 2), 0);
        Assert.assertEquals(2.567, PrecisionUtil.round(2.567, 3), 0);
        Assert.assertEquals(2.567, PrecisionUtil.round(2.567, 4), 0);
    }

    @Test
    public void testBigDecimalRound() {
        Assert.assertEquals(new BigDecimal("3"),
                PrecisionUtil.round(new BigDecimal("2.567"), 0));
        Assert.assertEquals(new BigDecimal("2.6"),
                PrecisionUtil.round(new BigDecimal("2.567"), 1));
        Assert.assertEquals(new BigDecimal("2.57"),
                PrecisionUtil.round(new BigDecimal("2.567"), 2));
        Assert.assertEquals(new BigDecimal("2.567"),
                PrecisionUtil.round(new BigDecimal("2.567"), 3));
        Assert.assertEquals(new BigDecimal("2.5670"),
                PrecisionUtil.round(new BigDecimal("2.567"), 4));
    }
}
