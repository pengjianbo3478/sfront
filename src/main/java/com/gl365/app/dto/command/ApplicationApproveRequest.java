package com.gl365.app.dto.command;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/6.
 */
public class ApplicationApproveRequest {

	private String agentId;

	private String applicationId;

	private String comment;

	private String partnerContractPicUrl;
	private List<String> partnerContractPicUrls;
	
	private String partnerContractPicUrl1;
	private String partnerContractPicUrl2;
	private String partnerContractPicUrl3;

	@ApiModelProperty(value = "合伙人合同照片地址")
	public String getPartnerContractPicUrl() {
		if(CollectionUtils.isNotEmpty(this.partnerContractPicUrls)&&
				this.partnerContractPicUrls.size() > 0){
			this.partnerContractPicUrl = this.partnerContractPicUrls.get(0);
		}
		return partnerContractPicUrl;
	}

	public void setPartnerContractPicUrl(String partnerContractPicUrl) {
		this.partnerContractPicUrl = partnerContractPicUrl;
	}

	@ApiModelProperty(value = "代理商id", required = false, hidden = true)
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	@ApiModelProperty(value = "申请id", required = true)
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@ApiModelProperty(value = "审批意见", required = true)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPartnerContractPicUrl1() {
		if(CollectionUtils.isNotEmpty(this.partnerContractPicUrls)&&
				this.partnerContractPicUrls.size() > 1){
			this.partnerContractPicUrl1 = this.partnerContractPicUrls.get(1);
		}
		return partnerContractPicUrl1;
	}

	public void setPartnerContractPicUrl1(String partnerContractPicUrl1) {
		this.partnerContractPicUrl1 = partnerContractPicUrl1;
	}

	public String getPartnerContractPicUrl2() {
		if(CollectionUtils.isNotEmpty(this.partnerContractPicUrls)&&
				this.partnerContractPicUrls.size() > 2){
			this.partnerContractPicUrl2 = this.partnerContractPicUrls.get(2);
		}
		return partnerContractPicUrl2;
	}

	public void setPartnerContractPicUrl2(String partnerContractPicUrl2) {
		this.partnerContractPicUrl2 = partnerContractPicUrl2;
	}

	public String getPartnerContractPicUrl3() {
		if(CollectionUtils.isNotEmpty(this.partnerContractPicUrls)&&
				this.partnerContractPicUrls.size() > 3){
			this.partnerContractPicUrl3 = this.partnerContractPicUrls.get(3);
		}
		return partnerContractPicUrl3;
	}

	public void setPartnerContractPicUrl3(String partnerContractPicUrl3) {
		this.partnerContractPicUrl3 = partnerContractPicUrl3;
	}

	public List<String> getPartnerContractPicUrls() {
		return partnerContractPicUrls;
	}

	public void setPartnerContractPicUrls(List<String> partnerContractPicUrls) {
		this.partnerContractPicUrls = partnerContractPicUrls;
	}
	
}
