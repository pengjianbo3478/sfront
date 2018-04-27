package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class BaseCommand {


    @ApiModelProperty(value = "提成对象类型。1：省级运营商; 2：市级运营商; 3：县级运营商。4：业务员机构" +
            "5：联盟商家商户; 6：员工,店长,会员; 7：企业协会机构; 8：积分机构; 9：电子商城" +
            "10：积分商城; 11：政府机构; 12：银行机构; 13：支付公司。App登录后不需要理会", hidden = true)
    private String ownerType;

    @ApiModelProperty(value = "代理商id。App登录后不需要理会", hidden = true)
    private String ownerId;
    
    @ApiModelProperty(value = "商家：0：结算款; 其它：1：分润款，默认值", hidden = true, example = "1")
	private String accType = "1";

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
    
}
