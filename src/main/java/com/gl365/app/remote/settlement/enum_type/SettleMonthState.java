package com.gl365.app.remote.settlement.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Created by wangmeihua on 2017/6/19.
 */
public enum SettleMonthState implements ValuedEnum<String> {
    /**
     * 待处理(待受理)
     */
    PENDING("0"),
    /**
     * 已审核(已受理)
     */
    AUDITED("1"),
    /**
     * 代付导出(处理中)
     */
    PROCESSING("2"),
    /**
     * 代付完成(结算完成)
     */
    COMPLETE("3"),
    /**
     * 代付失败(结算失败)
     */
    FIALURE("4");


    private final String value;

    SettleMonthState(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
