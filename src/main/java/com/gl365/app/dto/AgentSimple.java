package com.gl365.app.dto;

import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.handler.PicHandler;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/16.
 */
public class AgentSimple {
    private String id;
    private AgentType agentType;
    private String companyName;
    private String name;
    private String mobile;
    private String avatarUrl;
    private Integer province;
    private Integer city;
    private Integer district;
    private String licenseNo;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    public AgentSimple(){

    }


    public AgentSimple(Agent agent) {
        this.id = agent.getId();
        this.agentType = agent.getAgentType();
        this.companyName = agent.getCompanyName();
        this.name = agent.getName();
        this.mobile = agent.getMobile();
        this.avatarUrl = PicHandler.addPrefixUrlStatic(agent.getAvatarUrl());
        this.province = agent.getProvince();
        this.city = agent.getCity();
        this.district = agent.getDistrict();

    }

    @ApiModelProperty(value = "代理商id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "代理商类型。0：平台；2：市级代理；3：县级代理；4：业务员；7：企业协会")
    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }

    @ApiModelProperty(value = "公司名称")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @ApiModelProperty(value = "市县级代理联系人姓名或业务员姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "市县级代理联系人电话号码或业务员电话号码")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ApiModelProperty(value = "代理商头像")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @ApiModelProperty(value = "市县级代理或业务员所处省份")
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @ApiModelProperty(value = "市县级代理或业务员所处城市")
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @ApiModelProperty(value = "市县级代理或业务员所处区域")
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @ApiModelProperty(value = "营业执照号")
    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
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
}
