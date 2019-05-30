package com.github.neatlife.jframework.fundation.request;

import com.github.neatlife.jframework.fundation.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestToPoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestToPo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType();
        Map<String, String[]> requestParameterMap = webRequest.getParameterMap();
        if (MultiValueMap.class.isAssignableFrom(parameterType)) {
            MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>(requestParameterMap.size());
            requestParameterMap.forEach((key, values) -> {
                for (String value : values) {
                    linkedMultiValueMap.add(key, value);
                }
            });
            return linkedMultiValueMap;
        } else {
            Map<String, String> stringStringLinkedHashMap = new LinkedHashMap<>(requestParameterMap.size());
            requestParameterMap.forEach((key, values) -> {
                if (values.length > 0) {
                    stringStringLinkedHashMap.put(key, values[0]);
                }
            });
            return JsonUtil.toObject(stringStringLinkedHashMap, parameterType);
        }
    }
}
