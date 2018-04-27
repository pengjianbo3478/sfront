package com.gl365.app.remote.member.api_resp;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class QueryUserInfoDto {
    private Integer totalCount;
    private Integer totalPage;
    private Integer curPage;
    private Integer pageSize;
    private List<UserDetailDto> list;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<UserDetailDto> getList() {
        return list;
    }

    public void setList(List<UserDetailDto> list) {
        this.list = list;
    }
}
