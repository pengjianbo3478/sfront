package com.gl365.app.dto.wxpay;

import io.swagger.annotations.ApiModelProperty;

public class WalletUrlRsp extends BaseEntity {
	private static final long serialVersionUID = 3681499987153040734L;

	@ApiModelProperty(value = "钱包地址，有效一次", required = true, example = "http://test.rm-tech.com.cn/rmt/bif/webank/rdt.html?k=12631")
	private String authUrl;
	
	@ApiModelProperty(value = "钱包开启状态：0未开户，1开户中，2开户失败，3开户成功", example = "3")
	private String status;

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
