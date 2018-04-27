package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商户历史信息模型
 * Created by caoyilong on 2017/6/2.
 */
public class MerchantHistory {
	/**
	 * 历史记录id
	 */
	private String id;
	/**
	 * 到商户的外键
	 */
	private Merchant merchant;
	/**
	 * 对外经营名称
	 */
	private String externalOperationName;
	/**
	 * 税务登记证图片URL
	 */
	private String taxRegPicUrl;
	/**
	 * 营业执照图片URL
	 */
	private String licensePicUrl;
	/**
	 * 营业执照有效期
	 */
	private String licensePicVldDate;
	
	/**
	 * 注册地址
	 */
	private String registeredAddress;
	/**
	 * 法人身份证正面图片URL
	 */
	private String legalPersonIdCardFrontPicUrl;
	/**
	 * 法人身份证背面图片URL
	 */
	private String legalPersonIdCardBackPicUrl;
	/**
	 * 法人身份证有效期
	 */
	private String legalPersonIdCardVldDate;
	/**
	 * 组织机构代码证图片URL
	 */
	private String orgCodePicUrl;
	/**
	 * 协议照片URL
	 */
	private String contractPicUrl;
	/**
	 * 门头照图片URL
	 */
	private String facadeOfShopPicUrl;
	/**
	 * 地址照图片URL
	 */
	private String addressPicUrl;
	/**
	 * 经营内容图片URL
	 */
	private String scopeOfOperationPicUrl;
	/**
	 * 上传小票图片URL
	 */
	private String ticketPicUrl;
	/**
	 * 对公法人账号姓名
	 */
	private String publpAccountName;
	/**
	 * 对公法人账号号码
	 */
	private String publpAccountNo;
	/**
	 * 法人身份证号码
	 */
	private String publpIdCardNo;
	/**
	 * 开户行
	 */
	private String publpAccountBank;
	/**
	 * 对私法人账号姓名
	 */
	private String prilpAccountName;
	/**
	 * 对私法人账号号码
	 */
	private String prilpAccountNo;
	/**
	 * 法人身份证号码
	 */
	private String prilpIdCardNo;
	/**
	 * 开户行
	 */
	private String prilpAccountBank;
	/**
	 * 对私非法人账号姓名
	 */
	private String prinlpAccountName;
	/**
	 * 对私非法人账号号码
	 */
	private String prinlpAccountNo;
	/**
	 * 对私非法人-法人身份证号码
	 */
	private String prinlpIdCardNo;
	/**
	 * 对私非法人-非法人身份证号码
	 */
	private String prinlpIdCardNo2;
	/**
	 * 开户行
	 */
	private String prinlpAccountBank;
	/**
	 * 法人委托书图片URL
	 */
	private String prinlpEngagementLetterPicUrl;
	/**
	 * 法人持身份证照图片URL
	 */
	private String prinlpIdHoldingPicUrl;
	/**
	 * POS借记卡费率；从支付机构费率基本表取（放在渠道系统）M端运营后台维护
	 */
	private BigDecimal posDebitFeeRate;
	/**
	 * POS借记卡封顶值；0表示没有封顶值
	 */
	private BigDecimal posDebitMaxAmount;
	/**
	 * POS贷记卡费率
	 */
	private BigDecimal posCreditFeeRate;
	/**
	 * POS贷记卡封顶值
	 */
	private BigDecimal posCreditMaxAmount;
	/**
	 * 线上借记卡费率
	 */
	private BigDecimal onpayDebitFeeRate;
	/**
	 * 线上借记卡封顶值
	 */
	private BigDecimal onpayDebitMaxAmount;

	/**
	 * 线上贷记卡费率
	 */
	private BigDecimal onpayCreditFeeRate;

	/**
	 * 线上贷记卡封顶值
	 */
	private BigDecimal onpayCreditMaxAmount;

	/**
	 * 版本号
	 */
	private Long revision;
	/**
	 * 记录创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系人电话
	 */
	private String contactMobile;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 营业执照号
	 */
	private String licenseNo;
	/**
	 * 法人姓名
	 */
	private String legalPersonName;
	/**
	 * 行业类型
	 */
	private Integer industry;
	/**
	 * 细分行业
	 */
	private Integer subIndustry;
	/**
	 * 省
	 */
	private Integer province;

