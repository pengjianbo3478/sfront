package com.gl365.app.dto;

/**
 * Created by zhouhui on 2017/11/20.
 * 合伙人名下商户搜索条件
 */
public class SearchCondition {
    private String agentId ;
    private  int pageNo;
    private  int pageSize;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
