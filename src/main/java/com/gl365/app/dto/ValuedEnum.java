package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 带值枚举需要实现的接口
 * @author Chen, Zhuhui
 */
public interface ValuedEnum<ValueType> {

    @JsonValue
    ValueType value();

}
