package com.gl365.app.remote.profit.command;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
public class UpdateProfitConfigCommand{

    @ApiModelProperty(value = "结算帐号对象类型。1：省级运营商; 2：市级运营商; 3：县级运营商。4：业务员机构" +
            "5：联盟商家商户; 6：员工,店长,会员; 7：企业协会机构; 8：积分机构; 9：电子商城" +
            "10：积分商城; 11：政府机构; 12：银行机构; 13：支付公司。App登陆后不需要传递", hidden = true)
    private String agentType;

    @ApiModelProperty(value = "代理商ID。App登陆后不需要传递", hidden = true)
    private String agentNo;

    @ApiModelProperty(value = "修改人id。App登陆后不需要传递", hidden = true)
    private String modifyBy;

    @ApiModelProperty("一级业务员发展商户提成率")
    private BigDecimal devMerchantFirstRate;

    @ApiModelProperty("二级业务员发展商户提成率")
    private BigDecimal devMerchantSecondRate;

    @ApiModelProperty("本级代理城市提成率")
    private BigDecimal serviceRate;

    @ApiModelProperty("下级城市基本提成率")
    private BigDecimal subordinateServiceRate;


    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

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

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}
