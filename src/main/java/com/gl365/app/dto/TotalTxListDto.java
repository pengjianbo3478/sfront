package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class TotalTxListDto implements Serializable {

	private static final long serialVersionUID = 6109143674518467777L;

	private Integer pageNo;

	private Integer pageSize;

	private Long totalItems;

	private Integer totalPages;

	private LocalDate currentMonth;

	private boolean isEnd;

	private List<BriefTxDto> list;

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

	@ApiModelProperty("当前月份")
	public LocalDate getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(LocalDate currentMonth) {
		this.currentMonth = currentMonth;
	}

	@ApiModelProperty("交易信息")
	public List<BriefTxDto> getList() {
		return list;
	}

	public void setList(List<BriefTxDto> list) {
		this.list = list;
	}

	@ApiModelProperty("当查询开始时间大于该用户的注册时间时，返回true,即完成所有的查询")
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean end) {
		isEnd = end;
	}
}
