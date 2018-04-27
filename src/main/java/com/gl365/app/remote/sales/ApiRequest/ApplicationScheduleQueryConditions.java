package com.gl365.app.remote.sales.ApiRequest;

import com.gl365.app.dto.enum_type.ApplicationStatus;

/**
 * Created by wangmeihua on 2017/6/9.
 */
public class ApplicationScheduleQueryConditions {
	private String[] applicantIds;

	private ApplicationStatus[] statuses;

	private String keyword;

	private String upstreamAgentId;

	private String linkMerchantId;

	private int linkType;

	public String[] getApplicantIds() {
		return applicantIds;
	}

	public void setApplicantIds(String[] applicantIds) {
		this.applicantIds = applicantIds;
	}

	public ApplicationStatus[] getStatuses() {
		return statuses;
	}

	public void setStatuses(ApplicationStatus[] statuses) {
		this.statuses = statuses;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUpstreamAgentId() {
		return upstreamAgentId;
	}

	public void setUpstreamAgentId(String upstreamAgentId) {
		this.upstreamAgentId = upstreamAgentId;
	}

	public String getLinkMerchantId() {
		return linkMerchantId;
	}

	public void setLinkMerchantId(String linkMerchantId) {
		this.linkMerchantId = linkMerchantId;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}
}
