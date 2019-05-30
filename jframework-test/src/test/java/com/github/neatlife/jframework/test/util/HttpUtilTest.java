package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.HttpUtil;
import com.github.neatlife.jframework.test.JframeworkApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpUtilTest extends JframeworkApplicationTests {

    @Test
    public void testGet() throws IOException {
        Assert.assertNotNull(HttpUtil.get("https://bing.com"));
    }

}