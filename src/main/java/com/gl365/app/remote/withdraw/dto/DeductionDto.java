package com.gl365.app.remote.withdraw.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class DeductionDto extends  BaseDto{

    @ApiModelProperty("手续费百分比")
    private String feePercent;

    @ApiModelProperty("扣税费百分比")
    private BigDecimal taxPercent;

    public String getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(String feePercent) {
        this.feePercent = feePercent;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }
}
