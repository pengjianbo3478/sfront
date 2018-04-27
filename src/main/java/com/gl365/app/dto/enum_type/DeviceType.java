package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * pos设备类型
 * Created by caoyilong on 2017/5/19.
 */
public enum DeviceType implements ValuedEnum<Integer> {
	/**
	 * 购买
	 */
	BUY(1),
	/**
	 * 租赁
	 */
	RENT(2);

	private final int value;

	DeviceType(int value) {
		this.value = value;
	}

	@Override
	public Integer value() {
		return value;
	}
}
