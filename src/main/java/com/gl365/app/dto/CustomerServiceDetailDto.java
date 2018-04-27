package com.gl365.app.dto;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/7.
 */
public class CustomerServiceDetailDto {

    @ApiModelProperty(value = "商户名称", dataType = "java.lang.String")
    private String merchantName;

    @ApiModelProperty(value = "商户联系人名字", dataType = "java.lang.String")
    private String merchantContactName;

    @ApiModelProperty(value = "商户联系人电话", dataType = "java.lang.String")
    private String merchantContactMobile;

    @ApiModelProperty(hidden = true)
    private Integer merchantProvince;

    @ApiModelProperty(value = "商户所在省份", dataType = "java.lang.String")
    private String merchantProvinceDesc;

    @ApiModelProperty(hidden = true)
    private Integer merchantCity;

    @ApiModelProperty(value = "商户所在城市", dataType = "java.lang.String")
    private String merchantCityDesc;

    @ApiModelProperty(hidden = true)
    private Integer merchantDistrict;

    @ApiModelProperty(value = "商户所在地区", dataType = "java.lang.String")
    private String merchantDistrictDesc;

    @ApiModelProperty(value = "商户详细地址", dataType = "java.lang.Integer")
    private String merchantDetailedAddress;

    @ApiModelProperty(value = "售后流程进度")
    private List<CustomerServiceRequestFlowDto> flows;


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantContactName() {
        return merchantContactName;
    }

    public void setMerchantContactName(String merchantContactName) {
        this.merchantContactName = merchantContactName;
    }

    public String getMerchantContactMobile() {
        return merchantContactMobile;
    }

    public void setMerchantContactMobile(String merchantContactMobile) {
        this.merchantContactMobile = merchantContactMobile;
    }

    public Integer getMerchantProvince() {
        return merchantProvince;
    }

    public void setMerchantProvince(Integer merchantProvince) {
        this.merchantProvince = merchantProvince;
    }

    public Integer getMerchantCity() {
        return merchantCity;
    }

    public void setMerchantCity(Integer merchantCity) {
        this.merchantCity = merchantCity;
    }

    public Integer getMerchantDistrict() {
        return merchantDistrict;
    }

    public void setMerchantDistrict(Integer merchantDistrict) {
        this.merchantDistrict = merchantDistrict;
    }

    public String getMerchantDetailedAddress() {
        return merchantDetailedAddress;
    }

    public void setMerchantDetailedAddress(String merchantDetailedAddress) {
        this.merchantDetailedAddress = merchantDetailedAddress;
    }

    public List<CustomerServiceRequestFlowDto> getFlows() {
        return flows;
    }

    public void setFlows(List<CustomerServiceRequestFlowDto> flows) {
        this.flows = flows;
    }

    public String getMerchantProvinceDesc() {
        return merchantProvinceDesc;
    }

    public void setMerchantProvinceDesc(String merchantProvinceDesc) {
        this.merchantProvinceDesc = merchantProvinceDesc;
    }

    public String getMerchantCityDesc() {
        return merchantCityDesc;
    }

    public void setMerchantCityDesc(String merchantCityDesc) {
        this.merchantCityDesc = merchantCityDesc;
    }

    public String getMerchantDistrictDesc() {
        return merchantDistrictDesc;
    }

    public void setMerchantDistrictDesc(String merchantDistrictDesc) {
        this.merchantDistrictDesc = merchantDistrictDesc;
    }
}
