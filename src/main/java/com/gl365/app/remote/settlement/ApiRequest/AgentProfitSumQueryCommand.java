package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.enum_type.AgentType;

import java.util.List;

/**
 * @author Chen, Zhuhui
 */
public class AgentProfitSumQueryCommand {

    private AgentType ownerType;

    private List<String> queryData;

    public AgentType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(AgentType ownerType) {
        this.ownerType = ownerType;
    }

    public List<String> getQueryData() {
        return queryData;
    }

    public void setQueryData(List<String> queryData) {
        this.queryData = queryData;
    }
}
