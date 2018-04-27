package com.gl365.app.remote.withdraw.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/16.
 */
public class DetailCommand {

    @ApiModelProperty("查询的申请单号id")
    private String voucherId;

    @ApiModelProperty("每页记录数")
    private int numPerPage;

    @ApiModelProperty("页码")
    private int pageNum;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
