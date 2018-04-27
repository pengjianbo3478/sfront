package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gl365.app.utils.BigDecimaluitl;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class MyMerchantTradeList {
    private LocalDate tradeTime;
    private BigDecimal tradeAmount;
    private BigDecimal myProfit;
    private String payId;

    @ApiModelProperty(value = "交易时间")
    public LocalDate getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDate tradeTime) {
        this.tradeTime = tradeTime;
    }
    @ApiModelProperty(value = "交易金额")
    public String getTradeAmount() {
        return BigDecimaluitl.setScaleStr(tradeAmount);
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
    @ApiModelProperty(value = "我的分润")
    public String getMyProfit() {
        return BigDecimaluitl.setScaleStr(myProfit);
    }

    public void setMyProfit(BigDecimal myProfit) {
        this.myProfit = myProfit;
    }
    @ApiModelProperty(value = "流水号")
    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
