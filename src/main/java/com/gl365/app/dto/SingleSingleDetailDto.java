package com.gl365.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/13.
 */
public class SingleSingleDetailDto {
    private BigDecimal myProfit;
    private BigDecimal myServiceFee;
    
    private BigDecimal personProfit;
    
    private BigDecimal countyProfit;
    private BigDecimal countyOfServiceFee;
    
    private String merchantName;
    private String externalOperationName;
    private String merchantNo;
    private String terminal;
    private String Issuer;
    private String cardNo;
    private LocalDateTime payTime;
    private BigDecimal totalAmount;
    private String agentName;
    private String settleFlag;
    private String checkFlag;
    private String payStatus;
    
    private Integer linkType;

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

    @ApiModelProperty(value = "我的收益")
    public String getMyProfit() {
        return BigDecimaluitl.setScaleStr(myProfit,4);
    }

    public void setMyProfit(BigDecimal myProfit) {
        this.myProfit = myProfit;
    }
    @ApiModelProperty(value = "我的服务费")
    public String getMyServiceFee() {
        return BigDecimaluitl.setScaleStr(myServiceFee,4);
    }

    public void setMyServiceFee(BigDecimal myServiceFee) {
        this.myServiceFee = myServiceFee;
    }
    @ApiModelProperty(value = "业务员收益")
    public String getPersonProfit() {
        return BigDecimaluitl.setScaleStr(personProfit,4);
    }

    public void setPersonProfit(BigDecimal personProfit) {
        this.personProfit = personProfit;
    }

    @ApiModelProperty(value = "县代收益")
    public String getCountyProfit() {
        return BigDecimaluitl.setScaleStr(countyProfit,4);
    }

    public void setCountyProfit(BigDecimal countyProfit) {
        this.countyProfit = countyProfit;
    }
    @ApiModelProperty(value = "县代的服务费")
    public String getCountyOfServiceFee() {
        return BigDecimaluitl.setScaleStr(countyOfServiceFee,4);
    }

    public void setCountyOfServiceFee(BigDecimal countyOfServiceFee) {
        this.countyOfServiceFee = countyOfServiceFee;
    }
    @ApiModelProperty(value = "商户名称")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    @ApiModelProperty(value = "给乐商户号")
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    @ApiModelProperty(value = "终端号")
    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    @ApiModelProperty(value = "发卡行")
    public String getIssuer() {
        return Issuer;
    }

    public void setIssuer(String issuer) {
        Issuer = issuer;
    }
    @ApiModelProperty(value = "卡号")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    @ApiModelProperty(value = "付款时间")
    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
    
    @ApiModelProperty(value = "交易金额")
    public String getTotalAmount() {
        return BigDecimaluitl.setScaleStr(totalAmount);
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @ApiModelProperty(value = "区域代理商")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@ApiModelProperty(value = "商家简称")
	public String getExternalOperationName() {
		return externalOperationName;
	}

	public void setExternalOperationName(String externalOperationName) {
		this.externalOperationName = externalOperationName;
	}
	@ApiModelProperty(value = "清算标志:Y：已清算,N：未清算")
	public String getSettleFlag() {
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}
	@ApiModelProperty(value = "对账标志:Y：已对账,N：未对账")
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	@ApiModelProperty(value = "交易状态00：待支付 01：已支付 02：已撤销 03：已部分退货 04：已全额退货 05：已冲正 06：部分付款（现金已付乐豆未付） 07：部分付款撤销   08：支付失败")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
}
