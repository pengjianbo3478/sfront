package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import com.gl365.app.utils.BigDecimaluitl;

/**
 * Created by wangmeihua on 2017/6/16.
 */
public class SubAgentTradeDetailDto {
    private String name;
    private String mobile;
    private Integer merchantNum;
    private Long txnCount;
    private BigDecimal txnAmount;
    private BigDecimal myProfit;
    private String avatarUrl;

    @ApiModelProperty(value = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "手机号码")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @ApiModelProperty(value = "商户数量")
    public Integer getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(Integer merchantNum) {
        this.merchantNum = merchantNum;
    }
    @ApiModelProperty(value = "交易笔数")
    public Long getTxnCount() {
        return txnCount;
    }

    public void setTxnCount(Long txnCount) {
        this.txnCount = txnCount;
    }
    @ApiModelProperty(value = "交易总金额")
    public String getTxnAmount() {
        return BigDecimaluitl.setScaleStr(txnAmount);
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }
    @ApiModelProperty(value = "我的分润")
    public String getMyProfit() {
        return BigDecimaluitl.setScaleStr(myProfit);
    }

    public void setMyProfit(BigDecimal myProfit) {
        this.myProfit = myProfit;
    }

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
