package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * Pos型号
 * 
 * @author dfs_518
 * @ClassName: PosVersionEnum
 * @Description:
 * 
 * @date 2017年7月3日 下午2:56:12
 *
 */
public enum PosVersionEnum implements ValuedEnum<String> {

	/**
	 * 型号
	 */
	VX675("0601"), VX680("0602"), A920("0105");

	private final String value;

	PosVersionEnum(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return this.value;
	}

	public static PosVersionEnum getEnumByCode(String value) {
		for (PosVersionEnum e : values()) {
			if (e.value().equals(value)) {
				return e;
			}
		}
		return null;
	}

}
