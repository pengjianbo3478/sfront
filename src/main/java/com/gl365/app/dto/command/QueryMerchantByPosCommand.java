package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/13.
 */
public class QueryMerchantByPosCommand{

    @ApiModelProperty(value = "登录业务员的id。APP登录后不需要理会")
    private String agentId;

    @ApiModelProperty(value = "POS机终端号", required = true)
    private String deviceId;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
