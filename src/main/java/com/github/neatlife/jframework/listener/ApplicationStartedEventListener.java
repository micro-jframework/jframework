package com.github.neatlife.jframework.listener;

import com.github.neatlife.jframework.JFrameworkConfig;
import com.github.neatlife.jframework.logback.RedisAppender;
import com.github.neatlife.jframework.util.DdUtil;
import com.github.neatlife.jframework.util.LockUtil;
import com.github.neatlife.jframework.util.RedisUtil;
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
    private final JFrameworkConfig jFrameworkConfig;

    @Autowired
    public ApplicationStartedEventListener(RedisTemplate redisTemplate, JFrameworkConfig jFrameworkConfig) {
        this.redisTemplate = redisTemplate;
        this.jFrameworkConfig = jFrameworkConfig;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        RedisUtil.setRedisTemplate(redisTemplate);
        LockUtil.setRedisTemplate(redisTemplate);
        RedisAppender.setEnableNoRepeat(jFrameworkConfig.getMail().getEnableNoRepeat());
        RedisAppender.setRepeatInterval(jFrameworkConfig.getMail().getRepeatInterval());
        DdUtil.setDdUrl(jFrameworkConfig.getNotification().getDdUrl());
    }
}
