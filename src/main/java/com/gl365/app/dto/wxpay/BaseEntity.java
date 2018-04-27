package com.gl365.app.dto.wxpay;

import java.io.Serializable;

import com.gl365.app.utils.JsonUtils;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000986504315325574L;

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
	
}
