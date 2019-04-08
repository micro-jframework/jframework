package com.github.neatlife.jframework.util;

import com.github.neatlife.jframework.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

/**
 * @author suxiaolin
 * @date 2019-03-21 08:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    private final static String QUEUE = "myqueue";

    @Test
    public void setGetObject() {
        Person person = new Person("小明", 22);
        RedisUtil.setCacheObject("person-xiaoming", person);
        Person personFromRedis = RedisUtil.getCacheObject("person-xiaoming");

        Assert.assertEquals(personFromRedis.getName(), person.getName());
        Assert.assertEquals(personFromRedis.getAge(), person.getAge());
    }

    @Test
    public void blpop() {
        while (true) {
            String message = RedisUtil.blpop(QUEUE, 30);
            if (ObjectUtils.isEmpty(message)) {
                System.out.println("没有收到消息");
            } else {
                System.out.println("收到消息，消息是: " + message);
            }
        }
    }

    @Test
    public void lpush() throws Exception {
        while (true) {
            String message = "message: " + System.currentTimeMillis();
            RedisUtil.lpush(QUEUE, message);
            System.out.println("push message: " + message);
            Thread.sleep(1000L);
        }
    }
}