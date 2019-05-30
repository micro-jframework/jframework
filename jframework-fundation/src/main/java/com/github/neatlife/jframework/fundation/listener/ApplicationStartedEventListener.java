package com.github.neatlife.jframework.fundation.listener;

import com.github.neatlife.jframework.fundation.config.JFrameworkConfig;
import com.github.neatlife.jframework.fundation.util.DingTalkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author suxiaolin
 * @date 2019-03-21 08:37
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private final JFrameworkConfig jFrameworkConfig;

    @Autowired
    public ApplicationStartedEventListener(JFrameworkConfig jFrameworkConfig) {
        this.jFrameworkConfig = jFrameworkConfig;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        DingTalkUtil.setDdUrl(jFrameworkConfig.getNotification().getDingTalkUrl());
    }
}
