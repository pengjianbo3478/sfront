package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/6.
 */
public class GetMerchantApplicationDetailRequest {
	 private String applicationId;
	 
	 private Long linkMerchantId;

    private String agentId;

    @ApiModelProperty(value = "申请id")
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @ApiModelProperty(value = "代理商id", hidden = true)
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
    @ApiModelProperty(value = "连锁店Id")
	public Long getLinkMerchantId() {
		return linkMerchantId;
	}

	public void setLinkMerchantId(Long linkMerchantId) {
		this.linkMerchantId = linkMerchantId;
	}
    
    
}
