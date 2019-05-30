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
public class DoubleSpecifySerialize extends JsonSerializer<Double> implements ContextualSerializer {

    private int decimalNumber;

    public DoubleSpecifySerialize() {
        this(2);
    }

    public DoubleSpecifySerialize(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }
        gen.writeNumber(BigDecimal.valueOf(value).setScale(decimalNumber, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toString());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // 为空直接跳过
        if (property == null) {
            return prov.findNullValueSerializer(property);
        }
        // 非 Double 类直接跳过
        if (Objects.equals(property.getType().getRawClass(), Double.class)) {
            DoubleSpecify doubleSpecify = property.getAnnotation(DoubleSpecify.class);
            if (doubleSpecify == null) {
                doubleSpecify = property.getContextAnnotation(DoubleSpecify.class);
            }
            // 如果能得到注解，就将注解的 value 传入 DoubleSpecifySerialize
            if (doubleSpecify != null) {
                return new DoubleSpecifySerialize(doubleSpecify.value());
            }
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
