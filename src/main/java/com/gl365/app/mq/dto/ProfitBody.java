package com.gl365.app.mq.dto;

import java.io.Serializable;
import java.util.List;

import com.gl365.app.utils.GsonUtils;

/**
 * 2.43 S端提成消息推送MQ(1.0.3) 直接发展商户提成部分  内容包体
 * @author lch 2017年9月4日
 *
 */
public class ProfitBody implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 提成明细列表
	 */
	private List<PayProfit> payProfits;	
	
	public List<PayProfit> getPayProfits() {
		return payProfits;
	}

	public void setPayProfits(List<PayProfit> payProfits) {
		this.payProfits = payProfits;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
	
}
