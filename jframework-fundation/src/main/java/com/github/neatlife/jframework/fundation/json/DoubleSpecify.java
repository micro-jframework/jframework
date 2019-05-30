package com.github.neatlife.jframework.fundation.json;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author suxiaolin
 * @date 2019-03-20 08:52
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DoubleSpecifySerialize.class)
public @interface DoubleSpecify {
    /**
     * 小数位数，默认2位小数
     *
     * @return
     */
    int value() default 2;
}
