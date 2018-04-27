package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class UpdateNickNameCommand {

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(hidden = true)
    private String agentId;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
