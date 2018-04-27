package com.gl365.app.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.gl365.app.dto.ValuedEnum;

/**
 * @author Chen, Zhuhui
 */
public class ValuedEnumDeserializerModifier extends BeanDeserializerModifier {

    @Override
    public JsonDeserializer<?> modifyEnumDeserializer(DeserializationConfig config, JavaType type, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        Class<?> clazz = type.getRawClass();
        if (ValuedEnum.class.isAssignableFrom(clazz)) {
            return new ValuedEnumDeserializer(clazz);
        } else {
            return super.modifyEnumDeserializer(config, type, beanDesc, deserializer);
        }
    }
}
