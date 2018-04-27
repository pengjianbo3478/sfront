package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.PartnerType;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wangmeihua on 2017/6/10.
 */
public class MyMerchantProfitList {
	private String id;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime cooperationTime;
	private String name;
	private String companyName;
	private String externalOperationName;
	private Long txnCount;
	private BigDecimal totalProfitFee;
	private BigDecimal txnAmount;
	private String mobile;
	private String avatarUrl;
	private PartnerType partnerType;
	private String exCode;
	// 代理区域
	private String agentAreaid;

	// 用户信息
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "用户头像")
	private String photo;
	@ApiModelProperty(value = "用户注册时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registerTime;
	private Integer linkType;
	private String isArea;
	
	@ApiModelProperty(value = "是否代理区域业务员：1是;0否")
	public String getIsArea() {
		return isArea;
	}

	public void setIsArea(String isArea) {
		this.isArea = isArea;
	}
	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	@ApiModelProperty(value = "id ,支付流水号")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "合作时间或交易日期")
	public LocalDateTime getCooperationTime() {
		return cooperationTime;
	}

	public void setCooperationTime(LocalDateTime cooperationTime) {
		this.cooperationTime = cooperationTime;
	}

	@ApiModelProperty(value = "代理商名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "公司名称")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@ApiModelProperty(value = "交易笔数")
	public Long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
	}

	@ApiModelProperty(value = "我的利润")
	public String getTotalProfitFee() {
		return BigDecimaluitl.setScaleStr(totalProfitFee);
	}

	public void setTotalProfitFee(BigDecimal totalProfitFee) {
		this.totalProfitFee = totalProfitFee;
	}

	@ApiModelProperty(value = "总的交易金额")
	public String getTxnAmount() {
		return BigDecimaluitl.setScaleStr(txnAmount);
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	@ApiModelProperty(value = "手机号")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ApiModelProperty(value = "头像")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@ApiModelProperty(value = "伙伴类型。1：商户；3：县级代理；4：业务员；6：用户")
	public PartnerType getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	@ApiModelProperty(value = "业务员推广码")
	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	@ApiModelProperty(value = "代理区域")
	public String getAgentAreaid() {
		return agentAreaid;
	}

	public void setAgentAreaid(String agentAreaid) {
		this.agentAreaid = agentAreaid;
	}

	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDateTime getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}
}
