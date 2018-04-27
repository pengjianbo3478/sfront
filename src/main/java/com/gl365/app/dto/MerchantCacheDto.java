package com.gl365.app.dto;

import java.math.BigDecimal;
import java.util.List;

import com.gl365.app.dto.command.PosCommand;
import com.gl365.app.dto.enum_type.MerchantStatus;

import io.swagger.annotations.ApiModelProperty;

public class MerchantCacheDto {

	@ApiModelProperty(value = "序列主键:新增保存可空[列表返回]")
	private Long id;

	@ApiModelProperty(value = "商户联系人姓名[列表返回]", example = "李远波", required = false)
	private String contactName;
	@ApiModelProperty(value = "商户联系人手机号[列表返回]", example = "13899999999", required = false)
	private String contactMobile;
	@ApiModelProperty(value = "商户名称[列表返回]", example = "深圳给乐信息科技有限公司", required = false)
	private String name;
	@ApiModelProperty(value = "对外经营名称", hidden = true)
	private String externalOperationName;
	@ApiModelProperty(value = "营业执照号", example = "440301105274313", required = false)
	private String licenseNo;
	@ApiModelProperty(value = "注册地址", example = "深圳市南山区粤海街道高新南一道9号中国科技开发院3A层3A06室")
	private String registeredAddress;
	@ApiModelProperty(value = "营业执照有效期", example = "长期", required = false)
	private String licensePicVldDate;
	@ApiModelProperty(value = "法人姓名", example = "李远波", required = false)
	private String legalPersonName;
	@ApiModelProperty(value = "给乐合同编号", example = "GL201708231037", required = false)
	private String contractNo;
	@ApiModelProperty(value = "给乐商户合作协议URL", example = "https://gl365dev.oss-cn-shenzhen.aliyuncs.com/glsale/2000014profile_1499392149982.jpg", required = false)
	private String contractNoPicUrl;
	@ApiModelProperty(value = "付费通行业类型", example = "5812", hidden = true)
	private Integer industry;
	@ApiModelProperty(value = "商户所处省份", example = "6", required = false)
	private Integer province;
	@ApiModelProperty(value = "商户所处城市", example = "77", required = false)
	private Integer city;
	@ApiModelProperty(value = "商户所处区域", example = "707", required = false)
	private Integer district;
	@ApiModelProperty(value = "详细地址", example = "深圳市南山区粤海街道高新南一道9号中国科技开发院3A层3A06室", required = false)
	private String detailedAddress;
	@ApiModelProperty(value = "返佣率", hidden = true)
	private BigDecimal commissionRatio;
	@ApiModelProperty(value = "返豆率", example = "10", required = false)
	private BigDecimal saleRate;
	@ApiModelProperty(value = "负责进件的代理商ID", example = "2000063", required = false)
	private String agentId;
	@ApiModelProperty(value = "给乐行业", example = "10304", required = false)
	private Integer subIndustry;
	@ApiModelProperty(value = "[列表返回]：营业执照对应的商户状态：2其它业务员已经进件成功", example = "2", required = false)
	private MerchantStatus merchantStatus;

	@ApiModelProperty(value = "Y:下一步验证，保存为空", example = "Y", required = false)
	private String isCheck;

	@ApiModelProperty(value = "预先绑定二维码")
	private List<String> barCodes;

	@ApiModelProperty(value = "POS机信息")
	private List<PosCommand> commands;

	@ApiModelProperty(value = "POS机信息", hidden = true)
	private String pos;

	private String taxRegPicUrl;

	private String licensePicUrl;

	private String legalPersonIdCardFrontPicUrl;

	private String legalPersonIdCardBackPicUrl;

	private String legalPersonIdCardVldDate;

	private String orgCodePicUrl;

	private String contractPicUrl;

	private String facadeOfShopPicUrl;

	private String addressPicUrl;

	private String scopeOfOperationPicUrl;

	private String ticketPicUrl;

	private String publpAccountName;

	private String publpAccountNo;

	private String publpIdCardNo;

	private String publpAccountBank;

	private String prilpAccountName;

	private String prilpAccountNo;

	private String prilpIdCardNo;

	private String prilpAccountBank;

	private String prinlpAccountName;

	private String prinlpAccountNo;

	private String prinlpIdCardNo;

	private String prinlpIdCardNo2;

	private String prinlpAccountBank;

	private String prinlpEngagementLetterPicUrl;

	private String prinlpIdHoldingPicUrl;

	private BigDecimal posDebitFeeRate;

	private BigDecimal posDebitMaxAmount;

	private BigDecimal posCreditFeeRate;

	private BigDecimal posCreditMaxAmount;

	private BigDecimal onpayDebitFeeRate;

	private BigDecimal onpayDebitMaxAmount;

	private BigDecimal onpayCreditFeeRate;

