package com.gl365.app.remote.profit.command;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class QueryProfitConfigCommand {

    private String planName;//方案名字：%或为空 表示所有

    private int numPerPage;//每页记录数

    private int pageNum;//页码

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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
