package com.github.neatlife.jframework.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilTest {

    @Test
    public void testGet() throws IOException {
        Assert.assertNotNull(HttpUtil.get("https://bing.com"));
    }

}