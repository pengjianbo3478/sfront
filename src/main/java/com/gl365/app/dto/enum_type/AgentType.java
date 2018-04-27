package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 代理类型枚举
 * @author Chen, Zhuhui
 */
public enum AgentType implements ValuedEnum<Integer> {

    /**
     * 平台（实际业务中不存在此类型代理）
     */
    PLATFORM(0),

    /**
     * 市级代理
     */
    CITY(2),

    /**
     * 县级代理
     */
    COUNTY(3),

    /**
     * 业务员
     */
    PERSONAL(4),

    /**
     * 企业协会
     */
    ASSOCIATION(7)
    ;

    private final int value;

    AgentType(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
