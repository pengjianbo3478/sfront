package com.gl365.app.remote.withdraw.command;

import com.gl365.app.dto.command.BaseCommand;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class CashCommand extends BaseCommand {

    @ApiModelProperty(value = "提现金额", required = true)
    private BigDecimal cashMoney;


    @ApiModelProperty(hidden = true)
    private BigDecimal cahMoney;

    @ApiModelProperty(value = "银行卡号", hidden = true)
	private String bankAccountNo;
	
	@ApiModelProperty(value = "银行卡户名", hidden = true)
	private String bankAccountName;
	
	@ApiModelProperty(value = "开户支行银行名称", hidden = true)
	private String bankName;
	
	@ApiModelProperty("帐户类型：01-对公，02-对私")
	private String bankAccountType;
	
	@ApiModelProperty(value = "开户支行银行号", hidden = true)
	private String bankNo;
	
	@ApiModelProperty(value = "身份证号", hidden = true)
	private String certNo;
	
	@ApiModelProperty(value = "开户行银行总行", hidden = true)
	private String bankType;
	
	@ApiModelProperty(value = "手机号")
	private String mobile;
    
    public BigDecimal getCahMoney() {
        return cahMoney;
    }

    public void setCahMoney(BigDecimal cahMoney) {
        this.cahMoney = cahMoney;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
        this.cahMoney = cashMoney;
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

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
