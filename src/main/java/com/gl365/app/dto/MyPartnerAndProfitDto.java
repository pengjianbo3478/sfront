package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangmeihua on 2017/6/8.
 */
public class MyPartnerAndProfitDto extends PageInfoDto{
    private BigDecimal myCooperationProfit;
    private Long personalNum;
    private Long merchantNum;
    private Integer associationNum;
    private Long countyNum;
    private Integer userNum;
    private List<MyMerchantProfitList>  myProfits ;

    @ApiModelProperty(value = "我的合作总分润")
    public String getMyCooperationProfit() {
        return BigDecimaluitl.setScaleStr(myCooperationProfit);
    }

    public void setMyCooperationProfit(BigDecimal myCooperationProfit) {
        this.myCooperationProfit = myCooperationProfit;
    }
    @ApiModelProperty(value = "合作业务员数量")
    public Long getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(Long personalNum) {
        this.personalNum = personalNum;
    }



    @ApiModelProperty(value = "合作商户数量")
    public Long getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(Long merchantNum) {
        this.merchantNum = merchantNum;
    }



    @ApiModelProperty(value = "合作机构数量")
    public Integer getAssociationNum() {
        return associationNum;
    }

    public void setAssociationNum(Integer associationNum) {
        this.associationNum = associationNum;
    }
    @ApiModelProperty(value = "合作县代数量")
    public Long getCountyNum() {
        return countyNum;
    }

    public void setCountyNum(Long countyNum) {
        this.countyNum = countyNum;
    }

    @ApiModelProperty(value = "合作用户数量")
    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    @ApiModelProperty(value = "与商户的分润列表")
    public List<MyMerchantProfitList> getMyProfits() {
        return myProfits;
    }

    public void setMyProfits(List<MyMerchantProfitList> myProfits) {
        this.myProfits = myProfits;
    }
}
