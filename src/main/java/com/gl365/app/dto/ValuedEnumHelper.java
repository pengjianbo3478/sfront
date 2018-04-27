package com.gl365.app.dto;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 带单值的枚举类型的工具类
 * Created by Chen, Zhuhui on 2017/2/8.
 */
public class ValuedEnumHelper<EnumType extends Enum<EnumType> & ValuedEnum<? extends ValueType>, ValueType> {

    /**
     * 没有对应到值类型的枚举值时候抛出的异常
     */
    public static class NoSuchEnumWithValueException extends Exception {

        private static final long serialVersionUID = 1705033679905600263L;

        NoSuchEnumWithValueException(String message) {
            super(message);
        }
    }

    private Map<ValueType, EnumType> reverseMap;

    private final Class<EnumType> enumTypeClass;

    public ValuedEnumHelper(Class<EnumType> enumClazz) {
        this.enumTypeClass = enumClazz;
        EnumSet<? extends EnumType> allEnums = EnumSet.allOf(enumClazz);
        reverseMap = new HashMap<>();
        for (EnumType enumValue : allEnums) {
            reverseMap.put(enumValue.value(), enumValue);
        }
        reverseMap = Collections.unmodifiableMap(reverseMap);
    }

    public Enum<EnumType> enumForValue(ValueType value) throws NoSuchEnumWithValueException {
        Enum<EnumType> enumValue = reverseMap.get(value);
        if (enumValue == null) {
            throw new NoSuchEnumWithValueException(
                    String.format("无法找到对应值<%s>的枚举值（枚举类型：%s）", value.toString(), enumTypeClass.getName()));
        }
        return enumValue;
    }

    private static ConcurrentHashMap<Class<? extends Enum>, ValuedEnumHelper<? extends Enum, ?>> cachedValuedEnumHelpers = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <EnumType extends Enum<EnumType> & ValuedEnum<? extends ValueType>, ValueType>
    EnumType getEnumForValue(Class<EnumType> enumClazz, ValueType value) throws NoSuchEnumWithValueException {
        ValuedEnumHelper<EnumType, ValueType> helper = (ValuedEnumHelper<EnumType, ValueType>) cachedValuedEnumHelpers.computeIfAbsent(enumClazz, (clazz) -> new ValuedEnumHelper<>(enumClazz));
        return (EnumType) helper.enumForValue(value);
    }

    public static <EnumType extends Enum<EnumType> & ValuedEnum<? extends ValueType>, ValueType> Class<?> getValueType(Class<EnumType> enumClazz) {
        ParameterizedType superclass = (ParameterizedType) enumClazz.getGenericInterfaces()[0];
        Type valueType = superclass.getActualTypeArguments()[0];
        return (Class<?>) valueType;
    }
}
