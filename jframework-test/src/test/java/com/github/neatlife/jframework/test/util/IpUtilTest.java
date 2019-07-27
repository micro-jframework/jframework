package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.IpUtil;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.http.HttpServletRequest;

public class IpUtilTest {

    @Test
    public void testGetIpAddr1() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getHeader("x-forwarded-for"))
                .thenReturn("0:0:0:0:0:0:0:1");

        Assert.assertEquals("127.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testGetIpAddr2() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getHeader("Proxy-Client-IP"))
                .thenReturn("0:0:0:0:0:0:0:1");

        Assert.assertEquals("127.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testGetIpAddr3() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getHeader("X-Forwarded-For"))
                .thenReturn("0:0:0:0:0:0:0:1");

        Assert.assertEquals("127.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testGetIpAddr4() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getHeader("WL-Proxy-Client-IP"))
                .thenReturn("0:0:0:0:0:0:0:1");

        Assert.assertEquals("127.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testGetIpAddr5() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getHeader("X-Real-IP"))
                .thenReturn("0:0:0:0:0:0:0:1");

        Assert.assertEquals("127.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testGetIpAddr6() {
        HttpServletRequest httpServletRequest =
                PowerMockito.mock(HttpServletRequest.class);
        PowerMockito.when(httpServletRequest.getRemoteAddr())
                .thenReturn("10.0.0.1");

        Assert.assertEquals("10.0.0.1", IpUtil.getIpAddr(httpServletRequest));
    }

    @Test
    public void testInternalIp() {
        Assert.assertTrue(IpUtil.internalIp("10.0.0.1"));
        Assert.assertTrue(IpUtil.internalIp("127.0.0.1"));
        Assert.assertTrue(IpUtil.internalIp("172.17.0.1"));
        Assert.assertTrue(IpUtil.internalIp("192.168.1.1"));

        Assert.assertFalse(IpUtil.internalIp("172.0.0.1"));
        Assert.assertFalse(IpUtil.internalIp("224.0.0.1"));
        Assert.assertFalse(IpUtil.internalIp("240.0.0.1"));
    }

    @Test
    public void testIpv4ToByte() {
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("foo"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte(""));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("-10"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("-10.1"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("10.-1"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("-10.1.1"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("10.1.-1"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("-10.1.1.1"));
        Assert.assertArrayEquals(new byte[0], IpUtil.ipv4ToByte("10.1.1.1.1"));
        Assert.assertArrayEquals(new byte[]{0, 0, 0, 10},
                IpUtil.ipv4ToByte("10"));
        Assert.assertArrayEquals(new byte[]{10, 0, 0, 1},
                IpUtil.ipv4ToByte("10.1"));
        Assert.assertArrayEquals(new byte[]{10, 1, 0, 1},
                IpUtil.ipv4ToByte("10.1.1"));
        Assert.assertArrayEquals(new byte[]{10, 1, 1, 1},
                IpUtil.ipv4ToByte("10.1.1.1"));
    }
}
