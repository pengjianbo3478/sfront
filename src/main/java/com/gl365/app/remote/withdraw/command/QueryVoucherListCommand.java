package com.gl365.app.remote.withdraw.command;

import com.gl365.app.dto.command.BaseCommand;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class QueryVoucherListCommand extends BaseCommand {

    private String queryType;//必填。0-查询新增提现单。1-按下述条件查询

    private String voucherId;//申请单号

    private int numPerPage;//每页记录数

    private int pageNum;//页码

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

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
