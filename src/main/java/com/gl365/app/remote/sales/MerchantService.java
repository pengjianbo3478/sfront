package com.gl365.app.remote.sales;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.LinkMerchantDto;
import com.gl365.app.dto.Merchant;
import com.gl365.app.dto.MerchantBasics;
import com.gl365.app.dto.MerchantHistory;
import com.gl365.app.dto.MerchantIdAndName;
import com.gl365.app.dto.MerchantSearchResultDto;
import com.gl365.app.dto.MerchantSnAndName;
import com.gl365.app.dto.MyMerchantPartnerDto;
import com.gl365.app.dto.SearchCondition;
import com.gl365.app.dto.command.FindMyMerchantPartnerCommand;
import com.gl365.app.dto.command.MerchantAddPosCommand;
import com.gl365.app.dto.command.QueryMerchantByPosCommand;
import com.gl365.app.dto.merchant.NoBenefitMerchantDto;

/**
 * 商户进件相关
 * Created by caoyilong on 2017/5/23.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface MerchantService {

	@RequestMapping(value = "/merchant/queryMerchantInfo", method = RequestMethod.POST)
	MerchantHistory queryByRegNo(@RequestBody String regNo);

	@RequestMapping(value = "/merchant/addPos", method = RequestMethod.POST)
	void addPos(@RequestBody MerchantAddPosCommand merchantDto);
	
	@RequestMapping(value = "/merchant/addLinkMerchant", method = RequestMethod.POST)
	void addLinkMerchant(@RequestBody MerchantAddPosCommand merchantDto);

	@RequestMapping(value = "/merchant/findMyMerchantPartner", method = RequestMethod.POST)
	MyMerchantPartnerDto findMyMerchantPartner(@RequestBody FindMyMerchantPartnerCommand request,
											   @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
											   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

	@RequestMapping(value = "/merchant/getNumDevelopedMerchants", method = RequestMethod.POST)
	public List<Map<String, Object>> getNumDevelopedMerchants(@RequestBody List<String> agentIds);

	@RequestMapping(value = "/merchant/findById", method = RequestMethod.POST)
	public Merchant findById(@RequestBody String merchantId);

	@RequestMapping(value = "/merchant/findByDeviceId", method = RequestMethod.POST)
	MerchantSnAndName findByDeviceId(@RequestBody QueryMerchantByPosCommand command);

	@RequestMapping(value = "/merchant/findMyMerchantPartnerCount", method = RequestMethod.POST)
	Integer findMyMerchantPartnerCount(@RequestBody FindMyMerchantPartnerCommand request);
	
	@RequestMapping(value = "/merchant/findMerchantCountByUpId", method = RequestMethod.POST)
	Integer findMerchantCountByUpId(@RequestBody String id);
	
	
	@RequestMapping(value = "/merchant/findMerchantNameByAgentId/{agentId}", method = RequestMethod.POST)
	List<MerchantIdAndName> findMerchantNameByAgentId(@PathVariable("agentId") String agentId);

	@RequestMapping(value = "/merchant/findByGeileMerchantNo")
    Merchant findByGeileMerchantSn(String merchantNo);

	@RequestMapping(value = "/merchant/findAllMerchantById")
	List<Merchant> findAllMerchantById(@RequestBody String id);

	@RequestMapping(value = "/merchant/findAllMerchantCountById")
	Integer findAllMerchantCountById(@RequestBody String id);

	@RequestMapping(value = "/merchant/getAgentTeamOfMerchants", method = RequestMethod.POST)
	public MyMerchantPartnerDto getAgentTeamOfMerchants(@RequestBody String agentId,
														@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
														@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);
	
	@RequestMapping(value = "/merchant/findByGeileMerchantSns", method = RequestMethod.POST)
	public MyMerchantPartnerDto findByGeileMerchantSns(@RequestBody List<String> geileMerchantSn,
			@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

	@RequestMapping(value = "/merchant/basics", method = RequestMethod.GET)
	public MerchantBasics getMerchantBasics(@RequestParam("merchantNo") String geileMerchantSn);


	@RequestMapping(value = "/merchant/search/noAccount",method = RequestMethod.POST)
	public List<MerchantSearchResultDto> searchMerchantNoAccount(SearchCondition condition);

	@RequestMapping(value = "/merchant/findLinkByLicenseNo",method = RequestMethod.GET)
	public LinkMerchantDto findLinkByLicenseNo(@RequestParam("licenseNo") String licenseNo);
	
	@RequestMapping(value = "/merchant/noBenefit",method = RequestMethod.POST)
	public  void noBenefit(@RequestBody NoBenefitMerchantDto benefitMerchantDto);
}