	/**
	 * 市
	 */
	private Integer city;

	/**
	 * 区
	 */
	private Integer district;

	/**
	 * 返佣率
	 */
	private BigDecimal commissionRatio;
	
	/**
	 * 返豆率
	 */
	private BigDecimal saleRate;
	
	/**
	 * 详细地址
	 */
	private String detailedAddress;

	/**
	 * 合同编号
	 */
	private String contractNo;
	
	//开户许可证（非必填）
  	private String openLicensePicUrl;
  	//给乐商户合作协议（非必填）
  	private String contractNoPicUrl;
  	//收银台/前台照（必填）
  	private String cashierDeskPicUrl;
  	//经营产品/经营场地（必填）
  	private String operateProductPicUrl;
  	//经营场地/大楼外观照（必填）
  	private String operateFieldPicUrl;
  	//对私法人银行卡正面（必填）
  	private String prilpBankFrontPicUrl;
  	//对私法人银行卡反面（必填）
  	private String prilpBankBackPicUrl;
  	//对私非法人身份证正面（必填）
  	private String prinlpIdCardFrontPicUrl;
  	//对私非法人身份证反面（必填）
  	private String prinlpIdCardBackPicUrl;
  	//对私非法人银行卡正面（必填）
  	private String prinlpBankFrontPicUrl;
  	//对私非法人银行卡反面（必填）
  	private String prinlpBankBackPicUrl;
  	
  	private String barCode;
  	
	private Integer property;// 开户性质:1:对私法人，2对私非法人，3对公
	private String isPartner;// 合伙人商户,是:Y
	private String taxRegCerNo;// 税务登记号（企业，必填）

	private String busContacts;// 企业联系人姓名（企业，必填）
	private String busContactsPerNo;// 企业联系人身份证号（企业，必填）
	private String busContactsMobile;// 企业联系人手机号（企业，必填）
	private String prinlpMobile;// 对私非法人手机号
	private String legalPerMobile;// 企业为法人手机号
	
	private BigDecimal avgPrice;//人均消费
	private String additionalContractNoPicUrl;//补充给乐商户合作协议
	
	private List<String> barCodes;

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

	public List<String> getBarCodes() {
		return barCodes;
	}

	public void setBarCodes(List<String> barCodes) {
		this.barCodes = barCodes;
	}

	@ApiModelProperty(value = "历史记录的id", hidden = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ApiModelProperty("对外经营名称")
	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}

	@ApiModelProperty("税务登记证图片URL")
	public String getTaxRegPicUrl() {
		return taxRegPicUrl;
	}

	public void setTaxRegPicUrl(String taxRegPicUrl) {
		this.taxRegPicUrl = taxRegPicUrl;
	}

	@ApiModelProperty("营业执照图片URL")
	public String getLicensePicUrl() {
		return licensePicUrl;
	}

	public void setLicensePicUrl(String licensePicUrl) {
		this.licensePicUrl = licensePicUrl;
	}

	@ApiModelProperty("法人身份证正面图片URL")
	public String getLegalPersonIdCardFrontPicUrl() {
		return legalPersonIdCardFrontPicUrl;
	}

	public void setLegalPersonIdCardFrontPicUrl(String legalPersonIdCardFrontPicUrl) {
		this.legalPersonIdCardFrontPicUrl = legalPersonIdCardFrontPicUrl;
	}

	@ApiModelProperty("法人身份证背面图片URL")
	public String getLegalPersonIdCardBackPicUrl() {
		return legalPersonIdCardBackPicUrl;
	}

	public void setLegalPersonIdCardBackPicUrl(String legalPersonIdCardBackPicUrl) {
		this.legalPersonIdCardBackPicUrl = legalPersonIdCardBackPicUrl;
	}

	@ApiModelProperty("营业执照有效期")
	public String getLicensePicVldDate() {
		return licensePicVldDate;
	}

	public void setLicensePicVldDate(String licensePicVldDate) {
		this.licensePicVldDate = licensePicVldDate;
	}
	
	@ApiModelProperty("注册地址")
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	@ApiModelProperty("法人身份证有效期")
	public String getLegalPersonIdCardVldDate() {
		return legalPersonIdCardVldDate;
	}

