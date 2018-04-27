package com.gl365.app.remote.validator;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 给乐生活基础系统调用(验证)
 * Created by caoyilong on 2017/6/7.
 */
@FeignClient(name="validator",url="${${env:}.url.validator:}")
public interface GeiLeValidatorService {
	/**
	 * 实名认证
	 *
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	@RequestMapping(value = "/validIdCard", method = RequestMethod.POST)
	public ValidDto<Boolean> validIdCard(@RequestParam("cardId") String cardId, @RequestParam("name") String name);

	/**
	 * 验证手机
	 *
	 * @param mobileNum 手机号
	 * @param idNum     身份证
	 * @param name      姓名
	 */
	@RequestMapping(value = "/validMobile", method = RequestMethod.POST)
	public ValidDto<Boolean> validMobile(@RequestParam("mobileNum") String mobileNum, @RequestParam("idNum") String idNum, @RequestParam("name") String name);

	/**
	 * 验证银行卡
	 *
	 * @param bankId 银行卡号
	 * @param cardId 身份证
	 * @param name   姓名
	 */
	@RequestMapping(value = "/validBankCard", method = RequestMethod.POST)
	public ValidDto<Boolean> validBankCard(@RequestParam("bankId") String bankId, @RequestParam("cardId") String cardId, @RequestParam("name") String name);

}
