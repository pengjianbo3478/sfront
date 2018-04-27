package com.gl365.app.dto.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gl365.app.dto.Agent;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.enum_type.AgentType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by caoyilong on 2017/6/2.
 */
public class AgentLoginDto implements Serializable {
	
	private static final long serialVersionUID = -243273357905059945L;

	private String id;

	private AgentType agentType;

	private AgentStatus status;

	private String mobile;

	private Integer actingAreaId;

	private String token;

	private String name;

	private String avatarUrl;

	private Integer city;
	
	private Agent upstreamAgent;

    private Agent upstreamNonPersonalAgent;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;
	
	private String openId;

	@ApiModelProperty("开户Openid")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@ApiModelProperty("用户的id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "用户类型，0:平台（实际业务中不存在此类型代理)、2：市级代理、3：县级代理、4：业务员、7：企业协会", dataType = "java.lang.Integer")
	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

	@ApiModelProperty(value = "用户状态，1：待审核，2：已审核", dataType = "java.lang.Integer")
	public AgentStatus getStatus() {
		return status;
	}

	public void setStatus(AgentStatus status) {
		this.status = status;
	}


	@ApiModelProperty("用户手机号，即登录名")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ApiModelProperty("代理区域")
	public Integer getActingAreaId() {
		return actingAreaId;
	}

	public void setActingAreaId(Integer actingAreaId) {
		this.actingAreaId = actingAreaId;
	}

	@ApiModelProperty("token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@ApiModelProperty("名字")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty("头像")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@ApiModelProperty("所在市")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Agent getUpstreamAgent() {
		return upstreamAgent;
	}

	public void setUpstreamAgent(Agent upstreamAgent) {
		this.upstreamAgent = upstreamAgent;
	}

	public Agent getUpstreamNonPersonalAgent() {
		return upstreamNonPersonalAgent;
	}

	public void setUpstreamNonPersonalAgent(Agent upstreamNonPersonalAgent) {
		this.upstreamNonPersonalAgent = upstreamNonPersonalAgent;
	}
	
	
}
