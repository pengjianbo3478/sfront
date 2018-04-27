package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class validateCaptchaCommand implements Serializable {

	private static final long serialVersionUID = -6607440518429434814L;

	private String code;

	private String key;

	@ApiModelProperty("图形验证码")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ApiModelProperty("生成图形验证码的key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
