package com.neatlife.jframework.logback;

import ch.qos.logback.classic.html.DefaultThrowableRenderer;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.html.IThrowableRenderer;

public class CustomHTMLLayout extends HTMLLayout {

    static final String DEFAULT_CONVERSION_PATTERN = "%date%thread%level%logger%mdc%msg";

    IThrowableRenderer<ILoggingEvent> throwableRenderer;

    public CustomHTMLLayout() {
        pattern = DEFAULT_CONVERSION_PATTERN;
        throwableRenderer = new DefaultThrowableRenderer();
        cssBuilder = new CustomCssBuilder();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        String content = super.doLayout(event);
        if (content.contains("com.neatlife")) {
            content = content.replace("<tr class=\"", "<tr class=\"bg-pink ");
        }
        return content;
    }
}

