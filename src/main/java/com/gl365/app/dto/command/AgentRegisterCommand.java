package com.gl365.app.dto.command;

import com.gl365.app.dto.enum_type.InvitePartnerType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by caoyilong on 2017/6/7.
 */
public class AgentRegisterCommand implements Serializable {

	private static final long serialVersionUID = -8616018567241264137L;

	@Length(max = 200, message = "公司名字过长")
	private String companyName;

	@Length(max = 50, message = "营业执照号过长")
	private String licenseNo;

	@Length(max = 50, message = "税务登记证号过长")
	private String taxRegNo;

	@Length(max = 50, message = "组织机构代码过长")
	private String orgCodeNo;

	@Length(max = 25, message = "开户许可证号过长")
	private String grantNoteNo;

	@NotNull(message = "姓名不能为空")
	@Length(max = 200, message = "姓名过长")
	private String name;

	@NotNull(message = "密码不能为空")
	@Length(max = 500, message = "密码过长")
	private String password;


	@NotNull(message = "身份证不能为空")
	@Length(max = 18, message = "身份证过长")
	private String idCardNo;

	@NotNull(message = "手机号不能为空")
	@Length(max = 20, message = "手机号过长")
	private String mobile;

	@NotNull(message = "省不能为空")
	private Integer province;

	@NotNull(message = "市不能为空")
	private Integer city;

	@NotNull(message = "区不能为空")
	private Integer district;

	private Integer actingAreaId;

	@Length(max = 200, message = "详情地址过长")
	@NotNull(message = "详情地址不能为空")
	private String detailedAddress;

	@NotNull
	private String upstreamAgentId;

	@NotNull(message = "验证码不能为空")
	private String verifyCode;

	@NotNull
	private InvitePartnerType registerType;

	@ApiModelProperty("公司名字")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@ApiModelProperty("营业执照号")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@ApiModelProperty("税务登记证号")
	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	@ApiModelProperty("组织机构代码")
	public String getOrgCodeNo() {
		return orgCodeNo;
	}

	public void setOrgCodeNo(String orgCodeNo) {
		this.orgCodeNo = orgCodeNo;
	}

	@ApiModelProperty("开户许可证")
	public String getGrantNoteNo() {
		return grantNoteNo;
	}

	public void setGrantNoteNo(String grantNoteNo) {
		this.grantNoteNo = grantNoteNo;
	}

	@ApiModelProperty("名字")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty("密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ApiModelProperty("身份证")
	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	@ApiModelProperty("手机号")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@ApiModelProperty("代理区域,注册县代理的时候需要")
	public Integer getActingAreaId() {
		return actingAreaId;
	}

	public void setActingAreaId(Integer actingAreaId) {
		this.actingAreaId = actingAreaId;
	}

	@ApiModelProperty("详细地址")
	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	@ApiModelProperty("上级代理id")
	public String getUpstreamAgentId() {
		return upstreamAgentId;
	}

	public void setUpstreamAgentId(String upstreamAgentId) {
		this.upstreamAgentId = upstreamAgentId;
	}

	@ApiModelProperty("验证码")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@ApiModelProperty("注册类型，1：县级代理、2：业务员")
	public InvitePartnerType getRegisterType() {
		return registerType;
	}

	public void setRegisterType(InvitePartnerType registerType) {
		this.registerType = registerType;
	}
}
