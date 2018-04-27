package com.gl365.app.remote.sales.ApiRequest;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class FindStatusNumByConditions {
    private String[] applicantIds;
    private String upstreamAgentId;

    public String[] getApplicantIds() {
        return applicantIds;
    }

    public void setApplicantIds(String[] applicantIds) {
        this.applicantIds = applicantIds;
    }

	public String getUpstreamAgentId() {
		return upstreamAgentId;
	}

	public void setUpstreamAgentId(String upstreamAgentId) {
		this.upstreamAgentId = upstreamAgentId;
	}
}
