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
public class AgentDetailAndMerchantProfitListDto extends PageInfoDto{
    private String companyName;
    private String detailedAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CooperationTime;
    private String mobile;
    private String name;
    private Integer subPersonalNum;
    private String agentId ;
    private List<MyMerchantProfitList> myProfitList;
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
    @ApiModelProperty(value = "手机号")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @ApiModelProperty(value = "代理商名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "下级业务员数量")
    public Integer getSubPersonalNum() {
        return subPersonalNum;
    }

    public void setSubPersonalNum(Integer subPersonalNum) {
        this.subPersonalNum = subPersonalNum;
    }
    @ApiModelProperty(value = "代理商id")
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @ApiModelProperty(value = "与分润相关的列表")
    public List<MyMerchantProfitList> getMyProfitList() {
        return myProfitList;
    }

    public void setMyProfitList(List<MyMerchantProfitList> myProfitList) {
        this.myProfitList = myProfitList;
    }
}