	public void setLegalPersonIdCardVldDate(String legalPersonIdCardVldDate) {
		this.legalPersonIdCardVldDate = legalPersonIdCardVldDate;
	}

	@ApiModelProperty("组织机构代码证图片URL")
	public String getOrgCodePicUrl() {
		return orgCodePicUrl;
	}

	public void setOrgCodePicUrl(String orgCodePicUrl) {
		this.orgCodePicUrl = orgCodePicUrl;
	}

	@ApiModelProperty("协议照片URL")
	public String getContractPicUrl() {
		return contractPicUrl;
	}

	public void setContractPicUrl(String contractPicUrl) {
		this.contractPicUrl = contractPicUrl;
	}

	@ApiModelProperty("门头照图片URL")
	public String getFacadeOfShopPicUrl() {
		return facadeOfShopPicUrl;
	}

	public void setFacadeOfShopPicUrl(String facadeOfShopPicUrl) {
		this.facadeOfShopPicUrl = facadeOfShopPicUrl;
	}

	@ApiModelProperty("地址照图片URL")
	public String getAddressPicUrl() {
		return addressPicUrl;
	}

	public void setAddressPicUrl(String addressPicUrl) {
		this.addressPicUrl = addressPicUrl;
	}

	@ApiModelProperty("经营内容图片URL")
	public String getScopeOfOperationPicUrl() {
		return scopeOfOperationPicUrl;
	}

	public void setScopeOfOperationPicUrl(String scopeOfOperationPicUrl) {
		this.scopeOfOperationPicUrl = scopeOfOperationPicUrl;
	}

	@ApiModelProperty("上传小票图片URL")
	public String getTicketPicUrl() {
		return ticketPicUrl;
	}

	public void setTicketPicUrl(String ticketPicUrl) {
		this.ticketPicUrl = ticketPicUrl;
	}

	@ApiModelProperty("对公法人账号姓名")
	public String getPublpAccountName() {
		return publpAccountName;
	}

	public void setPublpAccountName(String publpAccountName) {
		this.publpAccountName = publpAccountName;
	}

	@ApiModelProperty("对公法人账号号码")
	public String getPublpAccountNo() {
		return publpAccountNo;
	}

	public void setPublpAccountNo(String publpAccountNo) {
		this.publpAccountNo = publpAccountNo;
	}

	@ApiModelProperty("对公法人身份证号码")
	public String getPublpIdCardNo() {
		return publpIdCardNo;
	}

	public void setPublpIdCardNo(String publpIdCardNo) {
		this.publpIdCardNo = publpIdCardNo;
	}

	@ApiModelProperty("对公法人开户行")
	public String getPublpAccountBank() {
		return publpAccountBank;
	}

	public void setPublpAccountBank(String publpAccountBank) {
		this.publpAccountBank = publpAccountBank;
	}

	@ApiModelProperty("对私法人账号姓名")
	public String getPrilpAccountName() {
		return prilpAccountName;
	}

	public void setPrilpAccountName(String prilpAccountName) {
		this.prilpAccountName = prilpAccountName;
	}

	@ApiModelProperty("对私法人账号号码")
	public String getPrilpAccountNo() {
		return prilpAccountNo;
	}

	public void setPrilpAccountNo(String prilpAccountNo) {
		this.prilpAccountNo = prilpAccountNo;
	}

	@ApiModelProperty("对私法人身份证号码")
	public String getPrilpIdCardNo() {
		return prilpIdCardNo;
	}

	public void setPrilpIdCardNo(String prilpIdCardNo) {
		this.prilpIdCardNo = prilpIdCardNo;
	}

	@ApiModelProperty("对私法人开户行")
	public String getPrilpAccountBank() {
		return prilpAccountBank;
	}

	public void setPrilpAccountBank(String prilpAccountBank) {
		this.prilpAccountBank = prilpAccountBank;
	}

	@ApiModelProperty("对私非法人账号姓名")
	public String getPrinlpAccountName() {
		return prinlpAccountName;
	}

	public void setPrinlpAccountName(String prinlpAccountName) {
		this.prinlpAccountName = prinlpAccountName;
	}

	@ApiModelProperty("对私非法人账号号码")
	public String getPrinlpAccountNo() {
		return prinlpAccountNo;
	}

