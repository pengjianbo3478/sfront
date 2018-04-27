package com.gl365.app.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AreaDto{
	
	@ApiModelProperty(value = "区域ID")
	@NotNull(message = "区域ID不能为空")
	private Integer areaId;
	
	@ApiModelProperty(value = "Y已经被代理的区域不显示,N显示所有")
	@NotNull(message = "是否隐藏不能为空")
	private String hide;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}
}
