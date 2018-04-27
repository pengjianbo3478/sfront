package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 代理状态枚举
 * @author Chen, Zhuhui
 */
public enum AgentStatus implements ValuedEnum<Integer> {

    /**
     * 待审核
     */
    PENDING(1),

    /**
     * 已审核
     */
    APPROVED(2),
    
    /**
     * 审核不通过
     */
    UNPASS(3),
    
    /**
     * 注销
     */
    QUIT(4)
    
    ;

    private final int value;

    AgentStatus(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