	public void setPrinlpAccountNo(String prinlpAccountNo) {
		this.prinlpAccountNo = prinlpAccountNo;
	}

	@ApiModelProperty("对私非法人-法人身份证号码")
	public String getPrinlpIdCardNo() {
		return prinlpIdCardNo;
	}

	public void setPrinlpIdCardNo(String prinlpIdCardNo) {
		this.prinlpIdCardNo = prinlpIdCardNo;
	}

	@ApiModelProperty("对私非法人-非法人身份证号码")
	public String getPrinlpIdCardNo2() {
		return prinlpIdCardNo2;
	}

	public void setPrinlpIdCardNo2(String prinlpIdCardNo2) {
		this.prinlpIdCardNo2 = prinlpIdCardNo2;
	}

	@ApiModelProperty("对私非法人-开户行")
	public String getPrinlpAccountBank() {
		return prinlpAccountBank;
	}

	public void setPrinlpAccountBank(String prinlpAccountBank) {
		this.prinlpAccountBank = prinlpAccountBank;
	}

	@ApiModelProperty("对私非法人-委托书图片URL")
	public String getPrinlpEngagementLetterPicUrl() {
		return prinlpEngagementLetterPicUrl;
	}

	public void setPrinlpEngagementLetterPicUrl(String prinlpEngagementLetterPicUrl) {
		this.prinlpEngagementLetterPicUrl = prinlpEngagementLetterPicUrl;
	}

	@ApiModelProperty("对私非法人-法人持身份证照图片URL")
	public String getPrinlpIdHoldingPicUrl() {
		return prinlpIdHoldingPicUrl;
	}

