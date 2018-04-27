package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/13.
 */
public class InstallPosCommand{

    @ApiModelProperty(value = "app登录之后不需要理会", hidden = true)
    private String agentId;

    @ApiModelProperty(value = "pos机id", required = true)
    private String posId;

    @ApiModelProperty(value = "pos机SN号", required = true)
    private String posSn;


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

    public String getPosSn() {
        return posSn;
    }

    public void setPosSn(String posSn) {
        this.posSn = posSn;
    }

}
