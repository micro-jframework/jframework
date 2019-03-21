package com.neatlife.jframework.util;

import com.neatlife.jframework.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author suxiaolin
 * @date 2019-03-21 08:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Test
    public void setGetObject() {
        Person person = new Person("小明", 22);
        RedisUtil.setCacheObject("person-xiaoming", person);
        Person personFromRedis = RedisUtil.getCacheObject("person-xiaoming");

        Assert.assertEquals(personFromRedis.getName(), person.getName());
        Assert.assertEquals(personFromRedis.getAge(), person.getAge());
    }

}