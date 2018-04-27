package com.gl365.app.dto;

import com.gl365.app.dto.enum_type.MerchantStatus;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/16.
 */
public class MerchantSimple {
    private String id;
    private String merchantSn;
    private String name;
    private String externalOperationName;
    private String contactName;
    private String contactMobile;
    private Integer province;
    private Integer city;
    private Integer district;

    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    
    private MerchantStatus merchantStatus;

    public MerchantSimple() {
    }

    public MerchantSimple(Merchant merchant) {
        this.id = merchant.getId();
        this.merchantSn = merchant.getMerchantSn();
        this.name = merchant.getName();
        this.contactName = merchant.getContactName();
        this.contactMobile = merchant.getContactMobile();
        this.province = merchant.getProvince();
        this.city = merchant.getCity();
        this.district = merchant.getDistrict();
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

    @ApiModelProperty("商户名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty("商户简称")
    public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
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

    @ApiModelProperty(value = "商户所处省份")
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @ApiModelProperty(value = "商户所处城市")
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @ApiModelProperty(value = "商户所处区域")
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @ApiModelProperty(value = "市县级代理或业务员所处城市")
    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }
    @ApiModelProperty(value = "市县级代理或业务员所处省份")
    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }
    @ApiModelProperty(value = "市县级代理或业务员所处区域")
    public String getDistrictDesc() {
        return districtDesc;
    }

    public void setDistrictDesc(String districtDesc) {
        this.districtDesc = districtDesc;
    }

    @ApiModelProperty(value = "商户状态1：新商户,2：曾经成功进件,3审核失败")
	public MerchantStatus getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(MerchantStatus merchantStatus) {
		this.merchantStatus = merchantStatus;
	}
    
}
