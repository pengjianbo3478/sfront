package com.gl365.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Chen, Zhuhui
 */
public class BriefTxDto {

    private String tid;

    private String merchantNo;

    private String merchantName;

    private BigDecimal amount;

    private BigDecimal profit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settleTime;
    
    private String settleFlag;

    /**
     * 返回交易ID
     */
    @ApiModelProperty(value = "交易ID", required = true)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * 返回给乐商户号
     */
    @ApiModelProperty(value = "给乐商户号", required = true)
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantId(String merchantId) {
        this.merchantNo = merchantId;
    }

    /**
     * 返回商户名称
     */
    @ApiModelProperty(value = "商户名称", required = true)
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 返回交易金额
     */
    @ApiModelProperty(value = "交易金额", required = true)
    public String getAmount() {
        return BigDecimaluitl.setScaleStr(amount);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 返回分润金额
     */
    @ApiModelProperty(value = "分润金额", required = true)
    public String getProfit() {
        return BigDecimaluitl.setScaleStr(profit);
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 返回交易时间
     */
    @ApiModelProperty(value = "交易时间", required = true, example = "2017-06-16")
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    /**
     * 返回清算时间
     */
    @ApiModelProperty(value = "清算时间")
	public LocalDate getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(LocalDate settleTime) {
		this.settleTime = settleTime;
	}
	
	@ApiModelProperty(value = "清算标志:Y：已清算,N：未清算")
	public String getSettleFlag() {
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}
}
