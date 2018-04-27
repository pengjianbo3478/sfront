package com.gl365.app.remote.member.api_req;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class QueryUserInfoCommand {
    private String recommendAgentT;
    private String recommendAgentId;
    private String mobilePhone;
    private Integer curPage;
    private Integer pageSize;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRecommendAgentT() {
        return recommendAgentT;
    }

    public void setRecommendAgentT(String recommendAgentT) {
        this.recommendAgentT = recommendAgentT;
    }

    public String getRecommendAgentId() {
        return recommendAgentId;
    }

    public void setRecommendAgentId(String recommendAgentId) {
        this.recommendAgentId = recommendAgentId;
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
}
