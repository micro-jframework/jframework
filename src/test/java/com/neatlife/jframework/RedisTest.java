package com.neatlife.jframework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("jframework", "hello");
        System.out.println("end...");
    }

    @Test
    public void testObjectSet() {
        Person person = new Person("小明", 22);
        redisTemplate.opsForValue().set("jframework-person", person);
        System.out.println("end...");
    }

    @Test
    public void testObjectGet() {
        Person person = (Person) redisTemplate.opsForValue().get("jframework-person");
        System.out.println("end...");
    }
}