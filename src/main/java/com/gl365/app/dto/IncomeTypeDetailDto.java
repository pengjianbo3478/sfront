package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/13.
 */
public class IncomeTypeDetailDto {
    private BigDecimal devMerchantFee;
    private BigDecimal devCustomerFee;
    private BigDecimal serviceFee;

    @ApiModelProperty(value = "发展商户费")
    public BigDecimal getDevMerchantFee() {
        return devMerchantFee;
    }

    public void setDevMerchantFee(BigDecimal devMerchantFee) {
        this.devMerchantFee = devMerchantFee;
    }
    @ApiModelProperty(value = "发展用户费")
    public BigDecimal getDevCustomerFee() {
        return devCustomerFee;
    }

    public void setDevCustomerFee(BigDecimal devCustomerFee) {
        this.devCustomerFee = devCustomerFee;
    }
    @ApiModelProperty(value = "服务费")
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }
}
