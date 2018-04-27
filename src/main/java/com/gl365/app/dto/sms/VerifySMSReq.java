package com.gl365.app.dto.sms;


import com.gl365.app.utils.JsonUtils;

import java.io.Serializable;

/**
 * 验证验证码请求
 * Created by caoyilong on 2017/6/10.
 */
public class VerifySMSReq implements Serializable {
	private static final long serialVersionUID = 3717210479804533744L;

	private String mobileNo;//手机号

	private SendSMSType type;//验证码用途

	private String verifyCode;//验证码

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public SendSMSType getType() {
		return type;
	}

	public void setType(SendSMSType type) {
		this.type = type;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
