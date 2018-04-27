package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.ApplicationStatus;
import com.gl365.app.dto.enum_type.PosDeviceStatus;
import com.gl365.app.dto.enum_type.PosType;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by qiuchaojie on 2017/6/12.
 */
public class MyPosDto {

    @ApiModelProperty(value = "POS机id", dataType = "java.lang.String")
    private String posId;

    @ApiModelProperty(value = "商户id", dataType = "java.lang.String")
    private String merchantId;

    @ApiModelProperty(value = "商户号", dataType = "java.lang.String")
    private String merchantSn;

    @ApiModelProperty(value = "商户名称", dataType = "java.lang.String")
    private String merchantName;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "POS机类型。1-移动机，2-固定机，3-智能机，4-扫付码", dataType = "java.lang.Integer")
    private PosType posType;

    @ApiModelProperty(value = "申请状态(字段为空时，代表该Pos机进件审核通过但是还未进行申请装机)。1-新申请，998-审核未通过，999-审核通过", dataType = "java.lang.Integer")
    private ApplicationStatus applicationStatus;

    @ApiModelProperty(value = "Pos机状态。1-支付公司审批通过，2-已绑定，3-未绑定(已解绑)", dataType = "java.lang.Integer")
    private PosDeviceStatus posDeviceStatus;

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantSn() {
        return merchantSn;
    }

    public void setMerchantSn(String merchantSn) {
        this.merchantSn = merchantSn;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public PosType getPosType() {
        return posType;
    }

    public void setPosType(PosType posType) {
        this.posType = posType;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public PosDeviceStatus getPosDeviceStatus() {
        return posDeviceStatus;
    }

    public void setPosDeviceStatus(PosDeviceStatus posDeviceStatus) {
        this.posDeviceStatus = posDeviceStatus;
    }

}
