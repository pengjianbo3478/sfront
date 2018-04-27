package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.PosType;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by qiuchaojie on 2017/6/14.
 */
public class PosDetailDto {

    @ApiModelProperty("商户号")
    private String merchantSn;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("联系人姓名")
    private String merchantContactName;

    @ApiModelProperty("联系人手机号")
    private String merchantContactMobile;

    @ApiModelProperty("行业类型id")
    private Integer merchantIndustry;

    @ApiModelProperty("行业类型名字")
    private String merchantIndustryName;

    @ApiModelProperty("POS机SN号")
    private String posSn;

    @ApiModelProperty("pos机类型，1：移动机，2：固定机，3：智能机，9：扫码付")
    private PosType posType;

    @ApiModelProperty("申请业务员名字")
    private String posAgentName;

    @ApiModelProperty("申请业务员手机号")
    private String posAgentMobile;

    @ApiModelProperty("申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime posCreationTime;


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

	public String getMerchantContactName() {
		return merchantContactName;
	}

	public void setMerchantContactName(String merchantContactName) {
		this.merchantContactName = merchantContactName;
	}

	public String getMerchantContactMobile() {
		return merchantContactMobile;
	}

	public void setMerchantContactMobile(String merchantContactMobile) {
		this.merchantContactMobile = merchantContactMobile;
	}

	public Integer getMerchantIndustry() {
		return merchantIndustry;
	}

	public void setMerchantIndustry(Integer merchantIndustry) {
		this.merchantIndustry = merchantIndustry;
	}

    public String getMerchantIndustryName() {
        return merchantIndustryName;
    }

    public void setMerchantIndustryName(String merchantIndustryName) {
        this.merchantIndustryName = merchantIndustryName;
    }

	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}

	public PosType getPosType() {
		return posType;
	}

	public void setPosType(PosType posType) {
		this.posType = posType;
	}

	public String getPosAgentName() {
		return posAgentName;
	}

	public void setPosAgentName(String posAgentName) {
		this.posAgentName = posAgentName;
	}

	public String getPosAgentMobile() {
		return posAgentMobile;
	}

	public void setPosAgentMobile(String posAgentMobile) {
		this.posAgentMobile = posAgentMobile;
	}

	public LocalDateTime getPosCreationTime() {
		return posCreationTime;
	}

	public void setPosCreationTime(LocalDateTime posCreationTime) {
		this.posCreationTime = posCreationTime;
	}
}
