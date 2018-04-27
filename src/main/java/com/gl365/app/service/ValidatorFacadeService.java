package com.gl365.app.service;

/**
 * Created by caoyilong on 2017/6/8.
 */
public interface ValidatorFacadeService {
	/**
	 * 实名认证
	 *
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	boolean validIdCard(String cardId, String name);

	/**
	 * 验证手机
	 *
	 * @param mobileNum 手机号
	 * @param idNum     身份证
	 * @param name      姓名
	 */
	boolean validMobile(String mobileNum, String idNum, String name);

	/**
	 * 验证银行卡
	 *
	 * @param bankId 银行卡号
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	boolean validBankCard(String bankId, String cardId, String name);
}
