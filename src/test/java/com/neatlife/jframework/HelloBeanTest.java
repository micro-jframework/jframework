package com.neatlife.jframework;

import com.neatlife.jframework.config.HelloBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloBeanTest {

    @Autowired
    private HelloBean helloBean;

    @Test
    public void test() {
        System.out.println(helloBean);
        Assert.assertTrue(helloBean.getName() instanceof String);
    }
}