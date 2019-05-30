package com.github.neatlife.jframework.fundation.request;

import com.github.neatlife.jframework.fundation.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestJsonToPoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJsonToPo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestJsonToPo parameterAnnotation = parameter.getParameterAnnotation(RequestJsonToPo.class);
        Objects.requireNonNull(parameterAnnotation);
        String value = parameterAnnotation.value();
        Class<?> clazz = parameter.getNestedParameterType();
        String jsonParameter = nativeWebRequest.getParameter(value);
        if (jsonParameter == null) {
            if (clazz.isAssignableFrom(List.class)) {
                return Lists.newArrayList();
            }
            return clazz.newInstance();
        }
        jsonParameter = URLDecoder.decode(jsonParameter, StandardCharsets.UTF_8.name());

        if (clazz.isAssignableFrom(List.class) || clazz.isAssignableFrom(ArrayList.class)) {
            String typeName = ((sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl) parameter.getGenericParameterType()).getActualTypeArguments()[0].getTypeName();
            Class<?> aClass = Class.forName(typeName);
            return JsonUtil.toList(jsonParameter, aClass);
        } else {
            return JsonUtil.toObject(jsonParameter, clazz);
        }
    }
}
