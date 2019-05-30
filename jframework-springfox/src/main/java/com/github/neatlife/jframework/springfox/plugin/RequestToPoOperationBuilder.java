package com.github.neatlife.jframework.springfox.plugin;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.classmate.members.RawField;
import com.github.neatlife.jframework.fundation.request.RequestToPo;
import com.github.neatlife.jframework.fundation.util.CaseUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static springfox.documentation.schema.Types.typeNameFor;

/**
 * @author suxiaolin
 * @date 2019-04-01 19:55
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
@ConditionalOnBean(Docket.class)
@Slf4j
class RequestToPoOperationBuilder implements OperationBuilderPlugin {
    private static List<Parameter> addFieldsToDoc(Field classField, String prefix, Class<?> parentClass) {
        Class<?> itemClass = classField.getType();
        if (List.class.isAssignableFrom(classField.getType())) {
            ParameterizedType listType = (ParameterizedType) classField.getGenericType();
            itemClass = (Class<?>) listType.getActualTypeArguments()[0];
        }
        List<Parameter> requestMapParameters = Lists.newArrayList();
        for (Field field : itemClass.getDeclaredFields()) {
            String itemTypeName = typeNameFor(field.getType());
            if (!ObjectUtils.isEmpty(itemTypeName)) {
                requestMapParameters.add(genPrimaryTypeDoc(field, prefix, itemTypeName));
            } else {
                if (field.getClass().equals(parentClass)) {
                    requestMapParameters.add(genPrimaryTypeDoc(field, prefix, "String"));
                } else {
                    // 解析出这个类型的所有成员加到文档里
                    String subPrefix = ObjectUtils.isEmpty(prefix) ? prefix : prefix + ".";
                    requestMapParameters.addAll(addFieldsToDoc(field, subPrefix + CaseUtil.snakeCase(field.getName()), classField.getClass()));
                }
            }
        }
        return requestMapParameters;
    }

    private static Parameter genPrimaryTypeDoc(Field field, String prefix, String itemTypeName) {
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        String description = ObjectUtils.isEmpty(apiModelProperty) ? field.getName() : apiModelProperty.value();
        String name = CaseUtil.snakeCase(field.getName());
        boolean required = false;
        NotNull notNull = field.getAnnotation(NotNull.class);
        if (notNull != null) {
            required = true;
        }
        return new ParameterBuilder()
                .description(description)
                .type(new TypeResolver().resolve(field.getClass()))
                .name(ObjectUtils.isEmpty(prefix) ? name : prefix + "." + name)
                .parameterType("query")
                .parameterAccess("access")
                .required(required)
                .modelRef(new ModelRef(itemTypeName))
                .build();
    }

    @Override
    public void apply(OperationContext context) {
        context.operationBuilder().parameters(readParameters(context));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private List<Parameter> readParameters(final OperationContext context) {

        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        final List<Parameter> parameterList = new ArrayList<>();

        for (ResolvedMethodParameter methodParameter : methodParameters) {
            ResolvedType alternate = context.alternateFor(methodParameter.getParameterType());

            if (!shouldIgnore(methodParameter, alternate, context.getIgnorableParameterTypes()) && isRequestFormToPojo(methodParameter)) {
                readRequestFormToPojo(context, methodParameter);
            }
        }
        return parameterList.stream().filter(((Predicate<Parameter>) Parameter::isHidden).negate()).collect(Collectors.toList());
    }

    private void readRequestFormToPojo(OperationContext context, ResolvedMethodParameter methodParameter) {

        List<RawField> fields = methodParameter.getParameterType().getMemberFields();
        List<RawField> parentRawFields = methodParameter.getParameterType().getParentClass().getMemberFields();

        List<RawField> fieldList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(fields)) {
            fieldList.addAll(fields);
        }
        if (!CollectionUtils.isEmpty(parentRawFields)) {
            fieldList.addAll(parentRawFields);
        }
        List<Parameter> requestMapParameters = Lists.newArrayList();
        for (RawField rawField : fieldList) {
            String name = CaseUtil.snakeCase(rawField.getName());
            Class itemClass = rawField.getRawMember().getType();
            if (itemClass != null) {
                String itemTypeName = typeNameFor(itemClass);
                if (ObjectUtils.isEmpty(itemTypeName)) {
                    // 解析出这个类型的所有成员加到文档里
                    requestMapParameters.addAll(addFieldsToDoc(rawField.getRawMember(), name, null));
                } else {
                    requestMapParameters.add(genPrimaryTypeDoc(rawField.getRawMember(), "", itemTypeName));
                }
            }
        }

        try {
            OperationBuilder operationBuilder = context.operationBuilder();
            Field parametersField = context.operationBuilder().getClass().getDeclaredField("parameters");
            parametersField.setAccessible(true);
            parametersField.set(operationBuilder, requestMapParameters);
        } catch (Exception e) {
            log.debug("swagger RequestToPo error", e);
        }
    }

    private boolean shouldIgnore(
            final ResolvedMethodParameter parameter,
            ResolvedType resolvedParameterType,
            final Set<Class> ignorableParamTypes) {

        if (ignorableParamTypes.contains(resolvedParameterType.getErasedType())) {
            return true;
        }
        return ignorableParamTypes.stream()
                .filter(Annotation.class::isAssignableFrom)
                .anyMatch(parameter::hasParameterAnnotation);
    }

    private boolean isRequestFormToPojo(final ResolvedMethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestToPo.class);
    }
}



