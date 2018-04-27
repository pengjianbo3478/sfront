package com.gl365.app.remote.merchant;

import com.gl365.app.remote.merchant.dto.MerchantIndustryDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by caoyilong on 2017/6/8.
 * 商户系统
 */
@FeignClient(name="merchant",url="${${env:}.url.merchant:}")
public interface GeiLeMerchantService {

	/**
	 * 查询分类
	 */
	@RequestMapping(value = "/merchant/category/query", method = RequestMethod.GET)
	MerchantIndustryDto query();

}
