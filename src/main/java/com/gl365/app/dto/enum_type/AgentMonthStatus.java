package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 月结状态
 * 
 * @author xty
 * @ClassName: AgentMonthStatus
 * @Description:
 * 
 * @date 2017年7月3日 下午2:50:43
 *
 */

public enum AgentMonthStatus implements ValuedEnum<String> {

	UNHANDLE("0", "待受理"),
	HANDLE("1", "已受理"),
	HANDLING("2", "处理中"),
	HANDLE_OK("3", "已结算"),
	HANDLE_FAILED("4", "结算失败"),
	HANDLE_REFUSE("5", "已拒绝"),
	;

	private final String value;

	private final String text;

	AgentMonthStatus(String value, String text) {
		this.value = value;
		this.text = text;
	}

	@Override
	public String value() {
		return this.value;
	}

	public String getText() {
		return this.text;
	}

	public static AgentMonthStatus getEnumByCode(String value) {
		for (AgentMonthStatus e : values()) {
			if (e.value().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getEnumTextByCode(String value) {
		for (AgentMonthStatus e : values()) {
			if (e.value().equals(value)) {
				return e.getText();
			}
		}
		return null;
	}

}
