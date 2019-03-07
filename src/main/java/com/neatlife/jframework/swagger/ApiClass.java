package com.neatlife.jframework.swagger;

import javax.validation.constraints.Null;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiClass {

    Class<?> value() default Null.class;

}

