package com.gl365.app.remote.settlement.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Created by wangmeihua on 2017/6/16.
 */
public enum ProfitType implements ValuedEnum<String>{

    /**
     * 市代，县代，业务员 直接发展商户发展费
     */
    AGENT_DEVELOP_FEE("02"),
    /**
     *业务员发展商户代理费
     */
    PERSONAL_SERVICE_FEE("05"),

    /**
     *市代或县代代理费（09市代县代都可能：如果区域是县代，县级有市级，那么09就是县，10就是市
     *     如果区域只有市，没有县，那么09就是市，此时10就没有数据 ）
     如果区域只有市，没有县，那么09就是市）
     */
    CITY_OR_COUNT_SERVICE_FEE("09"),

    /**
     * 市代代理费
     */
    CITY_SERVICE_FEE("10");
    private final String value;
    ProfitType(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
