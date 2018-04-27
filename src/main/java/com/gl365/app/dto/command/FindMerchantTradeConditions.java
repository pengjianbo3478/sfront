package com.gl365.app.dto.command;

import com.gl365.app.dto.Command;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class FindMerchantTradeConditions{
    private String merchantId;
    private String agentId;

    @ApiModelProperty(value = "商户列表中商户id",required = true)
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @ApiModelProperty(value = "代理商列表中的代理商id",required = true)
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
