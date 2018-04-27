package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.enum_type.ApplicationStatus;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wangmeihua on 2017/6/7.
 */
public class MerchantScheduleFlow {
    /**
     * 申请记录id
     */
    private String applicationFlowId;
    /**
     *创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    /**
     *上一状态
     */
    private ApplicationStatus previousStatus;
    /**
     *当前状态
     */
    private ApplicationStatus currentStatus;
    /**
     *处理意见
     */
    private String comments;
    /**
     *申请者名称
     */
    private String agentName;
    /**
     * 申请者电话
     */
    private String agentMobile;
    /**
     * 申请者类别
     */
    private AgentType agentType;
    /**
     *可移动设备数量
     */
    private Integer numMobileDevice;
    /**
     *固定设备数量
     */
    private Integer numImmobileDevice;
    /**
     *智能设备数量
     */
    private Integer numSmartDevice;
    /**
     * 是否申请二维码
     */
    private Boolean applyForQrcode;
    /**
     *商户号
     */
    private String merchantSn;
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

    @ApiModelProperty(value = "申请代理姓名")
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @ApiModelProperty(value = "申请代理电话")
    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    @ApiModelProperty(value = "申请代理类型，0：平台；2：市级代理；3：县级代理；4：业务员；7：企业协会")
    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }

    @ApiModelProperty(value = "是否有申请二维码")
    public Boolean getApplyForQrcode() {
        return applyForQrcode;
    }

    public void setApplyForQrcode(Boolean applyForQrcode) {
        this.applyForQrcode = applyForQrcode;
    }

    @ApiModelProperty(value = "可移动设备数量")
    public Integer getNumMobileDevice() {
        return numMobileDevice;
    }

    public void setNumMobileDevice(Integer numMobileDevice) {
        this.numMobileDevice = numMobileDevice;
    }
    @ApiModelProperty(value = "固定设备数量")
    public Integer getNumImmobileDevice() {
        return numImmobileDevice;
    }

    public void setNumImmobileDevice(Integer numImmobileDevice) {
        this.numImmobileDevice = numImmobileDevice;
    }
    @ApiModelProperty(value = "智能设备数量")
    public Integer getNumSmartDevice() {
        return numSmartDevice;
    }

    public void setNumSmartDevice(Integer numSmartDevice) {
        this.numSmartDevice = numSmartDevice;
    }
    @ApiModelProperty(value = "商户号")
    public String getMerchantSn() {
        return merchantSn;
    }

    public void setMerchantSn(String merchantSn) {
        this.merchantSn = merchantSn;
    }
}
