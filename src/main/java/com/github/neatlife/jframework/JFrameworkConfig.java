package com.github.neatlife.jframework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author suxiaolin
 * @date 2019-03-29 08:50
 */
@Data
@ConfigurationProperties(prefix = "jframework")
@Component
public class JFrameworkConfig {

    private Mail mail = new Mail();

    private Notification notification = new Notification();

    @Data
    public static class Notification {
        private String ddUrl;
    }

    @Data
    public static class Mail {
        private Boolean enableNoRepeat = false;
        private Integer repeatInterval = 600;
    }

}
