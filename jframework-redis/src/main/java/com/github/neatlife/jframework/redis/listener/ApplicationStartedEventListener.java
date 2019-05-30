package com.github.neatlife.jframework.redis.listener;

import com.github.neatlife.jframework.redis.util.LockUtil;
import com.github.neatlife.jframework.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author suxiaolin
 * @date 2019-03-21 08:37
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private final RedisTemplate redisTemplate;

    @Autowired
    public ApplicationStartedEventListener(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        RedisUtil.setRedisTemplate(redisTemplate);
        LockUtil.setRedisTemplate(redisTemplate);
    }
}
