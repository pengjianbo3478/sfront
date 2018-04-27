package com.gl365.app.remote.withdraw.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class VoucherDto {

    @ApiModelProperty("申请单号")
    private String voucherId;

    @ApiModelProperty("提现金额")
    private BigDecimal cashMoney;

    @ApiModelProperty("状态：0-待授理，1-已授理，3-代付导出，4-代付完成，5-提现失败，6-申请撤回")
    private String state;

    @ApiModelProperty("申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "代付费", hidden = true)
    private BigDecimal feeMoney;

    @ApiModelProperty(value = "扣税费", hidden = true)
    private BigDecimal taxMoney;
    
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty(value = "实得金额", example = "80")
	private BigDecimal realMoney;
    
    @ApiModelProperty(value = "手机号")
	private String mobile;


    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getCashMoney() {
        return BigDecimaluitl.setScaleStr(cashMoney);
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getFeeMoney() {
        return BigDecimaluitl.setScaleStr(feeMoney);
    }

    public void setFeeMoney(BigDecimal feeMoney) {
        this.feeMoney = feeMoney;
    }

    public String getTaxMoney() {
        return BigDecimaluitl.setScaleStr(taxMoney);
    }

    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRealMoney() {
		return BigDecimaluitl.setScaleStr(realMoney);
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
