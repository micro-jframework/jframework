package com.github.neatlife.jframework;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suxiaolin
 */
@SpringBootApplication
@EnableApolloConfig
public class JframeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(JframeworkApplication.class, args);
    }

}

