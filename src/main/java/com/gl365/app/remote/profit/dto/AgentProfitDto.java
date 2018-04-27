package com.gl365.app.remote.profit.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
public class AgentProfitDto {

    @ApiModelProperty("一级业务员发展商户提成率")
    private BigDecimal devMerchantFirstRate;

    @ApiModelProperty("二级业务员发展商户提成率")
    private BigDecimal devMerchantSecondRate;

    @ApiModelProperty("本级代理城市提成率")
    private BigDecimal serviceRate;

    @ApiModelProperty("下级城市基本提成率")
    private BigDecimal subordinateServiceRate;

    public BigDecimal getDevMerchantFirstRate() {
        return devMerchantFirstRate;
    }

    public void setDevMerchantFirstRate(BigDecimal devMerchantFirstRate) {
        this.devMerchantFirstRate = devMerchantFirstRate;
    }

    public BigDecimal getDevMerchantSecondRate() {
        return devMerchantSecondRate;
    }

    public void setDevMerchantSecondRate(BigDecimal devMerchantSecondRate) {
        this.devMerchantSecondRate = devMerchantSecondRate;
    }

    public BigDecimal getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(BigDecimal serviceRate) {
        this.serviceRate = serviceRate;
    }

    public BigDecimal getSubordinateServiceRate() {
        return subordinateServiceRate;
    }

    public void setSubordinateServiceRate(BigDecimal subordinateServiceRate) {
        this.subordinateServiceRate = subordinateServiceRate;
    }
}
