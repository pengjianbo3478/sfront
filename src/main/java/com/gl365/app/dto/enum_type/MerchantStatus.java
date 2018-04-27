package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * @author Chen, Zhuhui
 */
public enum MerchantStatus implements ValuedEnum<Integer> {

    /**
     * 新商户
     */
    NEW(1),

    /**
     * 已进件至少一次
     */
    HAS_EVER_APPLIED(2),

    /**
     * 审核失败
     */
    AUDIT_FAIL(3);

    private final int value;

    MerchantStatus(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
