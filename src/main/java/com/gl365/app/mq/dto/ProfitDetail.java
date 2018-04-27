package com.gl365.app.mq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 2.43 S端提成消息推送MQ(1.0.3) 直接发展商户提成部分信息
 * @author lch 2017年9月4日
 *
 */
public class ProfitDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 提成对象类型 1：省级运营商 2：市级运营商 3：县级运营商 4：业务员机构 5：联盟商家商户 6：员工,店长,会员 7：企业协会机构
	 * 8：积分机构 9：电子商城 10：积分商城 11：政府机构 12：银行机构 13：支付公司
	 */
	private String ownerType;

	/**
	 * 提成对象
	 */
	private String ownerId;
	
	/**
	 * 交易单号
	 */
	private String payId;
	
	/**
	 * 给乐商户号
	 */
	private String merchantNo;
	
	/**
	 * 商户名称	 
	 */
	private String merchantName;
	
	/**
	 * 交易类型
	 */
	private String transType;

	/**
	 * 支付时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;		

	/**
	 * 交易金额
	 */
	private BigDecimal txnAmount;
	
	/**
	 * 对象提成金额
	 */
	private BigDecimal ownerProfitAmount;

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

	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public BigDecimal getOwnerProfitAmount() {
		return ownerProfitAmount;
	}

	public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
		this.ownerProfitAmount = ownerProfitAmount;
	}

	@Override
	public String toString() {
		return "ProfitDetail [ownerType=" + ownerType + ", ownerId=" + ownerId + ", payId=" + payId + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", transType=" + transType + ", payTime=" + payTime
				+ ", txnAmount=" + txnAmount + ", ownerProfitAmount=" + ownerProfitAmount + "]";
	}
}
