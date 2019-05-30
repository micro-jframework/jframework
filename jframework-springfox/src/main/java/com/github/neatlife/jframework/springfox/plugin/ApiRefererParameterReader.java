package com.github.neatlife.jframework.springfox.plugin;

import com.github.neatlife.jframework.springfox.annotation.ApiReferer;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Slf4j
class ApiRefererParameterReader implements ParameterBuilderPlugin {

    @Override
    public void apply(ParameterContext context) {
        ApiReferer apiReferer = context.getOperationContext().findAnnotation(ApiReferer.class).orNull();
        if (ObjectUtils.isEmpty(apiReferer)) {
            return;
        }
        String filedName = context.resolvedMethodParameter().defaultName().orNull();
        if (ObjectUtils.isEmpty(filedName)) {
            return;
        }
        try {
            String apimodelPropertyValue = apiReferer.value().getDeclaredField(filedName).getAnnotation(ApiModelProperty.class).value();
            context.parameterBuilder().description(apimodelPropertyValue);
        } catch (Exception e) {
            log.debug("apiReferer get filed error ", e);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

}
