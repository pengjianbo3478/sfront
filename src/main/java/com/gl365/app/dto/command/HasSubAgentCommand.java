package com.gl365.app.dto.command;


import com.gl365.app.dto.enum_type.AgentType;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/15.
 */
public class HasSubAgentCommand {
    private List<String> agentIds;;
    private AgentType type;

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
}
