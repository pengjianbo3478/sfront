package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class AgentDetailAndMerchantTradeListDto extends PageInfoDto {
    private String companyName;
    private String detailedAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CooperationTime;
    private String contactMobile;
    private String contactName;
    private String externalOperationName;
    private Integer linkType;
    private List<MyMerchantTradeList> myTradeList;
    private Integer noBenefit;

	@ApiModelProperty(value = "0没开通免返逗；1开通免返逗")
	public Integer getNoBenefit() {
		return noBenefit;
	}

	public void setNoBenefit(Integer noBenefit) {
		this.noBenefit = noBenefit;
	}
    public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	@ApiModelProperty(value = "公司名称")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @ApiModelProperty(value = "详细地址")
    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
    @ApiModelProperty(value = "合作时间")
    public LocalDateTime getCooperationTime() {
        return CooperationTime;
    }

    public void setCooperationTime(LocalDateTime cooperationTime) {
        CooperationTime = cooperationTime;
    }

    @ApiModelProperty(value = "联系人手机号")
    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
    @ApiModelProperty(value = "联系人名称")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @ApiModelProperty(value = "与商户合作相关的列表")
    public List<MyMerchantTradeList> getMyTradeList() {
        return myTradeList;
    }

    public void setMyTradeList(List<MyMerchantTradeList> myTradeList) {
        this.myTradeList = myTradeList;
    }

    @ApiModelProperty(value = "商家简称")
	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}
    
    
}
