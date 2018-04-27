package com.gl365.app.remote.settlement.ApiResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/13.
 */
public class FindPayResponse {
    private String merchantNo;
    private String merchantName;
    private String terminal;
    private String transType;
    private String userId;
    private String userName;
    private String merchantOrderNo;
    private String cardNo;
    private BigDecimal totalAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;
    private String payStatus;
    private String settleFlag;
    private String checkFlag;
    

    @ApiModelProperty(value = "給乐商户号")
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    @ApiModelProperty(value = "商户名称")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    @ApiModelProperty(value = "终端号")
    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    @ApiModelProperty(value = "交易类型")
    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
    @ApiModelProperty(value = "会员ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @ApiModelProperty(value = "会员姓名")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @ApiModelProperty(value = "商户订单号")
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }
    @ApiModelProperty(value = "卡号")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    @ApiModelProperty(value = "交易总金额")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    @ApiModelProperty(value = "支付确认时间")
    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
    @ApiModelProperty(value = "交易状态00：待支付 01：已支付 02：已撤销 03：已部分退货 04：已全额退货 05：已冲正 06：部分付款（现金已付乐豆未付） 07：部分付款撤销   08：支付失败")
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
    
    
}
