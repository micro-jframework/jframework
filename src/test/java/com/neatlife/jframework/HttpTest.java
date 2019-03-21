package com.neatlife.jframework;

import com.neatlife.jframework.util.$;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTest {

    @Test
    public void testGet() throws IOException {
        Assert.assertNotNull($.http.get("https://bing.com"));
    }

}