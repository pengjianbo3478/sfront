package com.gl365.app.remote.withdraw.dto;

import java.math.BigDecimal;

import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class BalanceAndTotalDto {

	@ApiModelProperty("剩余金额")
	private String balance;

	@ApiModelProperty("总累计提现金额")
	private String totalRequestCash;

	@ApiModelProperty(value = "累积分润", example = "1000")
	private BigDecimal totalProfitFee;

	@ApiModelProperty(value = "分润款余额", example = "1000")
	private BigDecimal balanceAmount;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTotalRequestCash() {
		return totalRequestCash;
	}

	public void setTotalRequestCash(String totalRequestCash) {
		this.totalRequestCash = totalRequestCash;
	}

	public String getTotalProfitFee() {
		return BigDecimaluitl.setScaleStr(totalProfitFee);
	}

	public void setTotalProfitFee(BigDecimal totalProfitFee) {
		this.totalProfitFee = totalProfitFee;
	}

	public String getBalanceAmount() {
		return BigDecimaluitl.setScaleStr(balanceAmount);
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	
}
