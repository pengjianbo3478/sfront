package com.gl365.app.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.aspect.Log;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.LinkMerchantDto;
import com.gl365.app.dto.MerchantHistory;
import com.gl365.app.dto.MerchantIdAndName;
import com.gl365.app.dto.MerchantSearchResultDto;
import com.gl365.app.dto.MerchantSnAndName;
import com.gl365.app.dto.SearchCondition;
import com.gl365.app.dto.command.MerchantAddPosCommand;
import com.gl365.app.dto.command.QueryMerchantByPosCommand;
import com.gl365.app.dto.merchant.NoBenefitMerchantDto;
import com.gl365.app.remote.sales.ApplicationScheduleService;
import com.gl365.app.remote.sales.MerchantService;
import com.gl365.app.service.MerchantFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by caoyilong on 2017/5/23.
 */
@Api(description = "商户相关", tags = "MERCHANT")
@RestController
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MerchantFacadeService merchantFacadeService;

	@Autowired
	private MerchantService merchantService;


	@Resource
	private ApplicationScheduleService progressService;

	@ApiOperation("根据营业执照号查询企业信息-cyl")
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/queryCompanyInfo", method = RequestMethod.POST)
	public ApiResponse<MerchantHistory> queryCompanyInfo(@RequestBody String regNo) {
		return ApiResponse.createSuccess(merchantFacadeService.queryByRegNo(regNo));
	}


	@ApiOperation("根据POS机终端号获取商户信息")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/findByDeviceId", method = RequestMethod.POST)
	public ApiResponse<MerchantSnAndName> findByDeviceId(@RequestBody QueryMerchantByPosCommand command) {
		String agentId = getCurrentLoginUserId();
		command.setAgentId(agentId);
		MerchantSnAndName dto = merchantService.findByDeviceId(command);
		return ApiResponse.createSuccess(dto);
	}


	@ApiOperation("查询当前登录业务员发展的商户名字列表")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/findMerchantNameByAgentId", method = RequestMethod.POST)
	public ApiResponse<MerchantIdAndName> findMerchantNameByAgentId() {
		List<MerchantIdAndName> dto = merchantService.findMerchantNameByAgentId(getCurrentLoginUserId());
		return ApiResponse.createSuccess(dto);
	}


	@ApiOperation("商户进件-cyl")
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/addPos", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	public ApiResponse addPos(@RequestBody MerchantAddPosCommand merchantAddPosDto) {
		String agentId = getCurrentLoginUserId();
		merchantAddPosDto.setAgentId(agentId);
		LOG.info("商户进件入参：{}", JsonUtils.toJsonString(merchantAddPosDto));
		return merchantFacadeService.addPos(merchantAddPosDto);
	}
	
	@ApiOperation("商户进件连锁店-cyl")
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/addLinkMerchant", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	public ApiResponse addLinkMerchant(@RequestBody MerchantAddPosCommand merchantAddPosDto) {
		String agentId = getCurrentLoginUserId();
		merchantAddPosDto.setAgentId(agentId);
		LOG.info("商户进件连锁店入参：{}", JsonUtils.toJsonString(merchantAddPosDto));
		return merchantFacadeService.addLinkMerchant(merchantAddPosDto);
	}

	@GetMapping("list/noAccount")
	@ApiOperation("未开户商户列表")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public ApiResponse  searchMerchantListNoAccount(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
		String agentId = getCurrentLoginUserId();
		if (StringUtils.isEmpty(agentId)){
			return ApiResponse.createErrorResponse("用户未登录");
		}
		SearchCondition condi = new SearchCondition();
		condi.setAgentId(agentId);
		condi.setPageNo(pageNo);
		condi.setPageSize(pageSize);
		List<MerchantSearchResultDto>  list = merchantService.searchMerchantNoAccount(condi);
		return ApiResponse.createSuccess(list);
	}

	@GetMapping("has/noAccount")
	@ApiOperation("是否有未开户商户")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public  ApiResponse judgeHasMerchantNoAccount(){
		String agentId = getCurrentLoginUserId();
		if (StringUtils.isEmpty(agentId)){
			return ApiResponse.createErrorResponse("用户未登录");
		}
		SearchCondition condi = new SearchCondition();
		condi.setAgentId(agentId);
		List<MerchantSearchResultDto>  list = merchantService.searchMerchantNoAccount(condi);
		boolean result = false;
		if(!CollectionUtils.isEmpty(list)){
			result = true;
		}
		return ApiResponse.createSuccess(result);
	}
	
	@GetMapping("/findLinkByLicenseNo")
	@ApiOperation("通过营业执照查询主店信息")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public  ApiResponse<LinkMerchantDto> findLinkByLicenseNo(@RequestParam("licenseNo") String licenseNo){
		return ApiResponse.createSuccess(merchantService.findLinkByLicenseNo(licenseNo));
	}
	
	@PostMapping("/noBenefit")
	@ApiOperation("商家返利变动申请")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public  ApiResponse<LinkMerchantDto> noBenefit(@RequestBody NoBenefitMerchantDto benefitMerchantDto){
		benefitMerchantDto.setAgentId(getCurrentLoginUserId());
		merchantService.noBenefit(benefitMerchantDto);
		return ApiResponse.createSuccess(null);
	}


}
