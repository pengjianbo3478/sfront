package com.gl365.app.remote.settlement.ApiResponse;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/10.
 */
public class QueryDistrictMerResponse {
	private String merchantNo;
	private Long txnCount;

	private Long totalCount;
	private BigDecimal txnAmount;
	private BigDecimal totalProfitFee;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
		if (txnCount != null) {
			this.totalCount = txnCount;
		}
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		if (totalCount != null) {
			this.txnCount = totalCount;
		}
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public BigDecimal getTotalProfitFee() {
		return totalProfitFee;
	}

	public void setTotalProfitFee(BigDecimal totalProfitFee) {
		this.totalProfitFee = totalProfitFee;
	}

}
