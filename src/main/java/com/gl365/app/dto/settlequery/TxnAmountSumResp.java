package com.gl365.app.dto.settlequery;

import java.math.BigDecimal;

import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

public class TxnAmountSumResp {

	@ApiModelProperty("交易总金额")
	private BigDecimal sumTxnAmount;

	@ApiModelProperty("对象提成总金额")
	private BigDecimal sumOwnerProfitAmount;

	public String getSumTxnAmount() {
		return BigDecimaluitl.setScaleStr(sumTxnAmount);
	}

	public void setSumTxnAmount(BigDecimal sumTxnAmount) {
		this.sumTxnAmount = sumTxnAmount;
	}

	public String getSumOwnerProfitAmount() {
		return BigDecimaluitl.setScaleStr(sumOwnerProfitAmount);
	}

	public void setSumOwnerProfitAmount(BigDecimal sumOwnerProfitAmount) {
		this.sumOwnerProfitAmount = sumOwnerProfitAmount;
	}

	@Override
	public String toString() {
		return "TxnAmountSumResp [sumTxnAmount=" + sumTxnAmount + ", sumOwnerProfitAmount=" + sumOwnerProfitAmount
				+ "]";
	}

}
