package com.gl365.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class MonthSettlement {
    private String voucherId;
    private String settleMonth;
    private BigDecimal feeMoney;
    private BigDecimal taxMoney;
    private BigDecimal realMoney;
    private BigDecimal cashMoney;
    private String status;
    
    @ApiModelProperty(value = "银行卡号")
	private String bankAccountNo;	
	
	@ApiModelProperty(value = "银行卡户名")
	private String bankAccountName;
	
	@ApiModelProperty(value = "开户支行银行名称")
	private String bankName;	
	
	@ApiModelProperty(value = "开户支行银行号")
	private String bankNo;
	
	@ApiModelProperty(value = "身份证号")
	private String certNo;
	
	@ApiModelProperty(value = "开户行银行总行")
	private String bankType;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toTime;
    private String supplyTax;
    @ApiModelProperty(value = "月份")
    public String getSettleMonth() {
        return settleMonth;
    }

    public void setSettleMonth(String settleMonth) {
        this.settleMonth = settleMonth;
    }

    @ApiModelProperty(value = "申请单号")
    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
    @ApiModelProperty(value = "代付费")
    public String getFeeMoney() {
        return BigDecimaluitl.setScaleStr(feeMoney);
    }

    public void setFeeMoney(BigDecimal feeMoney) {
        this.feeMoney = feeMoney;
    }
    @ApiModelProperty(value = "扣税费")
    public String getTaxMoney() {
        return BigDecimaluitl.setScaleStr(taxMoney);
    }

    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
    }
    @ApiModelProperty(value = "实际结算金额")
    public String getRealMoney() {
        return BigDecimaluitl.setScaleStr(realMoney);
    }

    public void setRealMoney(BigDecimal realMoney) {
        this.realMoney = realMoney;
    }
    @ApiModelProperty(value = "状态")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @ApiModelProperty(value = "提现金额")
    public String getCashMoney() {
        return BigDecimaluitl.setScaleStr(cashMoney);
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }


    @ApiModelProperty(value = "开始时间")
    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    @ApiModelProperty(value = "结束时间")
    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }
    @ApiModelProperty(value = "是否返回发票")
    public String getSupplyTax() {
        return supplyTax;
    }

    public void setSupplyTax(String supplyTax) {
        this.supplyTax = supplyTax;
    }

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
}
