package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class MyRankingDto {
	private Integer ranking;
	private String id;

	private String name;

	private Integer num;


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

	@ApiModelProperty("头像")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@ApiModelProperty("排名")
	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
}
