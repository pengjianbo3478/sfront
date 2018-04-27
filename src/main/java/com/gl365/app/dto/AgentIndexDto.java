package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 首页返回的
 */
public class AgentIndexDto implements Serializable {
	private static final long serialVersionUID = 9043602791828942991L;

	private String id;

	private String name;

	private Integer num;

	private boolean isMyself;

	private String avatarUrl;

	@ApiModelProperty("代理的id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty("代理的名字")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty("发展的商户数量")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@ApiModelProperty("是否属于我")
	public boolean isMyself() {
		return isMyself;
	}

	public void setMyself(boolean myself) {
		isMyself = myself;
	}

	@ApiModelProperty("头像")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
