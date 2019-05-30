package com.github.neatlife.jframework.fundation.http;

/**
 * @author suxiaolin
 * @date 2019-03-21 13:07
 */
public enum HttpCode {
    /**
     * 常用的http状态码
     */
    SUCCESS("200"),
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    FORBIDDEN("403"),
    NOT_FOUND("404"),
    CONFLICT("409"),
    LOCKED("423"),
    UNSUPPORTED_MEDIA_TYPE("415"),
    INTERNAL_SERVER_ERROR("500"),
    NOT_IMPLEMENTED("501"),
    SERVICE_UNAVAILABLE("503"),
    UNKNOWN("-1");

    private String code;

    HttpCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
