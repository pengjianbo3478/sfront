package com.gl365.app.dto.command;

import com.gl365.app.dto.enum_type.CustomerServiceRequestType;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/1.
 */
public class InsertCustomerServiceCommand{

	@ApiModelProperty(value = "业务员id。APP登录后不需要理会", hidden = true)
	private String agentId;

	@ApiModelProperty(value = "商户id", required = true)
	private String merchantId;

	@ApiModelProperty(value = "售后服务类型。10：法人账户变更，20：非法人账户变更，30：机具故障，40：退机", allowableValues = "10,20,30,40", dataType = "java.lang.Integer", required = true)
	private CustomerServiceRequestType type;

	@ApiModelProperty(value = "营业执照URL")
	private String licenseUrl;

	@ApiModelProperty(value = "（法人）身份证正面图片URL")
	private String idCardFrontUrl;

	@ApiModelProperty(value = "（法人）身份证背面图片URL")
	private String idCardBackUrl;

	@ApiModelProperty(value = "银行卡正面图片URL")
	private String depositCardFrontUrl;

	@ApiModelProperty(value = "银行卡背面图片URL")
	private String depositCardBackUrl;

	@ApiModelProperty(value = "变更/退机申请表URL")
	private String applicationFormUrl;

	@ApiModelProperty(value = "非法人身份证正面图片URL")
	private String nlpIdCardFrontUrl;

	@ApiModelProperty(value = "非法人身份证背面图片URL")
	private String nlpIdCardBackUrl;

	@ApiModelProperty(value = "法人授权书URL")
	private String engagementLetterUrl;

	@ApiModelProperty(value = "维修清单URL")
	private String fixingListUrl;

	@ApiModelProperty(value = "合同URL")
	private String contractUrl;


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

	public CustomerServiceRequestType getType() {
		return type;
	}

	public void setType(CustomerServiceRequestType type) {
		this.type = type;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getIdCardFrontUrl() {
		return idCardFrontUrl;
	}

	public void setIdCardFrontUrl(String idCardFrontUrl) {
		this.idCardFrontUrl = idCardFrontUrl;
	}

	public String getIdCardBackUrl() {
		return idCardBackUrl;
	}

	public void setIdCardBackUrl(String idCardBackUrl) {
		this.idCardBackUrl = idCardBackUrl;
	}

	public String getDepositCardFrontUrl() {
		return depositCardFrontUrl;
	}

	public void setDepositCardFrontUrl(String depositCardFrontUrl) {
		this.depositCardFrontUrl = depositCardFrontUrl;
	}

	public String getDepositCardBackUrl() {
		return depositCardBackUrl;
	}

	public void setDepositCardBackUrl(String depositCardBackUrl) {
		this.depositCardBackUrl = depositCardBackUrl;
	}

	public String getApplicationFormUrl() {
		return applicationFormUrl;
	}

	public void setApplicationFormUrl(String applicationFormUrl) {
		this.applicationFormUrl = applicationFormUrl;
	}

	public String getNlpIdCardFrontUrl() {
		return nlpIdCardFrontUrl;
	}

	public void setNlpIdCardFrontUrl(String nlpIdCardFrontUrl) {
		this.nlpIdCardFrontUrl = nlpIdCardFrontUrl;
	}

	public String getNlpIdCardBackUrl() {
		return nlpIdCardBackUrl;
	}

	public void setNlpIdCardBackUrl(String nlpIdCardBackUrl) {
		this.nlpIdCardBackUrl = nlpIdCardBackUrl;
	}

	public String getEngagementLetterUrl() {
		return engagementLetterUrl;
	}

	public void setEngagementLetterUrl(String engagementLetterUrl) {
		this.engagementLetterUrl = engagementLetterUrl;
	}

	public String getFixingListUrl() {
		return fixingListUrl;
	}

	public void setFixingListUrl(String fixingListUrl) {
		this.fixingListUrl = fixingListUrl;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
}
