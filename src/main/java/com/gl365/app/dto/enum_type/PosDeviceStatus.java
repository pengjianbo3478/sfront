package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * @author Chen, Zhuhui
 */
public enum PosDeviceStatus implements ValuedEnum<Integer> {

    /**
     * 支付公司审批通过
     */
    APPROVED(1),

    /**
     * 已绑定
     */
    BOUND(2),

    /**
     * 未绑定（已解绑）
     */
    UNBOUND(3);

    private final Integer value;

    PosDeviceStatus(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return this.value;
    }

}
