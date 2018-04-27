package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class InvitePartnerRespDto implements Serializable {

	private static final long serialVersionUID = -3690184402042582661L;

	private String url;

	@ApiModelProperty("需要生成二维码的url，agentId为邀请人的id，actingAreaId: 代理区域id，仅仅发展县代理才会有。")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
