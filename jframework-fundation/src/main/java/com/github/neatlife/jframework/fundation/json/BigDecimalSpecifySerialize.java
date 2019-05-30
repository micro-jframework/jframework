package com.github.neatlife.jframework.fundation.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author suxiaolin
 * @date 2019-03-20 08:52
 */
public class BigDecimalSpecifySerialize extends JsonSerializer<BigDecimal> implements ContextualSerializer {

    private int decimalNumber;

    public BigDecimalSpecifySerialize() {
        this(2);
    }

    public BigDecimalSpecifySerialize(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }
        gen.writeNumber(value.setScale(decimalNumber, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toString());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // 为空直接跳过
        if (property == null) {
            return prov.findNullValueSerializer(property);
        }
        // 非 BigDecimal 类直接跳过
        if (Objects.equals(property.getType().getRawClass(), BigDecimal.class)) {
            BigDecimalSpecify doubleSpecify = property.getAnnotation(BigDecimalSpecify.class);
            if (doubleSpecify == null) {
                doubleSpecify = property.getContextAnnotation(BigDecimalSpecify.class);
            }
            // 如果能得到注解，就将注解的 value 传入 BigDecimalSpecifySerialize
            if (doubleSpecify != null) {
                return new BigDecimalSpecifySerialize(doubleSpecify.value());
            }
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
