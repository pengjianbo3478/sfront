package com.gl365.app.dto;

import java.util.List;

public class ApplicationPosDto {

	private String applicationId;

	/**
	 * 申请人Id
	 */
	private String agentId;

	/**
	 * 共申请POS机数量
	 */
	private String count;
	
	/**
	 * 线下商户号
	 */
	private String merchantSn;
	
	/**
	 * POS详情
	 */
	private List<ApplicationPosDetilDto> posDetilList;
	
	private List<MerchantPayAccount> merchantPayAccounts;
	
	private List<MerchantBarCode> barCodeList;

	public List<MerchantBarCode> getBarCodeList() {
		return barCodeList;
	}

	public void setBarCodeList(List<MerchantBarCode> barCodeList) {
		this.barCodeList = barCodeList;
	}

	public List<MerchantPayAccount> getMerchantPayAccounts() {
		return merchantPayAccounts;
	}

	public void setMerchantPayAccounts(List<MerchantPayAccount> merchantPayAccounts) {
		this.merchantPayAccounts = merchantPayAccounts;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public String getMerchantSn() {
		return merchantSn;
	}

	public void setMerchantSn(String merchantSn) {
		this.merchantSn = merchantSn;
	}

	public List<ApplicationPosDetilDto> getPosDetilList() {
		return posDetilList;
	}

	public void setPosDetilList(List<ApplicationPosDetilDto> posDetilList) {
		this.posDetilList = posDetilList;
	}
}
