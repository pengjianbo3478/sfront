package com.gl365.app.remote.settlement.ApiRequest;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/20.
 */
public class CalculateVcTxnCommond {
    private BigDecimal cashMoney;

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }
}
