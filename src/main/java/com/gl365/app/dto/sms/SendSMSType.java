package com.gl365.app.dto.sms;


import com.gl365.app.dto.ValuedEnum;

/**
 * Created by caoyilong on 2017/6/9.
 */
public enum SendSMSType implements ValuedEnum<Integer> {

	/**
	 * 注册
	 */
	REGISTER(1),

	/**
	 * 修改密码
	 */
	UPDATE_PASSWORD(2),

	/**
	 * 商户审核
	 */
	MERCHANT(3),

	/**
	 * 修改手机号码
	 */
	UPDATE_MOBILE(4);

	private final int value;

	SendSMSType(int value) {
		this.value = value;
	}

	@Override
	public Integer value() {
		return value;
	}
}
