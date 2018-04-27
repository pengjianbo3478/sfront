package com.gl365.app.remote.sales;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.sms.SendSMSReq;
import com.gl365.app.dto.sms.VerifySMSReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface SmsService {

	@RequestMapping(value = "/sms/sendCode", method = RequestMethod.POST)
	ApiResponse sendSmsCode(@RequestBody SendSMSReq req);

	@RequestMapping(value = "/sms/verifyCode", method = RequestMethod.POST)
	ApiResponse verifyCode(@RequestBody VerifySMSReq req);

	//用于查看发出的验证码
	//TODO:后面一点要删除了它，@cyl
	@RequestMapping(value = "/sms/queryCode/{mobile}/{type}", method = RequestMethod.GET)
	Object queryCode(@PathVariable("mobile") String mobile, @PathVariable("type") Integer type);


}
