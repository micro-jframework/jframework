package com.github.neatlife.jframework.fundation.exception;

/**
 * @author suxiaolin
 * @date 2019-03-25 23:55
 */
public interface IBusinessExceptionEnum {
    /**
     * 获取错误编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取错误描述
     *
     * @return
     */
    String getDescription();
}