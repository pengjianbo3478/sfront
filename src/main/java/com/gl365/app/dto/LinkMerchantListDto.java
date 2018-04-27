package com.gl365.app.dto;

import java.util.List;

import com.gl365.app.dto.users.PageInfoDto;

import io.swagger.annotations.ApiModelProperty;

public class LinkMerchantListDto  extends PageInfoDto{
	@ApiModelProperty("数据列表")
	private List<LinkMerchantDto> list;

	public List<LinkMerchantDto> getList() {
		return list;
	}

	public void setList(List<LinkMerchantDto> list) {
		this.list = list;
	}
}