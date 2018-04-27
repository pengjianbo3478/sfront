package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.AgentType;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用于保存我的伙伴的查询 Created by ryan on 2017/6/19.
 */
public class PartnerDto {
	private String id;

	private String name;

	// 如果是代理商/业务员，则为company_name， 如果为商户，则为contact_name
	private String name2;

	// 如果是代理商/业务员，则为mobile，如果为商户，则为contact_mobile
	private String mobile;

	// 商户类型为null
	private AgentType agentType;

	private String avatarUrl;

	private String upstreamAgentId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;

	// 代理区域
	private String agentAreaid;
	
	private String isArea = "1";
	
	private Integer linkType;
    private String externalOperationName;

	@ApiModelProperty(value = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "如果是代理商/业务员，则为公司名称， 如果为商户，则为联系人名称")
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@ApiModelProperty(value = "如果是代理商/业务员，则为电话，如果为商户，则为联系电话")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ApiModelProperty(value = "代理商/业务员类型，商户为null")
	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

	@ApiModelProperty(value = "代理商/业务员头像，商户为null")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@ApiModelProperty(value = "上级代理ID", hidden = true)
	public String getUpstreamAgentId() {
		return upstreamAgentId;
	}

	public void setUpstreamAgentId(String upstreamAgentId) {
		this.upstreamAgentId = upstreamAgentId;
	}

	@ApiModelProperty(value = "合作时间")
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
	@ApiModelProperty(value = "代理区域")
	public String getAgentAreaid() {
		return agentAreaid;
	}

	public void setAgentAreaid(String agentAreaid) {
		this.agentAreaid = agentAreaid;
	}

	@ApiModelProperty(value = "是否代理区域业务员：1是;0否")
	public String getIsArea() {
		return isArea;
	}

	public void setIsArea(String isArea) {
		this.isArea = isArea;
	}
	
	@ApiModelProperty(value = "连锁类型：0非连锁;1统一结算连锁;2单独结算连锁")
	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	@ApiModelProperty(value = "对外经营名称")
	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}
	
	
	
	
}
