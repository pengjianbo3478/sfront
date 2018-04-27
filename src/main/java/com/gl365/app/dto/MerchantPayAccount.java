package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class MerchantPayAccount {
	
	@ApiModelProperty(value = "支付机构代码：10001付费通,10003融脉微众")
	private String organCode;

	// 状态:0:开通中,1:开通成功,2:开通失败
	@ApiModelProperty(value = "开户 状态:0:开通中,1:开通成功,2:开通失败")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

}