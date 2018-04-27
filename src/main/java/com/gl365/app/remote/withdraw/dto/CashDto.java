package com.gl365.app.remote.withdraw.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class CashDto {

    @ApiModelProperty("金额")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
