package com.gl365.app.remote.profit.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class ProfitConfigDto {

    @ApiModelProperty("提成方案名字")
    private String name;

    @ApiModelProperty("提成方案数值")
    private BigDecimal value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
