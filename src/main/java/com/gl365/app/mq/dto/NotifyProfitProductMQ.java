package com.gl365.app.mq.dto;

import com.gl365.app.utils.GsonUtils;

/**
 * 2.43 S端提成消息推送MQ(1.0.3) 直接发展商户提成部分
 * @author lch 2017年9月4日
 *
 */
public class NotifyProfitProductMQ {
	
	/**
	 * 交易类型
	 */
	private String transType;
	
	/**
	 * 提成内容包体
	 */
	private ProfitBody profitBody;

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public ProfitBody getProfitBody() {
		return profitBody;
	}

	public void setProfitBody(ProfitBody profitBody) {
		this.profitBody = profitBody;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
