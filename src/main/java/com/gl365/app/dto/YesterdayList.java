package com.gl365.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.gl365.app.remote.profit.dto.YesterdayListDto;

import io.swagger.annotations.ApiModelProperty;

public class YesterdayList implements Serializable {

	private static final long serialVersionUID = 6109143674518467777L;

	private Integer pageNo;

	private Integer pageSize;

	private Long totalItems;

	private Integer totalPages;

	private List<YesterdayListDto> list;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	@ApiModelProperty("交易信息")
	public List<YesterdayListDto> getList() {
		return list;
	}

	public void setList(List<YesterdayListDto> list) {
		this.list = list;
	}

}
