package com.gl365.app.remote.profit.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
public class QueryOneCommand {

    @ApiModelProperty("结算帐号对象类型。1：省级运营商; 2：市级运营商; 3：县级运营商。4：业务员机构" +
            "5：联盟商家商户; 6：员工,店长,会员; 7：企业协会机构; 8：积分机构; 9：电子商城" +
            "10：积分商城; 11：政府机构; 12：银行机构; 13：支付公司")
    private String agentType;

    @ApiModelProperty("代理商id")
    private String agentNo;

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
}
