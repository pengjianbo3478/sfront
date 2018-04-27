package com.gl365.app.dto.users;

import io.swagger.annotations.ApiModelProperty;


public class AgentCheckBankBin{
	
	@ApiModelProperty(value = "总银名称", required = true)
    private String bankMainName;
	
	@ApiModelProperty(value = "银行卡号", required = true)
    private String bankAccountNo;

	public String getBankMainName() {
		return bankMainName;
	}

	public void setBankMainName(String bankMainName) {
		this.bankMainName = bankMainName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
}
