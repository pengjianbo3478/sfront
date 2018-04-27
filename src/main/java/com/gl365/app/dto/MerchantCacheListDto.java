package com.gl365.app.dto;

import java.util.List;

import com.gl365.app.dto.users.PageInfoDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/5.
 */
public class MerchantCacheListDto extends PageInfoDto {

	@ApiModelProperty("数据列表")
	private List<MerchantCacheDto> list;

	public List<MerchantCacheDto> getList() {
		return list;
	}

	public void setList(List<MerchantCacheDto> list) {
		this.list = list;
	}

}
