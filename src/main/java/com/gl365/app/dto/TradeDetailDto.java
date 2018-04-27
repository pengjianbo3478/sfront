package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

/**
 * Created by wangmeihua on 2017/6/16.
 */
public class TradeDetailDto {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tradeTime;
    private BigDecimal tradeAmount;
    private BigDecimal myProfit;

    @ApiModelProperty(value = "交易时间")
    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }
    @ApiModelProperty(value = "交易金额")
    public String getTradeAmount() {
        return BigDecimaluitl.setScaleStr(tradeAmount);
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
    @ApiModelProperty(value = "我的利润")
    public String getMyProfit() {
        return BigDecimaluitl.setScaleStr(myProfit);
    }

    public void setMyProfit(BigDecimal myProfit) {
        this.myProfit = myProfit;
    }
}
