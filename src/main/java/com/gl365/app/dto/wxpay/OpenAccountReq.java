package com.gl365.app.dto.wxpay;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class OpenAccountReq extends BaseEntity {
	private static final long serialVersionUID = -2901166051558574313L;

	@ApiModelProperty(value = "接口流水号:UUID", hidden = true)
	private String pono;

	@ApiModelProperty(value = "用户类型:2：市级运营商3：县级运营商4：业务员机构5：联盟商家商户6：员工,店长,会员7：企业协会机构", hidden = true)
	private String puserType;

	@ApiModelProperty(value = "用户Id", hidden = true)
	private String puserNo;

	@ApiModelProperty(value = "接入类型：1：PC，2：WAP，3：APP", hidden = true)
	private String accessType = "3";

	@ApiModelProperty(value = "账户类型（1：一般户（默认是一般户）， 2：贷款，3：过渡户）", hidden = true)
	private String type = "1";

	@Length(max = 1, message = "开户性质不能大于1")
	@ApiModelProperty(value = "开户性质，0：个人，1：企业",required = true, example = "0")
	private String property ;

	@ApiModelProperty(value = "是否外籍，0：不是，1：是", hidden = true, example = "0")
	private String foreignSign = "0";

	@ApiModelProperty(value = "证件类型；1：身份证，2:机构代码证，3: 护照", hidden = true, example = "1")
	private String cerType = "1";

	@NotNull(message = "证件编号不能为空")
	@Length(max = 50, message = "证件编号不能大于50")
	@ApiModelProperty(value = "证件编号", required = true, example = "41022198001016325")
	private String cerNo;

	@ApiModelProperty(value = "认证标志；（企业必填）1:已验证；0：未验证", hidden = true, example = "1")
	private String verifyFlag = "1";

	@Length(max = 150, message = "真实姓名不能大于150")
	@ApiModelProperty(value = "真实姓名（个人，必填）", example = "李齐聚")
	private String userName;

	@Length(max = 150, message = "企业名称不能大于150")
	@ApiModelProperty(value = "企业名称（企业，必填）", example = "深圳给乐科技")
	private String corpName;

	@Length(max = 50, message = "营业执照编号不能大于50")
	@ApiModelProperty(value = "营业执照编号（企业，必填）", example = "GL400020172000142")
	private String busLicNo;

	@Length(max = 50, message = "税务登记号不能大于50")
	@ApiModelProperty(value = "税务登记号（企业，必填）", example = "SWGL400020172000142")
	private String taxRegCerNo;

	@Length(max = 300, message = "企业注册地址不能大于300")
	@ApiModelProperty(value = "企业注册地址", example = "深圳市南山区粤海街道高新南一道9号中国科技开发院3A层3A06室")
	private String regAddress;

	@Length(max = 21, message = "企业经营期限不能大于21")
	@ApiModelProperty(value = "企业经营期限： YYYY-MM-DD-YYYY-MM-DD", example = "2008-08-08-2088-08-08")
	private String operatingPeriod;

	@Length(max = 21, message = "企业成立日期不能大于21")
	@ApiModelProperty(value = "企业成立日期： YYYY-MM-DD-YYYY-MM-DD", example = "2008-08-08 08:08:08")
	private String establishDate;

	@Length(max = 100, message = "企业经营范围不能大于100")
	@ApiModelProperty(value = " 企业经营范围，文字描述", example = "商务信息咨询（不含限制项目）；从事广告业务。信息服务业务")
	private String businessScope;

	@Length(max = 300, message = "企业登记机关不能大于300")
	@ApiModelProperty(value = " 企企业登记机关", example = "深圳工商局")
	private String registrationAuthority;

	@Length(max = 300, message = "企业类型，文字内容不能大于300")
	@ApiModelProperty(value = "企业类型，文字内容", example = "有限责任公司")
	private String corpType;

	@Length(max = 100, message = "开户许可证，数字+字符编号不能大于100")
	@ApiModelProperty(value = "开户许可证，数字+字符编号", example = "400020172000142SWGL")
	private String corpAccPermission;

	@Length(max = 150, message = "法人姓名不能大于150")
	@ApiModelProperty(value = "法人姓名（企业，必填）", example = "李齐聚")
	private String legalPerName;

	@Length(max = 18, message = "法人身份证号不能大于18")
	@ApiModelProperty(value = "法人身份证号（企业，必填）", example = "41022198001016325")
	private String legalPerNo;

	@Length(max = 100, message = "对公开户行不能大于100")
	@ApiModelProperty(value = "对公开户行（企业，必填）", example = "中国银行")
	private String corpBankName;

	@Length(max = 100, message = "对公账户不能大于100")
	@ApiModelProperty(value = "对公账户（企业，必填）", example = "622500001800041445")
	private String corpBankAcc;

	@Length(max = 15, message = "企业为法人手机号不能大于15")
	@ApiModelProperty(value = "手机号(企业为法人手机号)", example = "13512345678")
	private String mobile;

	@Length(max = 100, message = "企业为法人邮箱不能大于100")
	@ApiModelProperty(value = "邮箱(企业为法人邮箱)", example = "13512345678@166.com")
	private String email;

	@Length(max = 50, message = "企业联系人姓名不能大于50")
	@ApiModelProperty(value = "企业联系人姓名（企业，必填）", example = "李白")
	private String busContacts;

	@Length(max = 18, message = "企业联系人身份证号不能大于18")
	@ApiModelProperty(value = "企业联系人身份证号（企业，必填）", example = "41022198001116625")
	private String busContactsPerNo;

	@Length(max = 15, message = "企业联系人电话不能大于15")
	@ApiModelProperty(value = "企业联系人电话", example = "075512345678")
	private String busContactsTel;

	@Length(max = 15, message = "企业联系人手机号不能大于15")
	@ApiModelProperty(value = "企业联系人手机号（企业，必填）", example = "13512345678")
	private String busContactsMobile;

	@Length(max = 100, message = "企业联系人邮箱不能大于100")
	@ApiModelProperty(value = "企业联系人邮箱", example = "13512345678@163.com")
	private String busContactsEmail;

	@Length(max = 200, message = "企业联系人邮箱不能大于200")
	@ApiModelProperty(value = " 附言", example = "200 附言")
	private String postscript;
	
	@ApiModelProperty(value = "商家：0：结算款; 其它：1：分润款，默认值", hidden = true, example = "1")
	private String accType = "1";
	
	@ApiModelProperty(value = "开户渠道", hidden = true, example = "10003")
	private String openChannel = "10003";

	
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public String getPuserType() {
		return puserType;
	}

	public void setPuserType(String puserType) {
		this.puserType = puserType;
	}

	public String getPuserNo() {
		return puserNo;
	}

	public void setPuserNo(String puserNo) {
		this.puserNo = puserNo;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getForeignSign() {
		return foreignSign;
	}

	public void setForeignSign(String foreignSign) {
		this.foreignSign = foreignSign;
	}

	public String getCerType() {
		return cerType;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	public String getCerNo() {
		return cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getBusLicNo() {
		return busLicNo;
	}

	public void setBusLicNo(String busLicNo) {
		this.busLicNo = busLicNo;
	}

	public String getTaxRegCerNo() {
		return taxRegCerNo;
	}

	public void setTaxRegCerNo(String taxRegCerNo) {
		this.taxRegCerNo = taxRegCerNo;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getOperatingPeriod() {
		return operatingPeriod;
	}

	public void setOperatingPeriod(String operatingPeriod) {
		this.operatingPeriod = operatingPeriod;
	}

	public String getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getRegistrationAuthority() {
		return registrationAuthority;
	}

	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public String getCorpAccPermission() {
		return corpAccPermission;
	}

	public void setCorpAccPermission(String corpAccPermission) {
		this.corpAccPermission = corpAccPermission;
	}

	public String getLegalPerName() {
		return legalPerName;
	}

	public void setLegalPerName(String legalPerName) {
		this.legalPerName = legalPerName;
	}

	public String getLegalPerNo() {
		return legalPerNo;
	}

	public void setLegalPerNo(String legalPerNo) {
		this.legalPerNo = legalPerNo;
	}

	public String getCorpBankName() {
		return corpBankName;
	}

	public void setCorpBankName(String corpBankName) {
		this.corpBankName = corpBankName;
	}

	public String getCorpBankAcc() {
		return corpBankAcc;
	}

	public void setCorpBankAcc(String corpBankAcc) {
		this.corpBankAcc = corpBankAcc;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getBusContactsTel() {
		return busContactsTel;
	}

	public void setBusContactsTel(String busContactsTel) {
		this.busContactsTel = busContactsTel;
	}

	public String getBusContactsMobile() {
		return busContactsMobile;
	}

	public void setBusContactsMobile(String busContactsMobile) {
		this.busContactsMobile = busContactsMobile;
	}

	public String getBusContactsEmail() {
		return busContactsEmail;
	}

	public void setBusContactsEmail(String busContactsEmail) {
		this.busContactsEmail = busContactsEmail;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public String getOpenChannel() {
		return openChannel;
	}

	public void setOpenChannel(String openChannel) {
		this.openChannel = openChannel;
	}

}
