package com.gl365.app.dto.command;

import java.io.Serializable;

import com.gl365.app.dto.Agent;

import io.swagger.annotations.ApiModelProperty;

public class IndexCommand implements Serializable {

	private static final long serialVersionUID = -4582752505045768407L;

	private Agent currentUser;

	private int type;

	@ApiModelProperty(hidden = true)
	public Agent getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Agent currentUser) {
		this.currentUser = currentUser;
	}

	@ApiModelProperty("查询类型，1：今天，2：上月，3:本月")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
