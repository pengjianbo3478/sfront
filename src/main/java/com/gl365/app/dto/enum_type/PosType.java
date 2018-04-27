package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * @author Chen, Zhuhui
 */
public enum PosType implements ValuedEnum<Integer> {

    /**
     * 移动机
     */
    MOBILE(1),

    /**
     * 固定机
     */
    IMMOBILE(2),

    /**
     * 智能机
     */
    SMART(3),

    /**
     * 扫付码
     */
    QRCODE(9);

    private final Integer value;

    PosType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return this.value;
    }

}
