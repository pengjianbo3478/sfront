package com.gl365.app.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gl365.app.dto.ValuedEnum;

import java.io.IOException;

public class ValuedEnumSerializer extends JsonSerializer<ValuedEnum> {
    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(ValuedEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (value.value() instanceof Number) {
            gen.writeNumber(((Number) value.value()).longValue());
        } else {
            gen.writeString(value.value().toString());
        }
    }

    @Override
    public Class<ValuedEnum> handledType() {
        return ValuedEnum.class;
    }
}
