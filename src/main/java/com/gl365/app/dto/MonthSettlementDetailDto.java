package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class MonthSettlementDetailDto{
    private String bankAccountName;
    private String bankAccountNo;
    private LocalDateTime payTime;

    @ApiModelProperty(value = "收款人")
    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
    @ApiModelProperty(value = "收款账户")
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }
    @ApiModelProperty(value = "汇入时间")
    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
}
