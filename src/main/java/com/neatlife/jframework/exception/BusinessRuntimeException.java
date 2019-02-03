package com.neatlife.jframework.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author suxiaolin
 */
@Data()
@EqualsAndHashCode(callSuper = true)
public class BusinessRuntimeException extends RuntimeException {
    private String msg;
    private StatusCode statusCode;

    public BusinessRuntimeException(StatusCode statusCode, String msg) {
        super(msg);
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public BusinessRuntimeException(StatusCode statusCode, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