	private BigDecimal onpayCreditMaxAmount;

	private Long revision;

	private String openLicensePicUrl;

	private String cashierDeskPicUrl;

	private String operateProductPicUrl;

	private String operateFieldPicUrl;

	private String prilpBankFrontPicUrl;

	private String prilpBankBackPicUrl;

	private String prinlpIdCardFrontPicUrl;

	private String prinlpIdCardBackPicUrl;

	private String prinlpBankFrontPicUrl;

	private String prinlpBankBackPicUrl;

	private String barCode;

	private String auditor;

	private Integer property;

	private String isPartner;

	private String taxRegCerNo;

	private String busContacts;

	private String busContactsPerNo;

	private String busContactsMobile;

	private String prinlpMobile;

	private String legalPerMobile;

	private BigDecimal avgPrice;

	private String additionalContractNoPicUrl;
	
	private Integer linkType;
	
	private Long linkMerchantId;
	
	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getLicensePicVldDate() {
		return licensePicVldDate;
	}

	public void setLicensePicVldDate(String licensePicVldDate) {
		this.licensePicVldDate = licensePicVldDate;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNoPicUrl() {
		return contractNoPicUrl;
	}

	public void setContractNoPicUrl(String contractNoPicUrl) {
		this.contractNoPicUrl = contractNoPicUrl;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public BigDecimal getCommissionRatio() {
		return commissionRatio;
	}

	public void setCommissionRatio(BigDecimal commissionRatio) {
		this.commissionRatio = commissionRatio;
	}

	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Integer getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(Integer subIndustry) {
		this.subIndustry = subIndustry;
	}

	public MerchantStatus getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(MerchantStatus merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public List<String> getBarCodes() {
		return barCodes;
	}

	public void setBarCodes(List<String> barCodes) {
		this.barCodes = barCodes;
	}

	public String getTaxRegPicUrl() {
		return taxRegPicUrl;
	}

	public void setTaxRegPicUrl(String taxRegPicUrl) {
		this.taxRegPicUrl = taxRegPicUrl;
	}

	public String getLicensePicUrl() {
		return licensePicUrl;
	}

	public void setLicensePicUrl(String licensePicUrl) {
		this.licensePicUrl = licensePicUrl;
	}

	public String getLegalPersonIdCardFrontPicUrl() {
		return legalPersonIdCardFrontPicUrl;
	}

	public void setLegalPersonIdCardFrontPicUrl(String legalPersonIdCardFrontPicUrl) {
		this.legalPersonIdCardFrontPicUrl = legalPersonIdCardFrontPicUrl;
	}

	public String getLegalPersonIdCardBackPicUrl() {
		return legalPersonIdCardBackPicUrl;
	}

	public void setLegalPersonIdCardBackPicUrl(String legalPersonIdCardBackPicUrl) {
		this.legalPersonIdCardBackPicUrl = legalPersonIdCardBackPicUrl;
	}

	public String getLegalPersonIdCardVldDate() {
		return legalPersonIdCardVldDate;
	}

	public void setLegalPersonIdCardVldDate(String legalPersonIdCardVldDate) {
		this.legalPersonIdCardVldDate = legalPersonIdCardVldDate;
	}

	public String getOrgCodePicUrl() {
		return orgCodePicUrl;
	}

	public void setOrgCodePicUrl(String orgCodePicUrl) {
		this.orgCodePicUrl = orgCodePicUrl;
	}

	public String getContractPicUrl() {
		return contractPicUrl;
	}

	public void setContractPicUrl(String contractPicUrl) {
		this.contractPicUrl = contractPicUrl;
	}

	public String getFacadeOfShopPicUrl() {
		return facadeOfShopPicUrl;
	}

	public void setFacadeOfShopPicUrl(String facadeOfShopPicUrl) {
		this.facadeOfShopPicUrl = facadeOfShopPicUrl;
	}

	public String getAddressPicUrl() {
		return addressPicUrl;
	}

	public void setAddressPicUrl(String addressPicUrl) {
		this.addressPicUrl = addressPicUrl;
	}

	public String getScopeOfOperationPicUrl() {
		return scopeOfOperationPicUrl;
	}

	public void setScopeOfOperationPicUrl(String scopeOfOperationPicUrl) {
		this.scopeOfOperationPicUrl = scopeOfOperationPicUrl;
	}

	public String getTicketPicUrl() {
		return ticketPicUrl;
	}

	public void setTicketPicUrl(String ticketPicUrl) {
		this.ticketPicUrl = ticketPicUrl;
	}

	public String getPublpAccountName() {
		return publpAccountName;
	}

	public void setPublpAccountName(String publpAccountName) {
		this.publpAccountName = publpAccountName;
	}

	public String getPublpAccountNo() {
		return publpAccountNo;
	}

	public void setPublpAccountNo(String publpAccountNo) {
		this.publpAccountNo = publpAccountNo;
	}

	public String getPublpIdCardNo() {
		return publpIdCardNo;
	}

	public void setPublpIdCardNo(String publpIdCardNo) {
		this.publpIdCardNo = publpIdCardNo;
	}

	public String getPublpAccountBank() {
		return publpAccountBank;
	}

	public void setPublpAccountBank(String publpAccountBank) {
		this.publpAccountBank = publpAccountBank;
	}

	public String getPrilpAccountName() {
		return prilpAccountName;
	}

	public void setPrilpAccountName(String prilpAccountName) {
		this.prilpAccountName = prilpAccountName;
	}

	public String getPrilpAccountNo() {
		return prilpAccountNo;
	}

	public void setPrilpAccountNo(String prilpAccountNo) {
		this.prilpAccountNo = prilpAccountNo;
	}

	public String getPrilpIdCardNo() {
		return prilpIdCardNo;
	}

	public void setPrilpIdCardNo(String prilpIdCardNo) {
		this.prilpIdCardNo = prilpIdCardNo;
	}

	public String getPrilpAccountBank() {
		return prilpAccountBank;
	}

	public void setPrilpAccountBank(String prilpAccountBank) {
		this.prilpAccountBank = prilpAccountBank;
	}

	public String getPrinlpAccountName() {
		return prinlpAccountName;
	}

	public void setPrinlpAccountName(String prinlpAccountName) {
		this.prinlpAccountName = prinlpAccountName;
	}

	public String getPrinlpAccountNo() {
		return prinlpAccountNo;
	}

	public void setPrinlpAccountNo(String prinlpAccountNo) {
		this.prinlpAccountNo = prinlpAccountNo;
	}

	public String getPrinlpIdCardNo() {
		return prinlpIdCardNo;
	}

	public void setPrinlpIdCardNo(String prinlpIdCardNo) {
		this.prinlpIdCardNo = prinlpIdCardNo;
	}

	public String getPrinlpIdCardNo2() {
		return prinlpIdCardNo2;
	}

	public void setPrinlpIdCardNo2(String prinlpIdCardNo2) {
		this.prinlpIdCardNo2 = prinlpIdCardNo2;
	}

	public String getPrinlpAccountBank() {
		return prinlpAccountBank;
	}

	public void setPrinlpAccountBank(String prinlpAccountBank) {
		this.prinlpAccountBank = prinlpAccountBank;
	}

	public String getPrinlpEngagementLetterPicUrl() {
		return prinlpEngagementLetterPicUrl;
	}

	public void setPrinlpEngagementLetterPicUrl(String prinlpEngagementLetterPicUrl) {
		this.prinlpEngagementLetterPicUrl = prinlpEngagementLetterPicUrl;
	}

	public String getPrinlpIdHoldingPicUrl() {
		return prinlpIdHoldingPicUrl;
	}

	public void setPrinlpIdHoldingPicUrl(String prinlpIdHoldingPicUrl) {
		this.prinlpIdHoldingPicUrl = prinlpIdHoldingPicUrl;
	}

	public BigDecimal getPosDebitFeeRate() {
		return posDebitFeeRate;
	}

	public void setPosDebitFeeRate(BigDecimal posDebitFeeRate) {
		this.posDebitFeeRate = posDebitFeeRate;
	}

	public BigDecimal getPosDebitMaxAmount() {
		return posDebitMaxAmount;
	}

	public void setPosDebitMaxAmount(BigDecimal posDebitMaxAmount) {
		this.posDebitMaxAmount = posDebitMaxAmount;
	}

	public BigDecimal getPosCreditFeeRate() {
		return posCreditFeeRate;
	}

	public void setPosCreditFeeRate(BigDecimal posCreditFeeRate) {
		this.posCreditFeeRate = posCreditFeeRate;
	}

	public BigDecimal getPosCreditMaxAmount() {
		return posCreditMaxAmount;
	}

	public void setPosCreditMaxAmount(BigDecimal posCreditMaxAmount) {
		this.posCreditMaxAmount = posCreditMaxAmount;
	}

	public BigDecimal getOnpayDebitFeeRate() {
		return onpayDebitFeeRate;
	}

	public void setOnpayDebitFeeRate(BigDecimal onpayDebitFeeRate) {
		this.onpayDebitFeeRate = onpayDebitFeeRate;
	}

	public BigDecimal getOnpayDebitMaxAmount() {
		return onpayDebitMaxAmount;
	}

	public void setOnpayDebitMaxAmount(BigDecimal onpayDebitMaxAmount) {
		this.onpayDebitMaxAmount = onpayDebitMaxAmount;
	}

	public BigDecimal getOnpayCreditFeeRate() {
		return onpayCreditFeeRate;
	}

	public void setOnpayCreditFeeRate(BigDecimal onpayCreditFeeRate) {
		this.onpayCreditFeeRate = onpayCreditFeeRate;
	}

	public BigDecimal getOnpayCreditMaxAmount() {
		return onpayCreditMaxAmount;
	}

	public void setOnpayCreditMaxAmount(BigDecimal onpayCreditMaxAmount) {
		this.onpayCreditMaxAmount = onpayCreditMaxAmount;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public String getOpenLicensePicUrl() {
		return openLicensePicUrl;
	}

	public void setOpenLicensePicUrl(String openLicensePicUrl) {
		this.openLicensePicUrl = openLicensePicUrl;
	}

	public String getCashierDeskPicUrl() {
		return cashierDeskPicUrl;
	}

	public void setCashierDeskPicUrl(String cashierDeskPicUrl) {
		this.cashierDeskPicUrl = cashierDeskPicUrl;
	}

	public String getOperateProductPicUrl() {
		return operateProductPicUrl;
	}

	public void setOperateProductPicUrl(String operateProductPicUrl) {
		this.operateProductPicUrl = operateProductPicUrl;
	}

	public String getOperateFieldPicUrl() {
		return operateFieldPicUrl;
	}

	public void setOperateFieldPicUrl(String operateFieldPicUrl) {
		this.operateFieldPicUrl = operateFieldPicUrl;
	}

	public String getPrilpBankFrontPicUrl() {
		return prilpBankFrontPicUrl;
	}

	public void setPrilpBankFrontPicUrl(String prilpBankFrontPicUrl) {
		this.prilpBankFrontPicUrl = prilpBankFrontPicUrl;
	}

	public String getPrilpBankBackPicUrl() {
		return prilpBankBackPicUrl;
	}

	public void setPrilpBankBackPicUrl(String prilpBankBackPicUrl) {
		this.prilpBankBackPicUrl = prilpBankBackPicUrl;
	}

	public String getPrinlpIdCardFrontPicUrl() {
		return prinlpIdCardFrontPicUrl;
	}

	public void setPrinlpIdCardFrontPicUrl(String prinlpIdCardFrontPicUrl) {
		this.prinlpIdCardFrontPicUrl = prinlpIdCardFrontPicUrl;
	}

	public String getPrinlpIdCardBackPicUrl() {
		return prinlpIdCardBackPicUrl;
	}

	public void setPrinlpIdCardBackPicUrl(String prinlpIdCardBackPicUrl) {
		this.prinlpIdCardBackPicUrl = prinlpIdCardBackPicUrl;
	}

	public String getPrinlpBankFrontPicUrl() {
		return prinlpBankFrontPicUrl;
	}

	public void setPrinlpBankFrontPicUrl(String prinlpBankFrontPicUrl) {
		this.prinlpBankFrontPicUrl = prinlpBankFrontPicUrl;
	}

	public String getPrinlpBankBackPicUrl() {
		return prinlpBankBackPicUrl;
	}

	public void setPrinlpBankBackPicUrl(String prinlpBankBackPicUrl) {
		this.prinlpBankBackPicUrl = prinlpBankBackPicUrl;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}

	public String getTaxRegCerNo() {
		return taxRegCerNo;
	}

	public void setTaxRegCerNo(String taxRegCerNo) {
		this.taxRegCerNo = taxRegCerNo;
	}

	public String getBusContacts() {
		return busContacts;
	}

	public void setBusContacts(String busContacts) {
		this.busContacts = busContacts;
	}

	public String getBusContactsPerNo() {
		return busContactsPerNo;
	}

	public void setBusContactsPerNo(String busContactsPerNo) {
		this.busContactsPerNo = busContactsPerNo;
	}

	public String getBusContactsMobile() {
		return busContactsMobile;
	}

	public void setBusContactsMobile(String busContactsMobile) {
		this.busContactsMobile = busContactsMobile;
	}

	public String getPrinlpMobile() {
		return prinlpMobile;
	}

	public void setPrinlpMobile(String prinlpMobile) {
		this.prinlpMobile = prinlpMobile;
	}

	public String getLegalPerMobile() {
		return legalPerMobile;
	}

	public void setLegalPerMobile(String legalPerMobile) {
		this.legalPerMobile = legalPerMobile;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getAdditionalContractNoPicUrl() {
		return additionalContractNoPicUrl;
	}

	public void setAdditionalContractNoPicUrl(String additionalContractNoPicUrl) {
		this.additionalContractNoPicUrl = additionalContractNoPicUrl;
	}

	public List<PosCommand> getCommands() {
		return commands;
	}

	public void setCommands(List<PosCommand> commands) {
		this.commands = commands;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Long getLinkMerchantId() {
		return linkMerchantId;
	}

	public void setLinkMerchantId(Long linkMerchantId) {
		this.linkMerchantId = linkMerchantId;
	}


}