	public void setPrinlpIdHoldingPicUrl(String prinlpIdHoldingPicUrl) {
		this.prinlpIdHoldingPicUrl = prinlpIdHoldingPicUrl;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getPosDebitFeeRate() {
		return posDebitFeeRate;
	}

	public void setPosDebitFeeRate(BigDecimal posDebitFeeRate) {
		this.posDebitFeeRate = posDebitFeeRate;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getPosDebitMaxAmount() {
		return posDebitMaxAmount;
	}

	public void setPosDebitMaxAmount(BigDecimal posDebitMaxAmount) {
		this.posDebitMaxAmount = posDebitMaxAmount;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getPosCreditFeeRate() {
		return posCreditFeeRate;
	}

	public void setPosCreditFeeRate(BigDecimal posCreditFeeRate) {
		this.posCreditFeeRate = posCreditFeeRate;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getPosCreditMaxAmount() {
		return posCreditMaxAmount;
	}

	public void setPosCreditMaxAmount(BigDecimal posCreditMaxAmount) {
		this.posCreditMaxAmount = posCreditMaxAmount;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getOnpayDebitFeeRate() {
		return onpayDebitFeeRate;
	}

	public void setOnpayDebitFeeRate(BigDecimal onpayDebitFeeRate) {
		this.onpayDebitFeeRate = onpayDebitFeeRate;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getOnpayDebitMaxAmount() {
		return onpayDebitMaxAmount;
	}

	public void setOnpayDebitMaxAmount(BigDecimal onpayDebitMaxAmount) {
		this.onpayDebitMaxAmount = onpayDebitMaxAmount;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getOnpayCreditFeeRate() {
		return onpayCreditFeeRate;
	}

	public void setOnpayCreditFeeRate(BigDecimal onpayCreditFeeRate) {
		this.onpayCreditFeeRate = onpayCreditFeeRate;
	}

	@ApiModelProperty(hidden = true)
	public BigDecimal getOnpayCreditMaxAmount() {
		return onpayCreditMaxAmount;
	}

	public void setOnpayCreditMaxAmount(BigDecimal onpayCreditMaxAmount) {
		this.onpayCreditMaxAmount = onpayCreditMaxAmount;
	}

	@ApiModelProperty(hidden = true)
	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	@ApiModelProperty(hidden = true)
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@ApiModelProperty("联系人姓名")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@ApiModelProperty("联系人电话")
	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	@ApiModelProperty("商户名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty("营业执照号")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@ApiModelProperty("法人姓名")
	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	@ApiModelProperty("行业类型")
	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	@ApiModelProperty("细分行业")
	public Integer getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(Integer subIndustry) {
		this.subIndustry = subIndustry;
	}

	@ApiModelProperty("省")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@ApiModelProperty("市")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@ApiModelProperty("区")
	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@ApiModelProperty("返佣率")
	public BigDecimal getCommissionRatio() {
		return commissionRatio;
	}

	public void setCommissionRatio(BigDecimal commissionRatio) {
		this.commissionRatio = commissionRatio;
	}

	@ApiModelProperty("返豆率")
	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	@ApiModelProperty("详细地址")
	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	@ApiModelProperty("合同编号")
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@ApiModelProperty("开户许可证（非必填）")
	public String getOpenLicensePicUrl() {
		return openLicensePicUrl;
	}

	public void setOpenLicensePicUrl(String openLicensePicUrl) {
		this.openLicensePicUrl = openLicensePicUrl;
	}

	@ApiModelProperty("给乐商户合作协议（非必填）")
	public String getContractNoPicUrl() {
		return contractNoPicUrl;
	}

	public void setContractNoPicUrl(String contractNoPicUrl) {
		this.contractNoPicUrl = contractNoPicUrl;
	}
	
	@ApiModelProperty("收银台/前台照（必填）")
	public String getCashierDeskPicUrl() {
		return cashierDeskPicUrl;
	}

	public void setCashierDeskPicUrl(String cashierDeskPicUrl) {
		this.cashierDeskPicUrl = cashierDeskPicUrl;
	}

	@ApiModelProperty("经营产品/经营场地（必填）")
	public String getOperateProductPicUrl() {
		return operateProductPicUrl;
	}

	public void setOperateProductPicUrl(String operateProductPicUrl) {
		this.operateProductPicUrl = operateProductPicUrl;
	}

	@ApiModelProperty("经营场地/大楼外观照（必填）")
	public String getOperateFieldPicUrl() {
		return operateFieldPicUrl;
	}

	public void setOperateFieldPicUrl(String operateFieldPicUrl) {
		this.operateFieldPicUrl = operateFieldPicUrl;
	}

	@ApiModelProperty("对私法人银行卡正面（必填）")
	public String getPrilpBankFrontPicUrl() {
		return prilpBankFrontPicUrl;
	}

	public void setPrilpBankFrontPicUrl(String prilpBankFrontPicUrl) {
		this.prilpBankFrontPicUrl = prilpBankFrontPicUrl;
	}

	@ApiModelProperty("对私法人银行卡反面（必填）")
	public String getPrilpBankBackPicUrl() {
		return prilpBankBackPicUrl;
	}

	public void setPrilpBankBackPicUrl(String prilpBankBackPicUrl) {
		this.prilpBankBackPicUrl = prilpBankBackPicUrl;
	}

	@ApiModelProperty("对私非法人身份证正面（必填）")
	public String getPrinlpIdCardFrontPicUrl() {
		return prinlpIdCardFrontPicUrl;
	}

	public void setPrinlpIdCardFrontPicUrl(String prinlpIdCardFrontPicUrl) {
		this.prinlpIdCardFrontPicUrl = prinlpIdCardFrontPicUrl;
	}

	@ApiModelProperty("对私非法人身份证反面（必填）")
	public String getPrinlpIdCardBackPicUrl() {
		return prinlpIdCardBackPicUrl;
	}

	public void setPrinlpIdCardBackPicUrl(String prinlpIdCardBackPicUrl) {
		this.prinlpIdCardBackPicUrl = prinlpIdCardBackPicUrl;
	}

	@ApiModelProperty("对私非法人银行卡正面（必填）")
	public String getPrinlpBankFrontPicUrl() {
		return prinlpBankFrontPicUrl;
	}

	public void setPrinlpBankFrontPicUrl(String prinlpBankFrontPicUrl) {
		this.prinlpBankFrontPicUrl = prinlpBankFrontPicUrl;
	}

	@ApiModelProperty("对私非法人银行卡反面（必填）")
	public String getPrinlpBankBackPicUrl() {
		return prinlpBankBackPicUrl;
	}

	public void setPrinlpBankBackPicUrl(String prinlpBankBackPicUrl) {
		this.prinlpBankBackPicUrl = prinlpBankBackPicUrl;
	}

	@ApiModelProperty("预先绑定二维码")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	
	
	
	
}
