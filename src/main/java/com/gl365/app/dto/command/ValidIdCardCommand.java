package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ValidIdCardCommand implements Serializable {

	private static final long serialVersionUID = -1266939165059083074L;

	private String cardId;

	private String name;

	@ApiModelProperty("身份证")
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@ApiModelProperty("姓名")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
