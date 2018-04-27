package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 几个验证可以用这个返回
 */
public class ValidateDto implements Serializable {

	private static final long serialVersionUID = -9138955745845342344L;

	private boolean result;

	public boolean isResult() {
		return result;
	}

	@ApiModelProperty("验证通过与否")
	public void setResult(boolean result) {
		this.result = result;
	}
}
