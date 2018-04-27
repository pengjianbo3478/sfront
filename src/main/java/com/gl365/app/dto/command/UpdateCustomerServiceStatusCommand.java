package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/5.
 */
public class UpdateCustomerServiceStatusCommand{

    @ApiModelProperty(hidden = true)
    private String agentId;

    @ApiModelProperty(value = "流程id", required = true)
    private String requestId;

    @ApiModelProperty(value = "下一流程的处理人类型。platform-状给平台处理，agent-转给代理商处理，personAgent-转给业务员处理，done-完成", allowableValues = "platform, agent, personAgent, done", dataType = "java.lang.String", required = true)
    private String type;

    @ApiModelProperty(value = "营业执照URL")
    private String licenseUrl;

    @ApiModelProperty(value = "（法人）身份证正面图片URL")
    private String idCardFrontUrl;

    @ApiModelProperty(value = "（法人）身份证背面图片URL")
    private String idCardBackUrl;

    @ApiModelProperty(value = "银行卡正面图片URL")
    private String depositCardFrontUrl;

    @ApiModelProperty(value = "银行卡背面图片URL")
    private String depositCardBackUrl;

    @ApiModelProperty(value = "变更/退机申请表URL")
    private String applicationFormUrl;

    @ApiModelProperty(value = "非法人身份证正面图片URL")
    private String nlpIdCardFrontUrl;

    @ApiModelProperty(value = "非法人身份证背面图片URL")
    private String nlpIdCardBackUrl;

    @ApiModelProperty(value = "法人授权书URL")
    private String engagementLetterUrl;

    @ApiModelProperty(value = "维修清单URL")
    private String fixingListUrl;

    @ApiModelProperty(value = "合同URL")
    private String contractUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getDepositCardFrontUrl() {
        return depositCardFrontUrl;
    }

    public void setDepositCardFrontUrl(String depositCardFrontUrl) {
        this.depositCardFrontUrl = depositCardFrontUrl;
    }

    public String getDepositCardBackUrl() {
        return depositCardBackUrl;
    }

    public void setDepositCardBackUrl(String depositCardBackUrl) {
        this.depositCardBackUrl = depositCardBackUrl;
    }

    public String getApplicationFormUrl() {
        return applicationFormUrl;
    }

    public void setApplicationFormUrl(String applicationFormUrl) {
        this.applicationFormUrl = applicationFormUrl;
    }

    public String getNlpIdCardFrontUrl() {
        return nlpIdCardFrontUrl;
    }

    public void setNlpIdCardFrontUrl(String nlpIdCardFrontUrl) {
        this.nlpIdCardFrontUrl = nlpIdCardFrontUrl;
    }

    public String getNlpIdCardBackUrl() {
        return nlpIdCardBackUrl;
    }

    public void setNlpIdCardBackUrl(String nlpIdCardBackUrl) {
        this.nlpIdCardBackUrl = nlpIdCardBackUrl;
    }

    public String getEngagementLetterUrl() {
        return engagementLetterUrl;
    }

    public void setEngagementLetterUrl(String engagementLetterUrl) {
        this.engagementLetterUrl = engagementLetterUrl;
    }

    public String getFixingListUrl() {
        return fixingListUrl;
    }

    public void setFixingListUrl(String fixingListUrl) {
        this.fixingListUrl = fixingListUrl;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
