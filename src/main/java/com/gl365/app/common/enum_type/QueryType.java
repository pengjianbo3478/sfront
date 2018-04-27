package com.gl365.app.common.enum_type;

import com.gl365.app.dto.ValuedEnum;

public enum QueryType implements ValuedEnum<Integer> {
	/**
	 * 今天
	 */
	TODAY(1), /**
				 * 上月
				 */
	LAST_MONTH(2), /**
					 * 本月
					 */
	CURRENT_MONTH(3);

	private final int value;

	QueryType(int value) {
		this.value = value;
	}

	@Override
	public Integer value() {
		return value;
	}
}
