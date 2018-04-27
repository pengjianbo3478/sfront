package com.gl365.app.remote.settlement.enum_type;

import org.apache.commons.lang.StringUtils;

public enum AccountProfitType {
	/** 0 总公司 */
	GL365("0", "总公司 00"),

	/** 1 省级运营商 */
	PROVINCE("1", "省级运营商"),

	/** 2 市级运营商 */
	CITY("2", "市级运营商"),

	/** 3 县级运营商 */
	DISTRICT("3", "县级运营商"),

	/** 4 业务员机构 */
	SALESMAN_AGENT("4", "业务员机构"),

	/** 5 联盟商家商户 */
	MERCHANT("5", "联盟商家商户"),

	/** 6 员工/店长/会员 */
	DEV_STAFF("6", "员工/店长/会员"),

	/** 7 企业协会 */
	COMPANY("7", "企业协会机构"),

	/** 8 积分机构 */
	SCORE_AGENT("8", "积分机构"),

	/** 9 电子商城:50001 */
	E_MALL("9", "电子商城"),

	/** 10 积分商城:40001 */
	SCORE_MALL("10", "积分商城"),

	/** 11 政策机构:60001 */
	GOVEMENT_AGENT("11", "政府机构"),

	/** 12 银行机构：70001 */
	BANK_AGENT("12", "银行机构"),

	/** 13 支付公司 */
	PAYMENT_PROVIDER("13", "支付公司"),

	/** 14 服务公司 */
	SERVER_PROVIDER("14", "服务公司");

	private String key;

	private String value;

	private AccountProfitType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static AccountProfitType get(String key) {
		if (StringUtils.isBlank(key))
			return null;
		for (AccountProfitType item : AccountProfitType.values()) {
			if (key.equals(item.getKey())) {
				return item;
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
