package com.gl365.app.remote.profit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/22.
 */
public class YesterdayListDto {

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("交易金额")
    private BigDecimal txnAmount;

    @ApiModelProperty("对象提成金额")
    private BigDecimal ownerProfitAmount;

    @ApiModelProperty("时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @ApiModelProperty("清算时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settleTime;
    
    private String settleFlag;

    public YesterdayListDto(String merchantName, BigDecimal txnAmount, BigDecimal ownerProfitAmount, LocalDateTime createTime, LocalDate settleTime,String settleFlag) {
        this.merchantName = merchantName;
        this.txnAmount = txnAmount;
        this.ownerProfitAmount = ownerProfitAmount;
        this.createTime = createTime;
        this.settleTime = settleTime;
        this.settleFlag = settleFlag;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTxnAmount() {
        return BigDecimaluitl.setScaleStr(txnAmount);
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getOwnerProfitAmount() {
        return BigDecimaluitl.setScaleStr(ownerProfitAmount);
    }

    public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
        this.ownerProfitAmount = ownerProfitAmount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

	public LocalDate getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(LocalDate settleTime) {
		this.settleTime = settleTime;
	}
	
	@ApiModelProperty(value = "清算标志:Y：已清算,N：未清算")
	public String getSettleFlag() {
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}
}
