package com.gl365.app.dto.merchant;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author caoyilong
 * @Description 商户申请详情 Created by caoyilong on 2017/5/19.
 */
public class NoBenefitMerchantApplicationDto {

	@ApiModelProperty(value = "商户Id")
	private String merchantId;

	@ApiModelProperty(value = "给乐商户号")
	private String geileMerchantSn;

	@ApiModelProperty(value = "申请内容")
	private String reason;

	@ApiModelProperty(value = "商户名称")
	private String name;
	
	@ApiModelProperty(value = "商户简称")
	private String externalOperationName;

	@ApiModelProperty(value = "商户联系人名称")
	private String contactName;

	@ApiModelProperty(value = "商户联系人电话")
	private String contactMobile;

	@ApiModelProperty(value = "申请人名称")
	private String agentName;

	@ApiModelProperty(value = "申请人电话")
	private String agentMobile;

	@ApiModelProperty(value = "商户所在省份")
	private Integer province;

	@ApiModelProperty(value = "商户所在市")
	private Integer city;

	@ApiModelProperty(value = "商户所在地区")
	private Integer district;
	
	@ApiModelProperty(value = "商户所在省份")
	private String provinceDesc;
	@ApiModelProperty(value = "商户所在市")
    private String cityDesc;
    @ApiModelProperty(value = "商户所在地区")
    private String districtDesc;

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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getProvinceDesc() {
		return provinceDesc;
	}

	public void setProvinceDesc(String provinceDesc) {
		this.provinceDesc = provinceDesc;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}

	public String getDistrictDesc() {
		return districtDesc;
	}

	public void setDistrictDesc(String districtDesc) {
		this.districtDesc = districtDesc;
	}

}
