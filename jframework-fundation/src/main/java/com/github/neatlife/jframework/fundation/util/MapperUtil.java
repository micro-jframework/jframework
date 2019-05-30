package com.github.neatlife.jframework.fundation.util;

import org.springframework.beans.BeanUtils;

/**
 * @author suxiaolin
 */
public class MapperUtil {

    public static <SOURCE, TARGET> TARGET to(SOURCE source, Class<TARGET> targetClass) {
        TARGET target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
