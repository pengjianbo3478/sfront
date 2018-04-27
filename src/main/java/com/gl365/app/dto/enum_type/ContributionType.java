package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Created by wangmeihua on 2017/6/22.
 */
public enum ContributionType implements ValuedEnum<String> {
    COUNTY("1"),
    MERCHANT("2"),
    PERSON("3"),
    USER("4")
    ;

    private final String value;
    ContributionType(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}
