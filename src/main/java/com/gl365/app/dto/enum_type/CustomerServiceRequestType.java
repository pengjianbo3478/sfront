package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 售后服务类型
 * @author Chen, Zhuhui
 */
public enum CustomerServiceRequestType implements ValuedEnum<Integer> {

    /**
     * 法人账户变更
     */
    CHANGE_LEGAL_PERSON_ACCOUNT(10),

    /**
     * 非法人账户变更
     */
    CHANGE_NON_LEGAL_PERSON_ACCOUNT(20),

    /**
     * 机具故障
     */
    POS_MALFUNCTION(30),

    /**
     * 退机
     */
    POS_RETURN(40)
    ;

    private final int value;

    CustomerServiceRequestType(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
