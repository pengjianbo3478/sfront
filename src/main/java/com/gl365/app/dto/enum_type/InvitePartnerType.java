package com.gl365.app.dto.enum_type;


import com.gl365.app.dto.ValuedEnum;

/**
 * Created by caoyilong on 2017/6/6.
 * 邀请伙伴的类型
 */
public enum InvitePartnerType implements ValuedEnum<Integer> {
	/**
	 * 县级代理
	 */
	COUNTY(1),

	/**
	 * 业务员
	 */
	PERSONAL(2),
	/**
	 * 用户
	 */
	USER(3);

	private final int value;

	InvitePartnerType(int value) {
		this.value = value;
	}

	@Override
	public Integer value() {
		return value;
	}

}
