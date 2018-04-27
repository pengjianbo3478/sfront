package com.gl365.app.dto.command;


import com.gl365.app.dto.enum_type.AgentType;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/15.
 */
public class FindMyMerchantPartnerCommand {
    private List<String> agentIds;
    private AgentType type;
    private String upstreamNpAgentId;
    private String crossAgentId;

    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }

    public AgentType getType() {
        return type;
    }

    public void setType(AgentType type) {
        this.type = type;
    }

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
