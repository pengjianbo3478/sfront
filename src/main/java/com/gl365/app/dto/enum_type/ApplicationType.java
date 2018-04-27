package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 申请类型
 * @author Chen, Zhuhui
 */
public enum ApplicationType implements ValuedEnum<Integer> {

    /**
     * 代理商申请。由于能申请的只有县级代理商，所以实际上类型就是县级代理商申请
     */
    COUNTY_AGENT(10),

    /**
     * 业务员申请
     */
    PERSONAL_AGENT(20),

    /**
     * 商户申请（机具申请）
     */
    MERCHANT(30),

    /**
     * POS装机申请
     */
    POS_INSTALLMENT(40),

    /**
     * 返利变动申请
     */
    NO_BENEFIT(50);

    private final Integer value;

    ApplicationType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return this.value;
    }

}
