package com.gl365.app.mq.dto;

import java.math.BigDecimal;

import com.gl365.app.utils.BigDecimaluitl;

public class ProfitNotifyDto {

	private String ownerType;// 提成对象类型
	private String ownerId;// 提成对象
	private String payId;// 给乐支付流水号
	private String merchantNo;// 给乐商户号
	private String merchantName;// 给乐商户名称
	private String transType;// 交易类型
	
	private String payTime;// 支付确认时间
	
	private BigDecimal txnAmount;// 交易金额
	private BigDecimal ownerProfitAmount;// 对象提成金额
	
	private String systemType;// 消息类型

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getTxnAmount() {
		return BigDecimaluitl.setScaleStr(txnAmount);
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getOwnerProfitAmount() {
		return BigDecimaluitl.setScaleStr(ownerProfitAmount, 4);
	}

	public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
		this.ownerProfitAmount = ownerProfitAmount;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		return "ProfitNotifyDto [ownerType=" + ownerType + ", ownerId=" + ownerId + ", payId=" + payId + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", transType=" + transType + ", payTime=" + payTime
				+ ", txnAmount=" + txnAmount + ", ownerProfitAmount=" + ownerProfitAmount + ", systemType=" + systemType
				+ "]";
	}



}
