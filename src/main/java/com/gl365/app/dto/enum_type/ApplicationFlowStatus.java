package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public enum ApplicationFlowStatus implements ValuedEnum<Integer> {
    /**
     * 申请
     */
    APPLY(0),
    /**
     * 审核
     */
    AUDIT(1),
    /**
     * 通过
     */
    PASS(2),
    /**
     * 拒绝
     */
    REFUSE(3),
    /**
     * 撤回
     */
    WITHDRAW(4)
    ;

    private final Integer value;

    ApplicationFlowStatus(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return this.value;
    }
}
