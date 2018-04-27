package com.gl365.app.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class LinkMerchantDto {
	
	@ApiModelProperty("ID")
	private Long id;

	@ApiModelProperty("商户名称")
	private String name;

	@ApiModelProperty("营业执照号")
	private String licenseNo;

	@ApiModelProperty("录创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;

	@ApiModelProperty(hidden = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	@ApiModelProperty("商户状态。1：新商户；2：曾经成功进件的商户3：商户审核拒绝（运营拒绝）")
	private Integer merchantStatus;

	@ApiModelProperty(hidden = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime auditTime;

	@ApiModelProperty("连锁类型：1统一结算连锁;2单独结算连锁")
	private Integer linkType;

	@ApiModelProperty(hidden = true)
	private String agentId;

	@ApiModelProperty("分店总数")
	private Integer merchantCount;
	
	@ApiModelProperty(hidden = true)
	private String keyword;
	
	@ApiModelProperty("统一结算RM状态:0:未有开通中数据,1:有分店开通中")
	private Integer status;

	public LinkMerchantDto() {
	}

	public LinkMerchantDto(String agentId,Integer merchantStatus,String keyword,Integer linkType) {
		this.agentId = agentId;
		this.merchantStatus = merchantStatus;
		this.keyword = keyword;
		this.linkType = linkType;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(Integer merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public LocalDateTime getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(LocalDateTime auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Integer getMerchantCount() {
		return merchantCount;
	}

	public void setMerchantCount(Integer merchantCount) {
		this.merchantCount = merchantCount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}