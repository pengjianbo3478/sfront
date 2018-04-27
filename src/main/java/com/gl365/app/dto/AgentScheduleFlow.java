package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.enum_type.ApplicationStatus;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wangmeihua on 2017/6/7.
 */
public class AgentScheduleFlow {
    /**
     *申请记录id
     */
    private String applicationFlowId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    /**
     * 上一状态
     */
    private ApplicationStatus previousStatus;
    /**
     * 当前状态
     */
    private ApplicationStatus currentStatus;
    /**
     * 处理意见
     */
    private String comments;

    /**
     *申请者名称
     */
    private String agentName;
    
    /**
     *邀请者名称(申请人上级)
     */
    private String upstreamAgentName;
    
    /**
     * 申请者电话
     */
    private String agentMobile;
    /**
     * 申请者类别
     */
    private AgentType agentType;
    
    /**
     * 合伙人合同照片地址
     */
    private String partnerContractPicUrl;
    private List<String> partnerContractPicUrls;
    private String partnerContractPicUrl1;
    private String partnerContractPicUrl2;
    private String partnerContractPicUrl3;
    
    @ApiModelProperty(value = "申请记录id")
    public String getApplicationFlowId() {
        return applicationFlowId;
    }

    public void setApplicationFlowId(String applicationFlowId) {
        this.applicationFlowId = applicationFlowId;
    }
    @ApiModelProperty(value = "记录创建时间")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    @ApiModelProperty(value = "上一状态，状态值参考接口findAgentApplications")
    public ApplicationStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(ApplicationStatus previousStatus) {
        this.previousStatus = previousStatus;
    }
    @ApiModelProperty(value = "当前状态，状态值参考接口findAgentApplications")
    public ApplicationStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(ApplicationStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
    @ApiModelProperty(value = "拒绝原因")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ApiModelProperty(value = "申请者姓名")
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @ApiModelProperty(value = "申请者电话")
    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    @ApiModelProperty(value = "申请者类型，0：平台；2：市级代理；3：县级代理；4：业务员；7：企业协会")
    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }
    @ApiModelProperty(value = "邀请者名称(申请人上级)")
	public String getUpstreamAgentName() {
		return upstreamAgentName;
	}

	public void setUpstreamAgentName(String upstreamAgentName) {
		this.upstreamAgentName = upstreamAgentName;
	}

	 @ApiModelProperty(value = "合伙人合同照片地址")
	public String getPartnerContractPicUrl() {
		return partnerContractPicUrl;
	}

	public void setPartnerContractPicUrl(String partnerContractPicUrl) {
		this.partnerContractPicUrl = partnerContractPicUrl;
	}

	public List<String> getPartnerContractPicUrls() {
		return partnerContractPicUrls;
	}

	public void setPartnerContractPicUrls(List<String> partnerContractPicUrls) {
		this.partnerContractPicUrls = partnerContractPicUrls;
	}

	public String getPartnerContractPicUrl1() {
		return partnerContractPicUrl1;
	}

	public void setPartnerContractPicUrl1(String partnerContractPicUrl1) {
		this.partnerContractPicUrl1 = partnerContractPicUrl1;
	}

	public String getPartnerContractPicUrl2() {
		return partnerContractPicUrl2;
	}

	public void setPartnerContractPicUrl2(String partnerContractPicUrl2) {
		this.partnerContractPicUrl2 = partnerContractPicUrl2;
	}

	public String getPartnerContractPicUrl3() {
		return partnerContractPicUrl3;
	}

	public void setPartnerContractPicUrl3(String partnerContractPicUrl3) {
		this.partnerContractPicUrl3 = partnerContractPicUrl3;
	}
	
}
