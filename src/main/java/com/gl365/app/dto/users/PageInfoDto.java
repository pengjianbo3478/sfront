package com.gl365.app.dto.users;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/6.
 */
public class PageInfoDto {

    @ApiModelProperty(value = "当前页", dataType = "java.lang.Integer")
    private int pageNum;

    @ApiModelProperty(value = "每页的数量", dataType = "java.lang.Integer")
    private int pageSize;

    @ApiModelProperty(value = "当前页的数量", dataType = "java.lang.Integer")
    private int size;

    @ApiModelProperty(value = "总页数", dataType = "java.lang.Integer")
    private int pages;

    @ApiModelProperty(value = "总记录数", dataType = "java.lang.Long")
    private long total;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
