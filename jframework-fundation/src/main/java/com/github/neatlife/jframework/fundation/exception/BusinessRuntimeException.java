package com.github.neatlife.jframework.fundation.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author suxiaolin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessRuntimeException extends RuntimeException {
    private String description;
    private Integer code;

    public BusinessRuntimeException(Integer code, String description) {
        super(description);
        this.description = description;
        this.code = code;
    }

    public BusinessRuntimeException(Integer code, String description, Throwable e) {
        super(description, e);
        this.description = description;
        this.code = code;
    }

    public BusinessRuntimeException(IBusinessExceptionEnum iBusinessExceptionEnum) {
        super(iBusinessExceptionEnum.getDescription());
        this.description = iBusinessExceptionEnum.getDescription();
        this.code = iBusinessExceptionEnum.getCode();
    }
}
