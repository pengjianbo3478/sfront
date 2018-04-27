package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Created by ryan on 2017/6/20.
 */
public enum PartnerType implements ValuedEnum<Integer> {

    /**
     * 平台（实际业务中不存在此类型代理）
     */
    PLATFORM(0),

    /**
     * 商户
     */
    MERCHANT(1),

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
     * 会员用户
     */
    USER(6),

    /**
     * 企业协会
     */
    ASSOCIATION(7)
    ;

    private final int value;

    PartnerType(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
