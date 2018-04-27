package com.gl365.app.remote.gd.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by caoyilong on 2017/6/8.
 */
public class AreaDataDto implements Serializable {
	private static final long serialVersionUID = -6865114631972998621L;
	private Integer id;

	private Integer parentId;

	private String name;

	private Integer level;
	
	private String isHide;

	@ApiModelProperty("地区id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty("地区父id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@ApiModelProperty("地区名字")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty("地区级别")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@ApiModelProperty("是否隐藏,Y:已经被代理的区域不显示,N:显示所有")
	public String getIsHide() {
		return isHide;
	}

	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}
	
}
