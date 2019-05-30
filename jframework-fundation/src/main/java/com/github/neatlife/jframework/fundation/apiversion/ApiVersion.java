package com.github.neatlife.jframework.fundation.apiversion;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /**
     * 版本号
     *
     * @return
     */
    int value();
}