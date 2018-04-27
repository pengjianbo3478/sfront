package com.gl365.app.dto.users;

import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.enum_type.AgentType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by caoyilong on 2017/6/2.
 */
public class AgentBasicDto implements Serializable {

	private static final long serialVersionUID = 7203814304806063007L;

	private String id;

	private AgentType agentType;

	private AgentStatus status;

	private Integer loginFailCount;

	private String mobile;

	private Integer actingAreaId;

	private String token;

	@ApiModelProperty("用户的id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty("用户类型，0:平台（实际业务中不存在此类型代理、2：市级代理、3：县级代理、4：业务员、7：企业协会")
	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

	@ApiModelProperty("用户状态，1：待审核，2：已审核")
	public AgentStatus getStatus() {
		return status;
	}

	public void setStatus(AgentStatus status) {
		this.status = status;
	}

	@ApiModelProperty("登录失败次数")
	public Integer getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(Integer loginFailCount) {
		this.loginFailCount = loginFailCount;
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
}
