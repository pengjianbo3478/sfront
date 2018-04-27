package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * POS品牌
 * 
 * @author dfs_518
 * @ClassName: BrandEnum
 * @Description:
 * 
 * @date 2017年7月3日 下午2:50:43
 *
 */
public enum PosBrandEnum implements ValuedEnum<String> {

	/**
	 * 惠尔丰
	 */
	VERIFONE("06", "惠尔丰"),
	
	/**
	 * 百富
	 */
	PAX("01", "百富");

	private final String value;

	private final String text;

	PosBrandEnum(String value, String text) {
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

	public static PosBrandEnum getEnumByCode(String value) {
		for (PosBrandEnum e : values()) {
			if (e.value().equals(value)) {
				return e;
			}
		}
		return null;
	}

}
