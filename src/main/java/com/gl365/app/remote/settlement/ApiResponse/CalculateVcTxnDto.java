package com.gl365.app.remote.settlement.ApiResponse;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/20.
 */
public class CalculateVcTxnDto {
    private BigDecimal cashMoney ;
    private BigDecimal feeMoney;
    private BigDecimal taxMoney;
    private BigDecimal realMoney;

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public BigDecimal getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(BigDecimal feeMoney) {
        this.feeMoney = feeMoney;
    }

    public BigDecimal getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
    }

    public BigDecimal getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(BigDecimal realMoney) {
        this.realMoney = realMoney;
    }
}
