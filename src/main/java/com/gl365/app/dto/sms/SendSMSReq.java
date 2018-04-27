package com.gl365.app.dto.sms;

import com.gl365.app.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by caoyilong on 2017/6/9.
 * 发送验证码的请求
 */
public class SendSMSReq implements Serializable {

	private static final long serialVersionUID = 1485043418691111643L;

	private String mobileNo;//手机号

	private SendSMSType type;//验证码用途

	@ApiModelProperty("手机号")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@ApiModelProperty(value = "验证码用途,1:注册，2:修改密码,3:商户审核 ", hidden = true)
	public SendSMSType getType() {
		return type;
	}

	public void setType(SendSMSType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
