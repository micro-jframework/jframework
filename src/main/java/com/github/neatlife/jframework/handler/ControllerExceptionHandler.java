package com.github.neatlife.jframework.handler;

import com.github.neatlife.jframework.exception.BusinessRuntimeException;
import com.github.neatlife.jframework.http.HttpCode;
import com.github.neatlife.jframework.http.Response;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author suxiaolin
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler({Exception.class})
    public String handleException(HttpServletRequest request, Exception e) throws Exception {
        log.error("Request URL : {} , Exception : {}", request.getRequestURL(), e.getMessage());

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        return e.getMessage();
    }

    @ExceptionHandler({BusinessRuntimeException.class})
    @ResponseBody
    public Response handlerBusinessException(BusinessRuntimeException ex) {
        return new Response<Map>(HttpCode.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), Maps.newHashMap());
    }

}
