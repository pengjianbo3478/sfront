package com.gl365.app.dto;

public class MerchantBasics {

	// 给乐商户号
	private String merchantNo;

	// 上级代理名称
	private String agentName;

	// 商家名称
	private String name;

	// 商家简称
	private String externalOperationName;
	
	private Integer linkType;

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}

	@Override
	public String toString() {
		return "MerchantBasics [merchantNo=" + merchantNo + ", agentName=" + agentName + ", name=" + name
				+ ", externalOperationName=" + externalOperationName + "]";
	}

}
