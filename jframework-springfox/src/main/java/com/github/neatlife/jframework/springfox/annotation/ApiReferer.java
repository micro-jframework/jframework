package com.github.neatlife.jframework.springfox.annotation;

import javax.validation.constraints.Null;
import java.lang.annotation.*;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiReferer {

    Class<?> value() default Null.class;

}

