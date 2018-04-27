package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 售后服务状态
 * @author Chen, Zhuhui
 */
public enum CustomerServiceRequestStatus implements ValuedEnum<Integer> {

    /**
     * 待平台处理
     */
    PENDING_PLATFORM(10),

    /**
     * 待代理商处理
     */
    PENDING_AGENT(20),

    /**
     * 待业务员处理
     */
    PENDING_PERSONAL_AGENT(30),

    /**
     * 已完成
     */
    DONE(40),
    ;

    private final int value;

    CustomerServiceRequestStatus(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
