package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 企业信息表
 * Created by caoyilong on 2017/5/23.
 */
public class Company {

	@ApiModelProperty(value = "记录id值",hidden=true)
	private String id;

	@ApiModelProperty(value = "商户名称",example="深圳给乐信息科技有限公司")
	private String name;
	/**
	 * 企业类型
	 */
	@ApiModelProperty(value = "企业类型",example="有限责任公司")
	private String econKind;

	@ApiModelProperty(value = "地址",example="深圳市南山区粤海街道高新南一道9号中国科技开发院3A层3A06室")
	private String address;
	/**
	 * 企业注册号
	 */
	@ApiModelProperty(value = "企业注册号",example="440301105274313")
	private String regNo;
	/**
	 * 企业经营范围
	 */
	@ApiModelProperty(value = "企业经营范围",example="商务信息咨询（不含限制项目）；从事广告业务。信息服务业务（仅限广东省互联网信息服务业务，不含新闻、出版、教育、医疗保健、药品、医疗器械、电子公告以及其他按法律、法规规定需前置审批或专项审批的服务项目）（《增值电信业务经营许可证》有效期至2016年10月8日）。；")
	private String scope;
	/**
	 * 法人
	 */
	@ApiModelProperty(value = "法人",example="李远波")
	private String operName;
	
	/**
	 * 组织机构号码
	 */
	@ApiModelProperty(value = "组织机构号码",example="571973674")
	private String orgNo;
	
	/**
	 * 统一社会信用代码
	 */
	@ApiModelProperty(value = "统一社会信用代码",example="91440300571973674L")
	private String creditNo;
	
	/**
	 * 省
	 */
	@ApiModelProperty(value = "省",example="GD")
	private String province;
	
	/**
	 * 企业编号
	 */
	@ApiModelProperty(value = "企业编号",example="4d4d27e2-bfc1-4b00-bd21-aa40561fa503")
	private String companySn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEconKind() {
		return econKind;
	}

	public void setEconKind(String econKind) {
		this.econKind = econKind;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCompanySn() {
		return companySn;
	}

	public void setCompanySn(String companySn) {
		this.companySn = companySn;
	}
}
