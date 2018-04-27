package com.gl365.app.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.ApplicationStatus;
import com.gl365.app.dto.enum_type.ApplicationType;
import com.gl365.app.dto.merchant.NoBenefitMerchantApplicationDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/16.
 */
public class ApplicationSimple {

    private String id;

    private ApplicationType type;

    private AgentSimple agent;

    private MerchantApplicationSimple merchantApplicationDetails;

    private ApplicationStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    private NoBenefitMerchantApplicationDto noBenefitMerchant;

    public ApplicationSimple(){

    }

    public ApplicationSimple(Application application) {
        this.id = application.getId();
        this.type = application.getType();
        this.agent = new AgentSimple(application.getAgent());
        this.merchantApplicationDetails = new MerchantApplicationSimple(application.getMerchantApplicationDetails());
        this.status = application.getStatus();
        this.creationTime = application.getCreationTime();
        this.updateTime = application.getUpdateTime();
    }

    @ApiModelProperty(value = "申请id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "申请类型：10：代理商申请；20：业务员申请；30：商户申请（机具申请）；40：POS装机申请")
    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    @ApiModelProperty(value = "代理商申请则为代理商信息，业务员申请则为业务员信息，商户申请无返回")
    public AgentSimple getAgent() {
        return agent;
    }

    public void setAgent(AgentSimple agent) {
        this.agent = agent;
    }

    @ApiModelProperty(value = "商户申请（机具申请）的商户信息，其他申请无返回")
    public MerchantApplicationSimple getMerchantApplicationDetails() {
        return merchantApplicationDetails;
    }

    public void setMerchantApplicationDetails(MerchantApplicationSimple merchantApplicationDetails) {
        this.merchantApplicationDetails = merchantApplicationDetails;
    }

    @ApiModelProperty(value = "申请的当前详细状态。1：待审核；"+
            "10：业务员申请, 仅仅针对发展业务员需要邀请人审核；15：业务员申请不通过" +
            "310：（机具申请）初审通过/风控审核通过/运营审核中；315：初审失败/风控审核不通过；"+
            "320：（机具申请）运营审核通过/支付公司审核中；325：（机具申请）运营审核不通过；"+
            "330：（机具申请）支付公司审核通过；335：（机具申请）支付公司审核不通过"+
            "997：审批撤回；"+
            "998：审批不通过；"+
            "999：审批已通过。"+
            "前端对应：待审批：10；审核中：1，310，320；通过：330，999；拒绝：15，315，325，335，998；撤回：997", dataType = "[Ljava.lang.Integer;",required = true)
    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @ApiModelProperty(value = "创建时间")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @ApiModelProperty(value = "更新时间")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @ApiModelProperty(value = "返利修改申请")
	public NoBenefitMerchantApplicationDto getNoBenefitMerchant() {
		return noBenefitMerchant;
	}

	public void setNoBenefitMerchant(NoBenefitMerchantApplicationDto noBenefitMerchant) {
		this.noBenefitMerchant = noBenefitMerchant;
	}
    
    
}
