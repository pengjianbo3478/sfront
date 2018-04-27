package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/5.
 */
public class FindMyCustomerServiceCommand{

    @ApiModelProperty(value = "售后服务类型。todo：待处理，doing：处理中，completed：已完成，all：所有", allowableValues = "todo, doing, completed, all", dataType = "java.lang.String")
    private String status;

    @ApiModelProperty(value = "当前登录用户id", hidden = true)
    private String agentId;

    @ApiModelProperty("搜索框查询参数。")
    private String searchInput;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }
}
