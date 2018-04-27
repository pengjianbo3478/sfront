package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/14.
 */
public class PosDetailCommand {

    @ApiModelProperty(value = "代理商id", hidden = true)
    private String agentId;

    @ApiModelProperty(value = "pos机id", required = true)
    private String posId;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }
}
