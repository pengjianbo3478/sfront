package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/16.
 */
public class UpdateMobileCommand {

    @ApiModelProperty(value = "新手机号码", required = true)
    private String newMobile;

    @ApiModelProperty(value = "新手机的验证码", required = true)
    private String newMobileCode;

    @ApiModelProperty(hidden = true)
    private String agentId;

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getNewMobileCode() {
        return newMobileCode;
    }

    public void setNewMobileCode(String newMobileCode) {
        this.newMobileCode = newMobileCode;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
