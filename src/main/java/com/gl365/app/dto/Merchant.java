package com.gl365.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.MerchantStatus;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by caoyilong on 2017/5/31.
 */
public class Merchant {
	private Agent agent;

	private String id;

	private String merchantSn;

	private String geileMerchantSn;

	private String contactName;

	private String contactMobile;

	private String name;
	
	private String externalOperationName;

	private String licenseNo;

	private String legalPersonName;

	private Integer industry;

	private String industryName;

	private Integer subIndustry;

	private String subIndustryName;

	private Integer province;

	private Integer city;

	private Integer district;

	private String detailedAddress;

	private BigDecimal commissionRatio;

	private BigDecimal saleRate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	private String payOrgCode;

	private String contractNo;

	private MerchantStatus merchantStatus;
	
	private Integer linkType;
	
	private Integer noBenefit;
	
	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	@ApiModelProperty(value = "合同编号")
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Agent getAgent() {
		return agent;
	}

	public String getGeileMerchantSn() {
		return geileMerchantSn;
	}

	public void setGeileMerchantSn(String geileMerchantSn) {
		this.geileMerchantSn = geileMerchantSn;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@ApiModelProperty("记录ID值")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty("商户号")
	public String getMerchantSn() {
		return merchantSn;
	}

	public void setMerchantSn(String merchantSn) {
		this.merchantSn = merchantSn;
	}

	@ApiModelProperty("商户联系人姓名")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@ApiModelProperty("商户联系人手机号")
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

	@ApiModelProperty("商户所处省份")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@ApiModelProperty("商户所处城市")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@ApiModelProperty("商户所处区域")
	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@ApiModelProperty("详细地址")
	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
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

	@ApiModelProperty("创建时间")
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@ApiModelProperty("更新时间")
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@ApiModelProperty("进件受理支付公司ID（目前只有付费通，写死1001）")
	public String getPayOrgCode() {
		return payOrgCode;
	}

	public void setPayOrgCode(String payOrgCode) {
		this.payOrgCode = payOrgCode;
	}

	@ApiModelProperty(value = "行业名称")
	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	@ApiModelProperty(value = "细分行业名称")
	public String getSubIndustryName() {
		return subIndustryName;
	}

	public void setSubIndustryName(String subIndustryName) {
		this.subIndustryName = subIndustryName;
	}

	@ApiModelProperty(value = "商户状态1：新商户,2：曾经成功进件,3审核失败")
	public MerchantStatus getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(MerchantStatus merchantStatus) {
		this.merchantStatus = merchantStatus;
	}
	
	@ApiModelProperty(value = "商户简称")
	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}

	@ApiModelProperty(value = "0没开通免返逗；1开通免返逗")
	public Integer getNoBenefit() {
		return noBenefit;
	}

	public void setNoBenefit(Integer noBenefit) {
		this.noBenefit = noBenefit;
	}
	
	
}
