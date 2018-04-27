package com.gl365.app.service.impl;

import com.gl365.app.remote.validator.GeiLeValidatorService;
import com.gl365.app.remote.validator.ValidDto;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by caoyilong on 2017/6/8.
 */
@Component
public class ValidatorFacadeServiceImpl implements ValidatorFacadeService {
	protected final Logger LOG = LoggerFactory.getLogger(ValidatorFacadeServiceImpl.class);

	private final static String SUCCESS_CODE = "000000";

	@Autowired
	private GeiLeValidatorService geiLeValidatorService;

	/**
	 * 实名认证
	 *
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	@Override
	public boolean validIdCard(String cardId, String name) {
		ValidDto<Boolean> response = geiLeValidatorService.validIdCard(cardId, name);
		LOG.info("实名认证 返回参数为 \n param={}", JsonUtils.toJsonString(response));
		if (response.getResult().equals(SUCCESS_CODE)) {
			return response.getData();
		} else {
			LOG.error("调用实名认证接口错误,{}", response.getDescription());
			return false;
		}
	}

	/**
	 * 验证手机
	 *
	 * @param mobileNum 手机号
	 * @param idNum     身份证
	 * @param name      姓名
	 */
	@Override
	public boolean validMobile(String mobileNum, String idNum, String name) {
		ValidDto<Boolean> response = geiLeValidatorService.validMobile(mobileNum, idNum, name);
		LOG.info("验证手机 返回参数为 \n param={}", JsonUtils.toJsonString(response));
		if (response.getResult().equals(SUCCESS_CODE)) {
			return response.getData();
		} else {
			LOG.error("调用手机认证接口错误,{}", response.getDescription());
			return false;
		}
	}

	/**
	 * 验证银行卡
	 *
	 * @param bankId 银行卡号
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	@Override
	public boolean validBankCard(String bankId, String cardId, String name) {
		ValidDto<Boolean> response = geiLeValidatorService.validBankCard(bankId, cardId, name);
		LOG.info("验证银行卡 返回参数为 \n param={}", JsonUtils.toJsonString(response));
		if (response.getResult().equals(SUCCESS_CODE)) {
			return response.getData();
		} else {
			LOG.error("调用银行卡认证接口错误,{}", response.getDescription());
			return false;
		}
	}
}
