package com.gl365.app.dto.merchant;

import io.swagger.annotations.ApiModelProperty;

public class NoBenefitMerchantDto {

	@ApiModelProperty(value = "申请人", hidden = true)
	private String agentId;

	@ApiModelProperty(value = "商户Id")
	private String merchantId;

	@ApiModelProperty(value = "给乐商户号")
	private String geileMerchantSn;

	@ApiModelProperty(value = "申请原因", required = true)
	private String reason;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getGeileMerchantSn() {
		return geileMerchantSn;
	}

	public void setGeileMerchantSn(String geileMerchantSn) {
		this.geileMerchantSn = geileMerchantSn;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "NoBenefitMerchantDto [agentId=" + agentId + ", merchantId=" + merchantId + ", geileMerchantSn="
				+ geileMerchantSn + ", reason=" + reason + "]";
	}
	
	
}
