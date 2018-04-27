package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/5.
 */
public class AgentApplication extends Application {

    private Integer upstreamDevelopedNumAgents;

    @ApiModelProperty(value = "上级发展代理数量")
    public Integer getUpstreamDevelopedNumAgents() {
        return upstreamDevelopedNumAgents;
    }

    public void setUpstreamDevelopedNumAgents(Integer upstreamDevelopedNumAgents) {
        this.upstreamDevelopedNumAgents = upstreamDevelopedNumAgents;
    }
}

