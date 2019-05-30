package com.github.neatlife.jframework.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends JframeworkApplicationTests {

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