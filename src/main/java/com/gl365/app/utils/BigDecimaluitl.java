package com.gl365.app.utils;

import java.math.BigDecimal;

public class BigDecimaluitl {

	private static final int _2 = 2;

	public static BigDecimal setScale(BigDecimal decimal, int arg0) {
		if (decimal == null) {
			return new BigDecimal(0).setScale(arg0, BigDecimal.ROUND_DOWN);
		}

		return decimal.setScale(arg0, BigDecimal.ROUND_DOWN);
	}
	
	public static String setScaleStr(BigDecimal decimal, int arg0) {
		return setScale(decimal, arg0).toString();
	}
	
	public static String setScaleStr(BigDecimal decimal) {
		return setScaleStr(decimal, _2);
	}
}
