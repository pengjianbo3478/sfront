package com.gl365.app.dto.command;

import com.gl365.app.dto.Command;
import com.gl365.app.dto.enum_type.AgentType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/8.
 */
public class FindMyAgentPartnerCommand {
    private List<String> agentIds;
    private AgentType agentType;
    private AgentType otherAgentType;
    private String upstreamAgentId;
    private String upstreamNpAgentId;
    private String crossAgentId;
    @ApiModelProperty(value = "贡献方的代理商类型。0:平台 ，2：市级代理商；3：县级代理商；4：业务员；7：企业协会机构",required = true)
    public AgentType getOtherAgentType() {
        return otherAgentType;
    }

    public void setOtherAgentType(AgentType otherAgentType) {
        this.otherAgentType = otherAgentType;
    }

    @ApiModelProperty(value = "当前代理商Id",required = false)
    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }

    @ApiModelProperty(value = "当前代理商类型。0:平台 ，2：市级代理商；3：县级代理商；4：业务员；7：企业协会机构",required = true)
    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }
    @ApiModelProperty(value = "直接上级代理商id",required = false)
    public String getUpstreamAgentId() {
        return upstreamAgentId;
    }

    public void setUpstreamAgentId(String upstreamAgentId) {
        this.upstreamAgentId = upstreamAgentId;
    }
    @ApiModelProperty(value = "非业务员上级代理商id",required = false)
    public String getUpstreamNpAgentId() {
        return upstreamNpAgentId;
    }

    public void setUpstreamNpAgentId(String upstreamNpAgentId) {
        this.upstreamNpAgentId = upstreamNpAgentId;
    }

	public String getCrossAgentId() {
		return crossAgentId;
	}

	public void setCrossAgentId(String crossAgentId) {
		this.crossAgentId = crossAgentId;
	}
    
}
