package com.neatlife.jframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suxiaolin
 */
@Configuration
public class TestBean {

    @Bean
    public HelloBean helloBean() {
        return new HelloBean();
    }

}
