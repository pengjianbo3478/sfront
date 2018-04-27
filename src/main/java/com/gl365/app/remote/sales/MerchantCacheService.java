package com.gl365.app.remote.sales;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.Company;
import com.gl365.app.dto.MerchantCacheDto;
import com.gl365.app.dto.MerchantCacheListDto;

@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface MerchantCacheService {

	@RequestMapping(value = "/api/merchant/cache/findById", method = RequestMethod.POST)
	public MerchantCacheDto findById(@RequestBody Long id);

	@RequestMapping(value = "/api/merchant/cache/findList", method = RequestMethod.GET)
	public MerchantCacheListDto findList(@RequestParam("agentId") String agentId,
			@RequestParam(value = "linkType", required = false) Integer linkType ,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) ;

	@RequestMapping(value = "/api/merchant/cache/delMerchantCache", method = RequestMethod.POST)
	public void delMerchantCache(@RequestBody Long id);

	@RequestMapping(value = "/api/merchant/cache/saveMerchantCache", method = RequestMethod.POST)
	public Long saveMerchantCache(@RequestBody MerchantCacheDto merchantCache);
	
	@RequestMapping(value = "/api/merchant/cache/company", method = RequestMethod.POST)
	public Company queryAndCreate(@RequestBody String regNo);
	
	@RequestMapping(value = "/api/merchant/cache/findByLinkMerchantId", method = RequestMethod.POST)
	public List<MerchantCacheDto> findByLinkMerchant(Long id);

	@RequestMapping(value = "/api/merchant/cache/delLinkMerchantCache", method = RequestMethod.POST)
	public void delLinkMerchantCache(Long id);

	@RequestMapping(value = "/api/merchant/cache/saveLinkMerchantCache", method = RequestMethod.POST)
	public Long saveLinkMerchantCache(List<MerchantCacheDto> merchantCache);
	
}
