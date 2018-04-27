package com.gl365.app.dto;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 有分页的参数返回
 * Created by caoyilong on 2017/6/1.
 */
public class ApiPageResponse<T> extends ApiResponse<T> {
	private Integer pageNo;

	private Integer pageSize;

	private Long totalItems;

	private Integer totalPages;

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

	/**
	 * 没有错误的分页返回
	 */
	public static <T> ApiPageResponse<List<T>> createPagedNoErrorResponse(PageResult<T> data, String message) {
		ApiPageResponse<List<T>> response = new ApiPageResponse<>();
		response.setCode(ReturnCode.System.SUCCESS.getCode());
		if (!StringUtils.isEmpty(message)) {
			response.setMessage(message);
		} else {
			response.setMessage(ReturnCode.System.SUCCESS.getMsg());
		}
		response.setData(data.getData());
		response.setPageNo(data.getPageNo());
		response.setPageSize(data.getPageSize());
		response.setTotalItems(data.getTotalItems());
		response.setTotalPages(data.getTotalPages());
		return response;
	}
}
