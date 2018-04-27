package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Chen, Zhuhui
 */
public class ApiRequest<T> {

    private Integer pageNo;

    private Integer pageSize;

    private T data;

    public T getData() {
        return data;
    }

    @ApiModelProperty("请求数据")
    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    @ApiModelProperty(value = "分页请求时提供的分页页码值，从1开始", example = "1")
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @ApiModelProperty(value = "分页请求时提供的分页页大小", example = "20")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
