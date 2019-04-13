package com.github.neatlife.jframework.util;

import com.github.neatlife.jframework.JframeworkApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpUtilTest extends JframeworkApplicationTests {

    @Test
    public void testGet() throws IOException {
        Assert.assertNotNull(HttpUtil.get("https://bing.com"));
    }

}