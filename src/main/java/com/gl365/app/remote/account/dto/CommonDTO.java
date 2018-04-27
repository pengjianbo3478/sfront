package com.gl365.app.remote.account.dto;

public class CommonDTO{
	
	 /** 接口结果返回编码 */
	 private String resultCode;

	 /** 接口结果返回描述 */
	 private String resultDesc;
	 
	 /** 卡号  **/
	 private String cardNo;
	 
	 /** 银行代码  **/
	 private String bankCode;
	 
	 /** 银行名称  **/
	 private String bankName;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
