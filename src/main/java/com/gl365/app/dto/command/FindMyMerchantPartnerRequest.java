package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/10.
 */
public class FindMyMerchantPartnerRequest {
    private List<String> agentIds;

    @ApiModelProperty(value = "代理商id")
    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }
}
