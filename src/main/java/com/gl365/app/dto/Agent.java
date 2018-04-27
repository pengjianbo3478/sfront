package com.gl365.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.enum_type.AgentType;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Chen, Zhuhui
 */
public class Agent  implements Serializable {
	
	private static final long serialVersionUID = -8381746997043095804L;

	private String id;

    private AgentType agentType;

    private String companyName;

    private String licenseNo;

    private String taxRegNo;

    private String orgCodeNo;

    private String name;

    private String password;

    private String idCardNo;

    private String mobile;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer actingAreaId;

    private String detailedAddress;

    private Agent upstreamAgent;

    private Agent upstreamNonPersonalAgent;

    private Long leftIndex;

    private Long rightIndex;

    private Integer personalAgentLimits;

    private AgentStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String grantNoteNo;

    private String avatarUrl;

    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    
    private String openId;

    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@ApiModelProperty(value = "代理商头像")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    @ApiModelProperty(value = "营业执照号")
    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    @ApiModelProperty(value = "税务登记证号")
    public String getTaxRegNo() {
        return taxRegNo;
    }

    public void setTaxRegNo(String taxRegNo) {
        this.taxRegNo = taxRegNo;
    }
    @ApiModelProperty(value = "机构代码证号")
    public String getOrgCodeNo() {
        return orgCodeNo;
    }

    public void setOrgCodeNo(String orgCodeNo) {
        this.orgCodeNo = orgCodeNo;
    }

    @ApiModelProperty(value = "市县级代理联系人姓名或业务员姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(hidden = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @ApiModelProperty(value = "市县级代理联系人身份证号或业务员身份证号")
    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
    @ApiModelProperty(value = "市县级代理联系人电话号码或业务员电话号码")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @ApiModelProperty(value = "市县级代理或业务员的详细地址")
    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
    @ApiModelProperty(value = "代理/业务员所属的上级代理")
    public Agent getUpstreamAgent() {
        return upstreamAgent;
    }

    public void setUpstreamAgent(Agent upstreamAgent) {
        this.upstreamAgent = upstreamAgent;
    }

    @JsonIgnore
    public Long getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(Long leftIndex) {
        this.leftIndex = leftIndex;
    }

    @JsonIgnore
    public Long getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(Long rightIndex) {
        this.rightIndex = rightIndex;
    }
    @ApiModelProperty(value = "用户状态。1：待审核；2：已审核")
    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }
    @ApiModelProperty(value = "记录创建时间")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    @ApiModelProperty(value = "记录更新时间")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    @ApiModelProperty(value = "业务员数量限制")
    public Integer getPersonalAgentLimits() {
        return personalAgentLimits;
    }

    public void setPersonalAgentLimits(Integer personalAgentLimits) {
        this.personalAgentLimits = personalAgentLimits;
    }
    @ApiModelProperty(value = "市县级代理或业务员所处省份")
    public Integer getProvince() {
        return province;
    }
    @ApiModelProperty(value = "市县级代理或业务员所处城市")
    public Integer getCity() {
        return city;
    }
    @ApiModelProperty(value = "市县级代理或业务员所处区域")
    public Integer getDistrict() {
        return district;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }
    @ApiModelProperty(value = "代理区域")
    public Integer getActingAreaId() {
        return actingAreaId;
    }

    public void setActingAreaId(Integer actingAreaId) {
        this.actingAreaId = actingAreaId;
    }
    @ApiModelProperty(value = "开户许可证号")
    public String getGrantNoteNo() {
        return grantNoteNo;
    }

    public void setGrantNoteNo(String grantNoteNo) {
        this.grantNoteNo = grantNoteNo;
    }
    @ApiModelProperty(value = "上级非业务员类型代理商ID。")
    public Agent getUpstreamNonPersonalAgent() {
        return upstreamNonPersonalAgent;
    }

    public void setUpstreamNonPersonalAgent(Agent upstreamNonPersonalAgent) {
        this.upstreamNonPersonalAgent = upstreamNonPersonalAgent;
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


    public Agent() {
    }

    public Agent(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id='" + id + '\'' +
                ", agentType=" + agentType +
                ", companyName='" + companyName + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                ", taxRegNo='" + taxRegNo + '\'' +
                ", orgCodeNo='" + orgCodeNo + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", actingAreaId=" + actingAreaId +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", upstreamAgent=" + upstreamAgent +
                ", upstreamNonPersonalAgent=" + upstreamNonPersonalAgent +
                ", leftIndex=" + leftIndex +
                ", rightIndex=" + rightIndex +
                ", personalAgentLimits=" + personalAgentLimits +
                ", status=" + status +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                ", grantNoteNo='" + grantNoteNo + '\'' +
                '}';
    }
}
