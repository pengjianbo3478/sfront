package com.gl365.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class UserProfitDto {
	
	@ApiModelProperty(value = "手机号")
    private String mobile;
	
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "交易笔数")
    private Long txnCount;
    
    @ApiModelProperty(value = "交易总金额")
    private BigDecimal txnAmount;
    
    @ApiModelProperty(value = "我的分润")
    private BigDecimal totalProfitFee;
    
    @ApiModelProperty(value = "用户id")
    private String userId;
    
    @ApiModelProperty(value = "头像")
    private String photo;
    
    @ApiModelProperty(value = "注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public Long getTxnCount() {
        return txnCount;
    }

    public void setTxnCount(Long txnCount) {
        this.txnCount = txnCount;
    }
 
    public String getTxnAmount() {
        return BigDecimaluitl.setScaleStr(txnAmount);
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public String getTotalProfitFee() {
        return BigDecimaluitl.setScaleStr(totalProfitFee);
    }

    public void setTotalProfitFee(BigDecimal totalProfitFee) {
        this.totalProfitFee = totalProfitFee;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
	
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDateTime getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}
}
