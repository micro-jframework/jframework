package com.github.neatlife.jframework.fundation.http;

import com.github.neatlife.jframework.fundation.util.JsonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 响应模型，用于统一请求响应处理
 * <p>
 * 模型由三个属性组成：
 * code：响应编码，同HTTP状态码，200表示成功
 * message：错误描述，当code不为200时用于描述错误信息
 * body：返回的实际对象
 *
 * @param <E> 实际对象类型
 * @author suxiaolin
 * @date 2019-03-21 13:05
 */
public class Response<E> implements Serializable {

    public static final String FLAG_CODE = "code";
    public static final String FLAG_BODY = "body";
    public static final String FLAG_MESSAGE = "message";

    /**
     * 响应编码，同HTTP状态码，200表示成功
     */
    private String code;
    /**
     * 错误描述，当code不为200时用于描述错误信息
     */
    private String message;
    /**
     * 返回的实际对象
     */
    private E body;

    public Response() {
    }

    public Response(String code, String message, E body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    /**
     * 返回成功
     *
     * @param body 实际对象
     */
    public static <E> Response<E> success(E body) {
        return new Response<>(HttpCode.SUCCESS.toString(), "", body);
    }

    /**
     * 返回未找到资源的错误，如数据记录不存在
     *
     * @param message 错误描述
     */
    public static <E> Response<E> notFound(String message) {
        return new Response<>(HttpCode.NOT_FOUND.toString(), message, null);
    }

    /**
     * 返回资源冲突的错误，如密码错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> conflict(String message) {
        return new Response<>(HttpCode.CONFLICT.toString(), message, null);
    }

    /**
     * 返回资源被锁定的错误，如当前用户被禁用
     *
     * @param message 错误描述
     */
    public static <E> Response<E> locked(String message) {
        return new Response<>(HttpCode.LOCKED.toString(), message, null);
    }

    /**
     * 返回请求格式的错误，如json不合法
     *
     * @param message 错误描述
     */
    public static <E> Response<E> unsupportedMediaType(String message) {
        return new Response<>(HttpCode.UNSUPPORTED_MEDIA_TYPE.toString(), message, null);
    }

    /**
     * 返回请求参数的错误，如缺少参数
     *
     * @param message 错误描述
     */
    public static <E> Response<E> badRequest(String message) {
        return new Response<>(HttpCode.BAD_REQUEST.toString(), message, null);
    }

    /**
     * 返回请求拒绝的错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> forbidden(String message) {
        return new Response<>(HttpCode.FORBIDDEN.toString(), message, null);
    }

    /**
     * 返回未认证的错误，如资源没有权限访问
     *
     * @param message 错误描述
     */
    public static <E> Response<E> unAuthorized(String message) {
        return new Response<>(HttpCode.UNAUTHORIZED.toString(), message, null);
    }

    /**
     * 返回服务器的错误，如捕捉到执行异常
     *
     * @param message 错误描述
     */
    public static <E> Response<E> serverError(String message) {
        return new Response<>(HttpCode.INTERNAL_SERVER_ERROR.toString(), message, null);
    }

    /**
     * 返回方法未实现的错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> notImplemented(String message) {
        return new Response<>(HttpCode.NOT_IMPLEMENTED.toString(), message, null);
    }

    /**
     * 返回服务不可用的错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> serverUnavailable(String message) {
        return new Response<>(HttpCode.SERVICE_UNAVAILABLE.toString(), message, null);
    }

    /**
     * 返回未知错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> unknown(String message) {
        return new Response<>(HttpCode.UNKNOWN.toString(), message, null);
    }

    /**
     * 返回自定义错误
     *
     * @param message 错误描述
     */
    public static <E> Response<E> customFail(String code, String message) {
        return new Response<>(code, message, null);
    }

    /**
     * 错误转换，解决java泛型继承问题
     *
     * @param response 原响应对象
     * @return 转换后的响应对象
     */
    public static <E> Response<E> error(Response<?> response) {
        return new Response<>(response.getCode(), response.getMessage(), null);
    }

    /**
     * 响应body转换，将body类型A转换成类型B
     *
     * @param resp      源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<E> generic(String resp, Class<E> bodyClazz) {
        return generic(JsonUtil.toObject(resp, Response.class), bodyClazz);
    }

    /**
     * 响应body转换，将body类型A转换成类型B
     *
     * @param response  源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<E> generic(Response response, Class<E> bodyClazz) {
        E body = null;
        if (response.ok() && response.getBody() != null) {
            body = JsonUtil.toObject(response.getBody(), bodyClazz);
        }
        return new Response<>(response.getCode(), response.getMessage(), body);
    }

    /**
     * 响应body转换(列表)，将body类型A转换成类型B
     *
     * @param resp      源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<List<E>> genericList(String resp, Class<E> bodyClazz) {
        return genericList(JsonUtil.toObject(resp, Response.class), bodyClazz);
    }

    /**
     * 响应body转换(列表)，将body类型A转换成类型B
     *
     * @param response  源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<List<E>> genericList(Response response, Class<E> bodyClazz) {
        List<E> body = null;
        if (response.ok() && response.getBody() != null) {
            body = JsonUtil.toList(response.getBody(), bodyClazz);
        }
        return new Response<>(response.getCode(), response.getMessage(), body);
    }

    /**
     * 响应body转换(Set)，将body类型A转换成类型B
     *
     * @param resp      源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<Set<E>> genericSet(String resp, Class<E> bodyClazz) {
        return genericSet(JsonUtil.toObject(resp, Response.class), bodyClazz);
    }

    /**
     * 响应body转换(Set)，将body类型A转换成类型B
     *
     * @param response  源响应
     * @param bodyClazz 目标body类型
     * @return 目标响应
     */
    public static <E> Response<Set<E>> genericSet(Response response, Class<E> bodyClazz) {
        Set<E> body = null;
        if (response.ok() && response.getBody() != null) {
            body = JsonUtil.toSet(response.getBody(), bodyClazz);
        }
        return new Response<>(response.getCode(), response.getMessage(), body);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getBody() {
        return body;
    }

    public void setBody(E body) {
        this.body = body;
    }

    /**
     * 返回是否成功
     */
    public boolean ok() {
        return Objects.equals(this.code, HttpCode.SUCCESS.toString());
    }

    /**
     * 声明返回需要降级
     */
    public Response fallback() {
        if (true) {
            throw new FallbackException(this);
        }
        // 使用返回值是为了保持结构统一
        return this;
    }

    public static class FallbackException extends RuntimeException {

        public FallbackException(Response<?> response) {
            super(JsonUtil.toJsonString(response));
        }

    }

}
