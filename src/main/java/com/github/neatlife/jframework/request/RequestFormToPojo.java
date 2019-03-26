package com.github.neatlife.jframework.request;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestFormToPojo {
    boolean validator() default true;
}
