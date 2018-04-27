package com.gl365.app.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.dto.Agent;
import com.gl365.app.dto.AgentDetailAndMerchantProfitListDto;
import com.gl365.app.dto.AgentDetailAndMerchantTradeListDto;
import com.gl365.app.dto.ApiRequest;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.InvitePartnerRespDto;
import com.gl365.app.dto.Merchant;
import com.gl365.app.dto.MerchantBasics;
import com.gl365.app.dto.MonthSettlement;
import com.gl365.app.dto.MonthSettlementDetailDto;
import com.gl365.app.dto.MonthSettlementListDto;
import com.gl365.app.dto.MyAgentPartnerDto;
import com.gl365.app.dto.MyMerchantPartnerDto;
import com.gl365.app.dto.MyMerchantProfitList;
import com.gl365.app.dto.MyMerchantTradeList;
import com.gl365.app.dto.MyMerchantTradeListDto;
import com.gl365.app.dto.MyPartnerAndProfitDto;
import com.gl365.app.dto.PartnerDto;
import com.gl365.app.dto.PartnerPageDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.SingleSingleDetailDto;
import com.gl365.app.dto.SubAgentTradeDetailDto;
import com.gl365.app.dto.SubAgentTradeListDto;
import com.gl365.app.dto.TradeDetailDto;
import com.gl365.app.dto.UserProfitDto;
import com.gl365.app.dto.UserProfitListDto;
import com.gl365.app.dto.command.AgentRegisterCommand;
import com.gl365.app.dto.command.FindMerchantTradeConditions;
import com.gl365.app.dto.command.FindMyAgentPartnerCommand;
import com.gl365.app.dto.command.FindMyMerchantPartnerCommand;
import com.gl365.app.dto.command.InvitePartnerCommand;
import com.gl365.app.dto.command.MyPartnerQueryCommand;
import com.gl365.app.dto.enum_type.AgentMonthStatus;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.enum_type.ContributionType;
import com.gl365.app.dto.enum_type.InvitePartnerType;
import com.gl365.app.dto.enum_type.PartnerType;
import com.gl365.app.dto.sms.SendSMSReq;
import com.gl365.app.dto.sms.SendSMSType;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.member.MemberApiService;
import com.gl365.app.remote.member.api_req.QueryUserInfoCommand;
import com.gl365.app.remote.member.api_resp.QueryUserInfoDto;
import com.gl365.app.remote.member.api_resp.UserDetailDto;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.MerchantService;
import com.gl365.app.remote.sales.PartnerService;
import com.gl365.app.remote.settlement.MyPartnerApiService;
import com.gl365.app.remote.settlement.ResultDto;
import com.gl365.app.remote.settlement.ApiRequest.QueryAssociationCommand;
import com.gl365.app.remote.settlement.ApiRequest.QueryDistrictCommand;
import com.gl365.app.remote.settlement.ApiRequest.QueryMerchantSumDetailRequest;
import com.gl365.app.remote.settlement.ApiRequest.QueryPayProfitDetailsToSCommand;
import com.gl365.app.remote.settlement.ApiRequest.QueryVcAgentMonthCommand;
import com.gl365.app.remote.settlement.ApiRequest.QueryVcPayByIdCommand;
import com.gl365.app.remote.settlement.ApiRequest.ToSPayProfitBase;
import com.gl365.app.remote.settlement.ApiResponse.CooperationTotalProfitDto;
import com.gl365.app.remote.settlement.ApiResponse.FindPayResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryConUserSumByAgentResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryDistrictMerResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryDistrictResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryMerchantResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryMerchantSumDetailResponse;
import com.gl365.app.remote.settlement.ApiResponse.QueryPayProfitDetailsToSDto;
import com.gl365.app.remote.settlement.ApiResponse.QueryVcAgentMonthDto;
import com.gl365.app.remote.settlement.ApiResponse.QueryVcPayByIdDto;
import com.gl365.app.remote.settlement.enum_type.ProfitType;
import com.gl365.app.service.AgentFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.MaskUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by caoyilong on 2017/6/5.
 */
@Api(description = "合伙人相关", tags = "PARTNER")
@RestController
@RequestMapping("/api/partner")
public class PartnerController extends BaseController {

	private static final String PERSON_PROFIT = "personProfit";
	private static final String COUNTY_PROFIT = "countyProfit";
	private static final String COUNTY_OF_SERVICE_FEE = "countyOfServiceFee";
	private static final String MY_PROFIT = "myProfit";
	private static final String MY_SERVICE_FEE = "myServiceFee";
	/**
	 * 发展用户的url
	 */
	@Value("${partner.invite.userUrl}")
	private String inviteUserUrl;
	/**
	 * 县代理的url
	 */
	@Value("${partner.invite.countyUrl}")
	private String inviteCountyUrl;
	/**
	 * 业务员的url
	 */
	@Value("${partner.invite.personalUrl}")
	private String invitePersonalUrl;

	@Autowired
	private AgentFacadeService agentFacadeService;
	@Autowired
	private AgentController agentController;
	private static final String successCode = "000000";
	@Autowired
	private AgentService agentService;
	@Autowired
	private MyPartnerApiService myPartnerApiService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private MemberApiService memberApiService;

	private static final Logger LOG = LoggerFactory.getLogger(PartnerController.class);
	//"获取邀请合伙人的url-cyl
	@ApiOperation(value = "获取邀请合伙人的url-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/generateQRCodeUrl", method = RequestMethod.POST)
	public ApiResponse<InvitePartnerRespDto> generateQRCode(@RequestBody InvitePartnerCommand invitePartnerDto) {
		String agentId = getCurrentLoginUserId();
		// 从redis获得当前登录对象的信息
		AgentLoginDto agent = getCurrentUser();
		StringBuffer stringBuffer = new StringBuffer();
		// 发展用户,直接用商家app的url
		if (invitePartnerDto.getType().equals(InvitePartnerType.USER)) {
			// 市级代理recommendAgentT为2
			if (agent.getAgentType().equals(AgentType.CITY)) {
				String url = String.format(inviteUserUrl, 2, agentId);
				stringBuffer.append(url);
				// 县级别代理 recommendAgentT 为3
			} else if (agent.getAgentType().equals(AgentType.COUNTY)) {
				String url = String.format(inviteUserUrl, 3, agentId);
				stringBuffer.append(url);
			} else {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
			}

		} else if (invitePartnerDto.getType().equals(InvitePartnerType.COUNTY)) {
			// 发展县代理需要市级代理
			if (!agent.getAgentType().equals(AgentType.CITY)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
			}
			stringBuffer.append(inviteCountyUrl + "?upstreamAgentId=" + agentId + "&actingAreaId="
					+ invitePartnerDto.getActingAreaId());
		} else {
			stringBuffer.append(invitePersonalUrl + "?upstreamAgentId=" + agentId);
		}
		InvitePartnerRespDto respDto = new InvitePartnerRespDto();
		respDto.setUrl(stringBuffer.toString());
		return ApiResponse.createSuccess(respDto);
	}

	/**
	 * 开放
	 */
	@ApiOperation("注册代理-cyl")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ApiResponse register(@RequestBody @Validated AgentRegisterCommand agentRegisterDto) {
		return agentFacadeService.registerAgent(agentRegisterDto);
	}

	/**
	 * 开放的接口 发送注册的验证码
	 */
	@ApiOperation("发送验证码-cyl")
	@RequestMapping(value = "/sendRegisterVerifyCode", method = RequestMethod.POST)
	public ApiResponse sendRegisterVerifyCode(@RequestBody SendSMSReq req, HttpServletRequest request) {
		if (StringUtils.isBlank(req.getMobileNo())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_ERROR);
		}
		// 发送验证码的类型为注册
		req.setType(SendSMSType.REGISTER);
		Agent agent = agentService.findByMobile(req.getMobileNo());
		
		// 不能发送验证码
		if (null != agent &&
				agent.getStatus() != AgentStatus.UNPASS ) {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PHONE_ALREADY_REGISTER_ERROR);

		}
		return agentController.sendSms(req);
	}

	//查询市代的直接县代伙伴
	@ApiOperation("查询市代的直接县代伙伴")
	@RequestMapping(value = "/findCountyByCity", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MyPartnerAndProfitDto> findCountyByCity(@RequestBody ApiRequest<Void> request) {
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		if (AgentType.CITY != curAgent.getAgentType()) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
		}

		MyPartnerQueryCommand command = new MyPartnerQueryCommand();
		command.setQueryCounty(true);
		command.setAgentId(agentId);
		command.setAgentType(curAgent.getAgentType());
		MyPartnerAndProfitDto myPartnerAndProfitDto = getMyPartnerAndProfits(command, request.getPageNo(),
				request.getPageSize());
		// 首页查询时，查询我的总分润
		if (request.getPageNo() == 1) {
			Map<String, Object> coTotalProfitMap = getCoTotalProfit(curAgent, ContributionType.COUNTY.value());// type:1为县级贡献
			if (coTotalProfitMap != null && !coTotalProfitMap.isEmpty()
					&& coTotalProfitMap.containsKey("totalProfitFee")) {
				myPartnerAndProfitDto
						.setMyCooperationProfit(new BigDecimal(coTotalProfitMap.get("totalProfitFee").toString()));
			}
		}
		myPartnerAndProfitDto.setCountyNum(myPartnerAndProfitDto.getTotal());
		return ApiResponse.createSuccess(myPartnerAndProfitDto);
	}

	//查询直接商户伙伴
	@ApiOperation(value = "查询直接商户伙伴", notes = "有数据的登录帐号：17700010006，密码：123456")
	@RequestMapping(value = "/findMerchantPartner", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MyPartnerAndProfitDto> findMerchantPartner(@RequestBody ApiRequest<Void> request) {
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		FindMyMerchantPartnerCommand command = new FindMyMerchantPartnerCommand();
		List<String> agentIds = new ArrayList<String>();
		agentIds.add(agentId);
		command.setAgentIds(agentIds);
		LOG.info("PartnerController.findMerchantPartner invoke sales project /merchant/findMyMerchantPartner begin \n param={},pageNo={},pageSize={}",JsonUtils.toJsonString(command),request.getPageNo(),request.getPageSize());
        MyMerchantPartnerDto merchants = merchantService.findMyMerchantPartner(command, request.getPageNo(), request.getPageSize());
        LOG.info("PartnerController.findMerchantPartner invoke sales project /merchant/findMyMerchantPartner end \n rlt={}",JsonUtils.toJsonString(merchants));
		MyPartnerAndProfitDto result = new MyPartnerAndProfitDto();
		List<MyMerchantProfitList> proList = new ArrayList<MyMerchantProfitList>();
		BigDecimal myCooperationProfit = new BigDecimal(0);
		if (null != merchants.getList() && !merchants.getList().isEmpty()) {
			List<String> merchantIds = new ArrayList<String>();
			for (Merchant m : merchants.getList()) {
				if (StringUtils.isNotBlank(m.getGeileMerchantSn())) {
					merchantIds.add(m.getGeileMerchantSn());
				}
			}
			QueryDistrictCommand apiRequest = new QueryDistrictCommand();
			apiRequest.setOwnerId(agentId);
			apiRequest.setOwnerType(curAgent.getAgentType().value().toString());
			apiRequest.setQueryData(merchantIds);
			LOG.info("PartnerController.findMerchantPartner invoking settlement project begin \n url=/conMerchantSum/queryMerchant param={}",JsonUtils.toJsonString(apiRequest));
            ResultDto<List<QueryMerchantResponse>> resultDto = myPartnerApiService.queryMerchant(apiRequest);
            LOG.info("PartnerController.findMerchantPartner invoking settlement project end \n url=/conMerchantSum/queryMerchant rlt={}",JsonUtils.toJsonString(resultDto));
			for (Merchant merchant : merchants.getList()) {
				MyMerchantProfitList dto = new MyMerchantProfitList();
				dto.setId(merchant.getId());
				dto.setCompanyName(merchant.getName());
				dto.setExternalOperationName(merchant.getExternalOperationName());
				dto.setMobile(merchant.getContactMobile());
				dto.setCooperationTime(merchant.getCreationTime());
				dto.setLinkType(merchant.getLinkType());
				//dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(merchant.getAgent().getAvatarUrl()));
				if (null != resultDto && null != resultDto.getResultCode()
						&& resultDto.getResultCode().equals(successCode)) {
					for (QueryMerchantResponse resp : resultDto.getData()) {
						if (resp.getMerchantNo().equals(merchant.getGeileMerchantSn())) {
							dto.setTxnCount(resp.getTotalCount());
							dto.setTotalProfitFee(resp.getTotalProfitFee());
							dto.setTxnAmount(resp.getTxnAmount());
							break;
						}
					}
				}
				proList.add(dto);
			}
			// 查询我的总分润
			Map<String, Object> coTotalProfitMap = getCoTotalProfit(curAgent, ContributionType.MERCHANT.value());
			if (coTotalProfitMap != null && !coTotalProfitMap.isEmpty()) {
				myCooperationProfit = myCooperationProfit
						.add(new BigDecimal(coTotalProfitMap.get("totalProfitFee").toString()));
			}
			result.setTotal(merchants.getTotal());
			result.setPageNum(request.getPageNo());
			result.setPageSize(request.getPageSize());
			result.setPages(getPages(merchants.getTotal(), merchants.getPageSize()));
			result.setMerchantNum(merchants.getTotal());
		}
		result.setMyProfits(proList);
		result.setMyCooperationProfit(myCooperationProfit);
		return ApiResponse.createSuccess(result);
	}

	//查询直接业务员伙伴
	@ApiOperation(value = "查询直接业务员伙伴", notes = "有数据的帐号 13560778700")
	@RequestMapping(value = "/findPersonalPartner", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MyPartnerAndProfitDto> findPersonalPartner(@RequestBody ApiRequest<Void> request) {
		LOG.info("查询直接业务员伙伴 PartnerController.findPersonalPartner begin param={}",JsonUtils.toJsonString(request));
		MyPartnerAndProfitDto result = new MyPartnerAndProfitDto();
		List<MyMerchantProfitList> proList = new ArrayList<MyMerchantProfitList>();

		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		LOG.info("curAgent={}",JsonUtils.toJsonString(curAgent));
		MyPartnerQueryCommand command = new MyPartnerQueryCommand();
		command.setAgentId(agentId);
		command.setAgentType(curAgent.getAgentType());
		command.setQueryPersonal(true);
		command.setQueryzjPersonl(true);
		PartnerPageDto partnerPage = partnerService.findMyPartners(command, request.getPageNo(), request.getPageSize());
		LOG.info("partnerPage={}",JsonUtils.toJsonString(partnerPage));
		List<String> cPersonalIds = new ArrayList<String>();
		List<String> aPersonalIds = new ArrayList<String>();
		if (null == partnerPage.getList() || partnerPage.getList().isEmpty()) {
			result.setMyProfits(proList);
			return ApiResponse.createSuccess(result);
		}
		for (PartnerDto partner : partnerPage.getList()) {
			// 市代或县代直接业务员
			if (null != curAgent.getAgentType()
					&& (curAgent.getAgentType().equals(AgentType.COUNTY)
							|| curAgent.getAgentType().equals(AgentType.CITY))
					&& partner.getUpstreamAgentId().equals(agentId)) {
				cPersonalIds.add(partner.getId());
			} else {
				aPersonalIds.add(partner.getId());
			}
		}

		BigDecimal myCooperationProfit = new BigDecimal(0);
		Integer cooMerchantNum = new Integer(0);
		// 查询业务员合作商户数量
		cooMerchantNum = merchantService.findMerchantCountByUpId(agentId);
		LOG.info("查询业务员合作商户数量 cooMerchantNum={}",cooMerchantNum);
		// 查询我的总分润
		Map<String, Object> coTotalProfitMap = getCoTotalProfit(curAgent, ContributionType.PERSON.value().toString());
		if (coTotalProfitMap != null && !coTotalProfitMap.isEmpty()) {
			myCooperationProfit = myCooperationProfit.add(new BigDecimal(coTotalProfitMap.get("totalProfitFee").toString()));
		}
		// 查询市代县代直接业务员分润
		QueryDistrictCommand cApiRequset = new QueryDistrictCommand();
		cApiRequset.setOwnerType(curAgent.getAgentType().value().toString());
		cApiRequset.setOwnerId(agentId);
		cApiRequset.setQueryData(cPersonalIds);
		ResultDto<List<QueryDistrictResponse>> cResultDto = new ResultDto<List<QueryDistrictResponse>>();
		if (null != cPersonalIds && !cPersonalIds.isEmpty()) {
			cResultDto = myPartnerApiService.findConSalesmanSum(cApiRequset);
		}

		// 查询下级业务员分润
		QueryDistrictCommand aApiRequest = new QueryDistrictCommand();
		aApiRequest.setOwnerId(agentId);
		aApiRequest.setOwnerType(curAgent.getAgentType().value().toString());
		aApiRequest.setQueryData(aPersonalIds);
		ResultDto<List<QueryDistrictResponse>> aResultDto = new ResultDto<List<QueryDistrictResponse>>();
		if (null != aPersonalIds && !aPersonalIds.isEmpty()) {
			aResultDto = myPartnerApiService.queryConSecondSalesmanDaily(aApiRequest);
		}

		for (PartnerDto partner : partnerPage.getList()) {
			MyMerchantProfitList dto = new MyMerchantProfitList();
			dto.setId(partner.getId());
			dto.setExCode(partner.getId());
			dto.setName(partner.getName());
			dto.setCooperationTime(partner.getCreationTime());
			dto.setMobile(partner.getMobile());
			dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(partner.getAvatarUrl()));
			dto.setIsArea(partner.getIsArea());
			if (null != cResultDto && null != cResultDto.getResultCode()
					&& cResultDto.getResultCode().equals(successCode)) {
				for (QueryDistrictResponse resp : cResultDto.getData()) {
					if (resp.getAgentNo().equals(partner.getId())) {
						dto.setTxnCount(resp.getTxnCount());
						dto.setTotalProfitFee(resp.getTotalProfitFee());
						dto.setTxnAmount(resp.getTxnAmount());
						break;
					}
				}
			} else if (null != aResultDto && null != aResultDto.getResultCode()
					&& aResultDto.getResultCode().equals(successCode)) {
				for (QueryDistrictResponse resp : aResultDto.getData()) {
					if (resp.getAgentNo().equals(partner.getId())) {
						dto.setTxnCount(resp.getTxnCount());
						dto.setTotalProfitFee(resp.getTotalProfitFee());
						dto.setTxnAmount(resp.getTxnAmount());
						break;
					}
				}
			}
			proList.add(dto);
		}
		result.setTotal(partnerPage.getTotal());
		result.setPageNum(request.getPageNo());
		result.setPageSize(request.getPageSize());
		result.setPages(getPages(partnerPage.getTotal(), request.getPageSize()));
		result.setPages(partnerPage.getPages());

		result.setPersonalNum(partnerPage.getTotal());
		result.setMerchantNum(new Long(cooMerchantNum));
		result.setMyCooperationProfit(myCooperationProfit);
		result.setMyProfits(proList);
		return ApiResponse.createSuccess(result);
	}

	//查询直接用户伙伴
	@ApiOperation(value = "查询直接用户伙伴", notes = "登录帐号：13560778700，密码：123456")
	@RequestMapping(value = "/findCustomerPartner", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<UserProfitListDto> findCustomerPartner(@RequestBody ApiRequest<Void> request) {
		return findCustomerPartnerAndTel(request, null);
	}

	private ApiResponse<UserProfitListDto> findCustomerPartnerAndTel(ApiRequest<Void> request, String mobilePhone) {
		UserProfitListDto result = new UserProfitListDto();
		List<UserProfitDto> dtos = new ArrayList<UserProfitDto>();
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		QueryUserInfoCommand userInfoCommand = new QueryUserInfoCommand();
		userInfoCommand.setCurPage(request.getPageNo());
		userInfoCommand.setPageSize(request.getPageSize());
		userInfoCommand.setMobilePhone(mobilePhone);
		userInfoCommand.setRecommendAgentId(curAgent.getId());
		userInfoCommand.setRecommendAgentT(curAgent.getAgentType().value().toString());
		com.gl365.app.remote.member.ResultDto<QueryUserInfoDto> userResultDto = memberApiService
				.queryUserInfo(userInfoCommand);
		if (null == userResultDto || null == userResultDto.getResult()
				|| !userResultDto.getResult().equals(successCode)) {
			return ApiResponse.createSuccess(result);
		}
		List<String> userIds = new ArrayList<String>();
		for (UserDetailDto user : userResultDto.getData().getList()) {
			userIds.add(user.getUserId());
		}
		QueryDistrictCommand command = new QueryDistrictCommand();
		command.setOwnerId(curAgent.getId());
		command.setOwnerType(curAgent.getAgentType().value().toString());
		command.setQueryData(userIds);
		ResultDto<List<QueryConUserSumByAgentResponse>> resultDto = myPartnerApiService.queryConUserSumByAgent(command);

		for (UserDetailDto user : userResultDto.getData().getList()) {
			UserProfitDto dto = new UserProfitDto();
			dto.setUserId(user.getUserId());
			dto.setMobile(user.getMobilePhone());
			dto.setUserName(user.getRealName());
			dto.setPhoto(user.getPhoto());
			dto.setRegisterTime(user.getRegisterTime());
			if (null != resultDto.getResultCode() && resultDto.getResultCode().equals(successCode)) {
				for (QueryConUserSumByAgentResponse resp : resultDto.getData()) {
					if (resp.getUserId().equals(user.getUserId())) {
						dto.setTotalProfitFee(resp.getTotalProfitFee());
						dto.setTxnAmount(resp.getTxnAmount());
						dto.setTxnCount(resp.getTxnCount());
						dto.setUserName(resp.getUserName());
					}
				}

			}
			dtos.add(dto);
		}
		BigDecimal myCooperationProfit = new BigDecimal(0);
		// 我的分润
		Map<String, Object> coTotalProfitMap = getCoTotalProfit(curAgent, ContributionType.USER.value());
		if (coTotalProfitMap != null && !coTotalProfitMap.isEmpty()) {
			myCooperationProfit = myCooperationProfit
					.add(new BigDecimal(coTotalProfitMap.get("totalProfitFee").toString()));
		}
		result.setMyCooperationProfit(myCooperationProfit);
		result.setUserCount(userResultDto.getData().getTotalCount().longValue());
		result.setList(dtos);
		result.setPageNum(request.getPageNo());
		result.setPageSize(request.getPageSize());
		result.setTotal(userResultDto.getData().getTotalCount().longValue());
		result.setPages(userResultDto.getData().getTotalPage());
		return ApiResponse.createSuccess(result);
	}

	//查询直接用户详情页的商户列表
	@ApiOperation(value = "查询直接用户详情页的商户列表", notes = "登录帐号：13560778700，密码：123456")
	@RequestMapping(value = "/findCustomerDetailOfMerchants/{userId}", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<AgentDetailAndMerchantProfitListDto> findCustomerDetailOfMerchants(
			@PathVariable("userId") String userId, @RequestBody ApiRequest<Void> request) {
		AgentDetailAndMerchantProfitListDto result = new AgentDetailAndMerchantProfitListDto();
		List<MyMerchantProfitList> dtos = new ArrayList<MyMerchantProfitList>();
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		QueryPayProfitDetailsToSCommand apiRequest = new QueryPayProfitDetailsToSCommand();
		apiRequest.setOwnerId(agentId);
		apiRequest.setOwnerType(curAgent.getAgentType().value().toString());
		apiRequest.setPageNum(request.getPageNo());
		apiRequest.setNumPerPage(request.getPageSize());
		apiRequest.setUserId(userId);
		ResultDto<List<QueryPayProfitDetailsToSDto>> resultDto = myPartnerApiService
				.queryPayProfitDetailsToS(apiRequest);
		if (null != resultDto && null != resultDto.getResultCode() && resultDto.getResultCode().equals(successCode)) {

			// 获取商户头像
			List<Merchant> merchants = new ArrayList<>();
			if (resultDto.getData() != null && resultDto.getData().size() > 0) {
				List<String> geileMerchantSn = new ArrayList<>();
				for (QueryPayProfitDetailsToSDto rlt : resultDto.getData()) {
					geileMerchantSn.add(rlt.getMerchantNo());
				}
				MyMerchantPartnerDto merchantDto = merchantService.findByGeileMerchantSns(geileMerchantSn, 1,
						resultDto.getData().size());
				if (null != merchantDto && null != merchantDto.getList() && merchantDto.getList().size() > 0) {
					merchants = merchantDto.getList();
				}
			}

			for (QueryPayProfitDetailsToSDto resp : resultDto.getData()) {
				MyMerchantProfitList dto = new MyMerchantProfitList();
				dto.setCooperationTime(resp.getPayTime());
				dto.setName(resp.getMerchantName());
				dto.setTotalProfitFee(resp.getOwnerProfitAmount());
				dto.setTxnAmount(resp.getTxnAmount());
				dto.setId(resp.getPayId());

				for (Merchant merchant : merchants) {
					if (null != resp.getMerchantNo() && resp.getMerchantNo().equals(merchant.getGeileMerchantSn())) {
						dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(merchant.getAgent().getAvatarUrl()));
						dto.setExternalOperationName(merchant.getExternalOperationName());
						break;
					}
				}

				dtos.add(dto);
			}
		}
		result.setMyProfitList(dtos);
		result.setPageNum(request.getPageNo());
		result.setPageSize(request.getPageSize());
		result.setTotal(resultDto.getTotalNum());
		result.setPages(getPages(resultDto.getTotalNum(), request.getPageSize()));
		return ApiResponse.createSuccess(result);
	}

	//查询间接的商户交易列表
	@ApiOperation(value = "查询间接的商户交易列表", notes = "用于业务员详情，县代详情，有数据的merchantId:5226bcb1c30146f1b99fb1b16fd059c3 ， agentId: 4000010")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/findIndirectMerchantTrades", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<AgentDetailAndMerchantTradeListDto> findIndirectMerchantTrades(
			@RequestBody ApiRequest<FindMerchantTradeConditions> request) {
		String agentId = request.getData().getAgentId();
		Agent agent = agentService.findById(agentId);
		
		Agent loginAgent = agentService.findById(getCurrentLoginUserId());
		String merchantId = request.getData().getMerchantId();
		Merchant merchant = merchantService.findById(merchantId);
		if(merchant == null){
			merchant = merchantService.findByGeileMerchantSn(merchantId);
		}
		// 查询交易列表
		MyMerchantTradeListDto tradeListDto = getMerchantTradeList(loginAgent,agent, merchant, request.getPageNo(),
				request.getPageSize());
		AgentDetailAndMerchantTradeListDto result = new AgentDetailAndMerchantTradeListDto();
		result.setCompanyName(merchant.getName());
		result.setExternalOperationName(merchant.getExternalOperationName());
		result.setLinkType(merchant.getLinkType());
		result.setCooperationTime(merchant.getCreationTime());
		result.setDetailedAddress(merchant.getDetailedAddress());
		result.setContactMobile(merchant.getContactMobile());
		result.setContactName(merchant.getContactName());
		if (null != tradeListDto && !tradeListDto.getData().isEmpty()) {
			result.setMyTradeList(tradeListDto.getData());
			result.setPageNum(tradeListDto.getPageNum());
			result.setPageSize(tradeListDto.getPageSize());
			result.setPages(tradeListDto.getPages());
			result.setTotal(tradeListDto.getTotal());
		} else {
			result.setMyTradeList(new ArrayList<MyMerchantTradeList>());
		}

		return ApiResponse.createSuccess(result);
	}

	//查询间接的商户伙伴
	@ApiOperation(value = "查询间接的商户伙伴", notes = "用于县代详情，业务员详情,模拟参数：agentId:4000010 ,登录帐号：13560778700，密码：1234567")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/findIndirectMerchantPartner/{agentId}", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<AgentDetailAndMerchantProfitListDto> findIndirectMerchantPartner(
			@PathVariable("agentId") String agentId, @RequestBody ApiRequest<MyPartnerQueryCommand> request) {
		Agent curAgent = agentService.findById(agentId);
		if (curAgent == null
				|| (curAgent.getAgentType() != AgentType.PERSONAL && curAgent.getAgentType() != AgentType.COUNTY)) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
		}
		Integer subAgentNum = new Integer(0);
		MyMerchantPartnerDto merchantDto = new MyMerchantPartnerDto();
		if (curAgent != null && curAgent.getAgentType() == AgentType.PERSONAL) {
			// 查询直接的商户列表
			FindMyMerchantPartnerCommand command = new FindMyMerchantPartnerCommand();
			List<String> agentIds = new ArrayList<String>();
			agentIds.add(agentId);
			command.setAgentIds(agentIds);
			command.setCrossAgentId(getCurrentLoginUserId());
			merchantDto = merchantService.findMyMerchantPartner(command, request.getPageNo(), request.getPageSize());

			// 业务员类型的时候，查询下级业务员数量
			FindMyAgentPartnerCommand subAgentCommand = new FindMyAgentPartnerCommand();
			subAgentCommand.setUpstreamAgentId(agentId);
			subAgentNum = agentService.findMyAgentPartnerCount(subAgentCommand);

		} else if (curAgent != null && curAgent.getAgentType() == AgentType.COUNTY) {
			// 查询县代及自己团队发展的商户
			merchantDto = merchantService.getAgentTeamOfMerchants(agentId, request.getPageNo(), request.getPageSize());
		}
		AgentDetailAndMerchantProfitListDto result = new AgentDetailAndMerchantProfitListDto();
		List<MyMerchantProfitList> proList = new ArrayList<MyMerchantProfitList>();
		if (null != merchantDto.getList() && !merchantDto.getList().isEmpty()) {
			List<String> merchantIds = new ArrayList<String>();
			for (Merchant m : merchantDto.getList()) {
				if (StringUtils.isNotBlank(m.getGeileMerchantSn())) {
					merchantIds.add(m.getGeileMerchantSn());
				}
			}
			QueryDistrictCommand apiRequest = new QueryDistrictCommand();
			apiRequest.setOwnerId(curAgent.getId());
			apiRequest.setOwnerType(curAgent.getAgentType().value().toString());
			
			AgentLoginDto loginDto = getCurrentUser();
			apiRequest.setParentAgentNo(loginDto.getId());
			apiRequest.setParentAgentType(loginDto.getAgentType().value().toString());
			apiRequest.setQueryData(merchantIds);
			
			LOG.info("myPartnerApiService.queryDistrictMer invoke settlement project begin param={}",JsonUtils.toJsonString(apiRequest));
			ResultDto<List<QueryDistrictMerResponse>>   resultDto = myPartnerApiService.queryDistrictMer(apiRequest);
			LOG.info("myPartnerApiService.queryDistrictMer invoke settlement project end rlt={}",JsonUtils.toJsonString(resultDto));
			/*if (curAgent != null && curAgent.getAgentType() == AgentType.PERSONAL) {
				// 查询直接的商户列表
				resultDto = myPartnerApiService.queryMer(apiRequest);
			} else if (curAgent != null && curAgent.getAgentType() == AgentType.COUNTY) {
				// 查询县代及自己团队发展的商户
				resultDto = myPartnerApiService.queryDistrictMer(apiRequest);
			}*/
			
			for (Merchant merchant : merchantDto.getList()) {
				MyMerchantProfitList dto = new MyMerchantProfitList();
				dto.setId(merchant.getId());
				dto.setName(merchant.getName());
				dto.setExternalOperationName(merchant.getExternalOperationName());
				dto.setLinkType(merchant.getLinkType());
				dto.setCooperationTime(merchant.getCreationTime());
				//dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(merchant.getAgent().getAvatarUrl()));
				if (null != resultDto && resultDto.getResultCode().equals(successCode)) {
					for (QueryDistrictMerResponse resp : resultDto.getData()) {
						if (resp.getMerchantNo().equals(merchant.getGeileMerchantSn())) {
							dto.setTxnCount(resp.getTxnCount());
							dto.setTotalProfitFee(resp.getTotalProfitFee());
							dto.setTxnAmount(resp.getTxnAmount());
						}
					}
				}
				proList.add(dto);
			}
			try {
				LOG.info("===>merchantDto:{},resultDto:{}",JsonUtils.toJsonString(merchantDto),JsonUtils.toJsonString(resultDto));
			} catch (Exception e) {
			}
			
			result.setTotal(merchantDto.getTotal());
			result.setPageNum(request.getPageNo());
			result.setPageSize(request.getPageSize());
			result.setPages(getPages(merchantDto.getTotal(), merchantDto.getPageSize()));
		}
		result.setAgentId(curAgent.getId());
		result.setMobile(curAgent.getMobile());
		result.setName(curAgent.getName());
		result.setDetailedAddress(curAgent.getDetailedAddress());
		result.setCompanyName(curAgent.getCompanyName());
		result.setCooperationTime(curAgent.getCreationTime());
		result.setSubPersonalNum(subAgentNum);
		result.setMyProfitList(proList);
		return ApiResponse.createSuccess(result);
	}

	//查询单笔交易分润详情
	@ApiOperation(value = "查询单笔交易分润详情")
	@RequestMapping(value = "/findSingleTradeDetail/{payId}", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<SingleSingleDetailDto> findSingleTradeDetail(@PathVariable("payId") String payId,@PathParam("settleFlag")String settleFlag) {
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		FindPayResponse data = null;
		if("N".equals(settleFlag)){
			data = myPartnerApiService.findByPayId(payId);
		}else{
			data = myPartnerApiService.mergetxnFindByPayId(payId);
		}
		ResultDto<List<QueryMerchantSumDetailResponse>> resultDetailDto = myPartnerApiService.findByPayDetailId(payId);
		SingleSingleDetailDto result = new SingleSingleDetailDto();
		if (data != null) {
			// 查询所属代理商 
			if(StringUtils.isNotBlank(data.getMerchantNo())){
				MerchantBasics merchantBasics = merchantService.getMerchantBasics(data.getMerchantNo());
				if(merchantBasics != null){
					result.setAgentName(merchantBasics.getAgentName());
					result.setExternalOperationName(merchantBasics.getExternalOperationName());
					result.setMerchantName(merchantBasics.getName());
					result.setLinkType(merchantBasics.getLinkType());
				}
			}
			result.setCardNo(MaskUtils.bankCardNoMask(data.getCardNo()));
			// result.setIssuer(resp.get); todo 返回数据中暂无该字段
			if(StringUtils.isBlank(result.getMerchantName())){
				result.setMerchantName(data.getMerchantName());
			}
			result.setPayTime(data.getPayTime());
			result.setTotalAmount(data.getTotalAmount());
			result.setTerminal(data.getTerminal()==null?"--":data.getTerminal());
			result.setMerchantNo(data.getMerchantNo());
			result.setSettleFlag(data.getSettleFlag());
			result.setCheckFlag(data.getCheckFlag());
			result.setPayStatus(data.getPayStatus());
		}
		if (resultDetailDto != null && resultDetailDto.getResultCode() != null
				&& resultDetailDto.getResultCode().equals(successCode)) {
			Map<String, BigDecimal> myProfitData = new HashMap<String, BigDecimal>();
			myProfitData.put(MY_SERVICE_FEE, new BigDecimal(0));
			myProfitData.put(MY_PROFIT, new BigDecimal(0));

			myProfitData.put(COUNTY_OF_SERVICE_FEE, new BigDecimal(0));
			myProfitData.put(COUNTY_PROFIT, new BigDecimal(0));

			myProfitData.put(PERSON_PROFIT, new BigDecimal(0));

			for (QueryMerchantSumDetailResponse resp : resultDetailDto.getData()) {
				findMyProfitDetail(myProfitData, curAgent, resp);
			}
			result.setMyProfit(myProfitData.get(MY_PROFIT));
			result.setMyServiceFee(myProfitData.get(MY_SERVICE_FEE));// 市

			result.setCountyProfit(myProfitData.get(COUNTY_PROFIT));
			result.setCountyOfServiceFee(myProfitData.get(COUNTY_OF_SERVICE_FEE));// 县

			result.setPersonProfit(myProfitData.get(PERSON_PROFIT));// 业务员

		}
		return ApiResponse.createSuccess(result);
	}

	private void findMyProfitDetail(Map<String, BigDecimal> result, Agent curAgent,
			QueryMerchantSumDetailResponse resp) {
		if (curAgent.getAgentType() == AgentType.CITY && curAgent.getId().equals(resp.getOwnerId())
				&& String.valueOf(AgentType.CITY.value()).equals(resp.getOwnerType())) {

			if (ProfitType.CITY_SERVICE_FEE.value().equals(resp.getProfitType())
					|| ProfitType.CITY_OR_COUNT_SERVICE_FEE.value().equals(resp.getProfitType())) {
				result.put(MY_SERVICE_FEE, result.get(MY_SERVICE_FEE).add(resp.getOwnerProfitAmount()));
			} else {
				result.put(MY_PROFIT, result.get(MY_PROFIT).add(resp.getOwnerProfitAmount()));
			}

		} else if (String.valueOf(AgentType.COUNTY.value()).equals(resp.getOwnerType())) {

			if (ProfitType.CITY_SERVICE_FEE.value().equals(resp.getProfitType())
					|| ProfitType.CITY_OR_COUNT_SERVICE_FEE.value().equals(resp.getProfitType())) {
				result.put(COUNTY_OF_SERVICE_FEE, result.get(COUNTY_OF_SERVICE_FEE).add(resp.getOwnerProfitAmount()));
				if (curAgent.getId().equals(resp.getOwnerId())) {
					result.put(MY_SERVICE_FEE, result.get(MY_SERVICE_FEE).add(resp.getOwnerProfitAmount()));
				}
			} else {
				result.put(COUNTY_PROFIT, result.get(COUNTY_PROFIT).add(resp.getOwnerProfitAmount()));
				if (curAgent.getId().equals(resp.getOwnerId())) {
					result.put(MY_PROFIT, result.get(MY_PROFIT).add(resp.getOwnerProfitAmount()));
				}
			}

		} else if (String.valueOf(AgentType.PERSONAL.value()).equals(resp.getOwnerType())) {
			if (curAgent.getId().equals(resp.getOwnerId())) {
				result.put(MY_PROFIT, result.get(MY_PROFIT).add(resp.getOwnerProfitAmount()));
			} else {
				result.put(PERSON_PROFIT, result.get(PERSON_PROFIT).add(resp.getOwnerProfitAmount()));
			}
		}
	}

	//查询业务员的下级业务员分润情况
	@ApiOperation(value = "查询业务员的下级业务员分润情况", notes = "模拟数据参数：agentId 4000010")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/findSubPersonalProfitDetail/{agentId}", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<SubAgentTradeListDto> findSubPersonalProfitDetail(HttpServletRequest request,@PathVariable("agentId") String agentId,
			@RequestBody ApiRequest<MyPartnerQueryCommand> queryCommand) {
		
		String currentLoginUserId = (String)request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		LOG.info("=========findSubPersonalProfitDetail===currentLoginUserId=====>"+currentLoginUserId);
		
		Agent upAgent = agentService.findById(agentId);
		FindMyAgentPartnerCommand command = new FindMyAgentPartnerCommand();
		command.setUpstreamAgentId(agentId);
		command.setAgentType(AgentType.PERSONAL);
		if (queryCommand.getData() != null && queryCommand.getData().isCrossAgent()) {
			command.setCrossAgentId(currentLoginUserId);
		}
		MyAgentPartnerDto agentPartnerDto = agentService.findMyAgentPartner(command, queryCommand.getPageNo(),
				queryCommand.getPageSize());
		SubAgentTradeListDto result = new SubAgentTradeListDto();

		if (null == agentPartnerDto || agentPartnerDto.getList().isEmpty()) {
			return ApiResponse.createSuccess(result);
		}

		List<SubAgentTradeDetailDto> data = new ArrayList<SubAgentTradeDetailDto>();
		List<String> subAgentIds = new ArrayList<String>();
		for (Agent a : agentPartnerDto.getList()) {
			subAgentIds.add(a.getId());
		}
		QueryDistrictCommand apiRequest = new QueryDistrictCommand();
		AgentLoginDto agentdto = getCurrentUser();
		apiRequest.setOwnerId(agentdto.getId()); 
		apiRequest.setOwnerType(agentdto.getAgentType().value().toString());
		apiRequest.setQueryData(subAgentIds);
		ResultDto<List<QueryDistrictResponse>> resultDto = myPartnerApiService.queryConSecondSalesmanDaily(apiRequest);
		for (Agent agent : agentPartnerDto.getList()) {
			SubAgentTradeDetailDto dto = new SubAgentTradeDetailDto();
			// 查询发展商户数量
			List<String> agentIds = new ArrayList<String>();
			agentIds.add(agent.getId());
			FindMyMerchantPartnerCommand numCommand = new FindMyMerchantPartnerCommand();
			numCommand.setAgentIds(agentIds);
			numCommand.setCrossAgentId(currentLoginUserId);
			Integer merchantNum = new Integer(0);
			merchantNum = merchantService.findMyMerchantPartnerCount(numCommand);
			dto.setName(agent.getName());
			dto.setMobile(agent.getMobile());
			dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(agent.getAvatarUrl()));
			dto.setMerchantNum(merchantNum);
			if (null != resultDto && resultDto.getResultCode() != null
					&& resultDto.getResultCode().equals(successCode)) {
				for (QueryDistrictResponse resp : resultDto.getData()) {
					if (resp.getAgentNo().equals(agent.getId())) {
						dto.setMyProfit(resp.getTotalProfitFee());
						dto.setTxnCount(resp.getTxnCount());
						dto.setTxnAmount(resp.getTxnAmount());
						break;
					}
				}
			}
			data.add(dto);
		}
		result.setData(data);
		result.setTotal(agentPartnerDto.getTotal());
		result.setPageNum(queryCommand.getPageNo());
		result.setPageSize(queryCommand.getPageSize());
		result.setPages(getPages(agentPartnerDto.getTotal(), agentPartnerDto.getPageSize()));
		return ApiResponse.createSuccess(result);
	}

	//查询商户日交易详情
	@ApiOperation(value = "查询商户日交易详情")
	@RequestMapping(value = "/findMerchantTradeDetail/{payId}", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<List<TradeDetailDto>> findMerchantTradeDetail(@PathVariable("payId") String payId) {
		String agentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(agentId);
		List<TradeDetailDto> result = new ArrayList<TradeDetailDto>();
		ResultDto<List<QueryMerchantSumDetailResponse>> resultDto = myPartnerApiService.findByPayDetailId(payId);
		if (resultDto != null && resultDto.getResultCode() != null && resultDto.getResultCode().equals(successCode)) {
			for (QueryMerchantSumDetailResponse resp : resultDto.getData()) {
				// 过滤数据
				if (resp.getOwnerId().equals(curAgent.getId())
						&& resp.getOwnerType().equals(curAgent.getAgentType().value().toString())) {
					TradeDetailDto dto = new TradeDetailDto();
					dto.setTradeTime(resp.getPayTime());
					dto.setMyProfit(resp.getOwnerProfitAmount());
					dto.setTradeAmount(resp.getTxnAmount());
					result.add(dto);
				}
			}
		}
		return ApiResponse.createSuccess(result);
	}

	//查询商户日交易列表
	@ApiOperation(value = "查询商户日交易列表", notes = "有数据的参数：merchantId:508c6c660a364c8f8b64c61e6310b1a7 ，当前登录帐号：17700010006 ，密码：123456 ")
	@RequestMapping(value = "/findMerchantTrades/{merchantId}", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<AgentDetailAndMerchantTradeListDto> findMerchantTrades(
			@PathVariable("merchantId") String merchantId, @RequestBody ApiRequest<Void> request) {
		String curAgentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(curAgentId);
		Merchant merchant = merchantService.findById(merchantId);
		if(merchant == null){
			merchant = merchantService.findByGeileMerchantSn(merchantId);
		}
		// 查询交易列表
		MyMerchantTradeListDto tradeListDto = getMerchantDateTradeList(curAgent, merchant, request.getPageNo(),request.getPageSize());

		AgentDetailAndMerchantTradeListDto result = new AgentDetailAndMerchantTradeListDto();
		result.setCompanyName(merchant.getName());
		result.setCooperationTime(merchant.getCreationTime());
		result.setDetailedAddress(merchant.getDetailedAddress());
		result.setContactMobile(merchant.getContactMobile());
		result.setContactName(merchant.getContactName());
		result.setNoBenefit(merchant.getNoBenefit());
		result.setExternalOperationName(merchant.getExternalOperationName());
		if (null != tradeListDto && !tradeListDto.getData().isEmpty()) {
			result.setMyTradeList(tradeListDto.getData());
			result.setPageNum(tradeListDto.getPageNum());
			result.setPageSize(tradeListDto.getPageSize());
			result.setPages(tradeListDto.getPages());
			result.setTotal(tradeListDto.getTotal());
		} else {
			result.setMyTradeList(new ArrayList<MyMerchantTradeList>());
		}
		return ApiResponse.createSuccess(result);
	}

	//查询月结列表
	@ApiOperation(value = "查询月结列表")
	@RequestMapping(value = "/findMonthSettlementList", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MonthSettlementListDto> findMonthSettlementList(@RequestBody ApiRequest<Void> request) {
		String curAgentId = getCurrentLoginUserId();
		Agent curAgent = agentService.findById(curAgentId);
		QueryVcAgentMonthCommand command = new QueryVcAgentMonthCommand();
		command.setGetNumPerPage(request.getPageNo());
		command.setNumPerPage(request.getPageSize());
		command.setOwnerId(curAgentId);
		command.setOwnerType(curAgent.getAgentType().value().toString());
		command.setQueryType("1");
		ResultDto<List<QueryVcAgentMonthDto>> resultDto = myPartnerApiService.queryVcAgentMonth(command);
		MonthSettlementListDto result = new MonthSettlementListDto();
		List<MonthSettlement> list = new ArrayList<MonthSettlement>();
		if (null != resultDto.getResultCode() && resultDto.getResultCode().equals(successCode)) {
			for (QueryVcAgentMonthDto dto : resultDto.getData()) {
				MonthSettlement settlement = new MonthSettlement();
				settlement.setVoucherId(dto.getVoucherId());
				settlement.setCashMoney(dto.getCashMoney());
				settlement.setSettleMonth(dto.getSettleMonth());
				settlement.setFeeMoney(dto.getFeeMoney());
				settlement.setRealMoney(dto.getRealMoney());
				settlement.setTaxMoney(dto.getTaxMoney());
				settlement.setSupplyTax(dto.getSupplyTax());
				
				settlement.setBankAccountNo(dto.getBankAccountNo());
				settlement.setBankAccountName(dto.getBankAccountName());
				settlement.setBankName(dto.getBankName());
				settlement.setBankNo(dto.getBankNo());
				settlement.setCertNo(dto.getCertNo());
				settlement.setBankType(dto.getBankType());
				// LocalDateTime curTime = LocalDateTime.now();
				// LocalDateTime monthStartTime =
				// LocalDateTime.of(curTime.toLocalDate().with(TemporalAdjusters.firstDayOfMonth())
				// , LocalTime.of(0, 0, 0));
				// LocalDateTime monthEndTime =
				// curTime.with(TemporalAdjusters.lastDayOfMonth());

				try {
					settlement.setFromTime(LocalDateTime.of(LocalDate.parse(monthBegin(dto.getSettleMonth(), "yyyyMM")),
							LocalTime.MIN));
					settlement.setToTime(
							LocalDateTime.of(LocalDate.parse(monthEnd(dto.getSettleMonth(), "yyyyMM")), LocalTime.MAX));
				} catch (ParseException e) {
					LOG.info("时间转换异常" + e.getMessage());
				}
				settlement.setStatus(AgentMonthStatus.getEnumTextByCode(dto.getState()));

				// if (curTime.isEqual(monthEndTime)) {
				// settlement.setStatus("已结算");
				// } else {
				// settlement.setStatus("未结算");
				// }
				list.add(settlement);
			}
			result.setTotal(resultDto.getTotalNum());
			result.setPageNum(request.getPageNo());
			result.setPageSize(request.getPageSize());
			result.setPages(getPages(resultDto.getTotalNum(), request.getPageSize()));
		}
		result.setList(list);
		return ApiResponse.createSuccess(result);
	}

	///查询月结详情
	@ApiOperation(value = "查询月结详情")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/findMonthSettlementDetail/{voucherId}", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MonthSettlementDetailDto> findMonthSettlementDetail(
			@PathVariable("voucherId") String voucherId) {
		MonthSettlementDetailDto result = new MonthSettlementDetailDto();
		QueryVcPayByIdCommand vcCommand = new QueryVcPayByIdCommand();
		vcCommand.setNumPerPage(20);
		vcCommand.setPageNum(1);
		vcCommand.setVoucherId(voucherId);
		ResultDto<List<QueryVcPayByIdDto>> voucherDto = myPartnerApiService.queryVcPayByVoucherId(vcCommand);
		if (null != voucherDto.getResultCode() && voucherDto.getResultCode().equals(successCode)
				&& CollectionUtils.isNotEmpty(voucherDto.getData())) {
			QueryVcPayByIdDto dto = voucherDto.getData().get(0);
			result.setBankAccountName(dto.getBankAccountName());
			result.setBankAccountNo(dto.getBankAccountNo());
			result.setPayTime(dto.getPayTime());
		}

		return ApiResponse.createSuccess(result);
	}

	//搜索我的合伙人
	@ApiOperation("搜索我的合伙人")
	@RequestMapping(value = "/searchMyPartners", method = RequestMethod.POST)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MyPartnerAndProfitDto> searchMyPartners(
			@Validated @RequestBody ApiRequest<MyPartnerQueryCommand> request) {
		MyPartnerQueryCommand command = request.getData();
		String agentId = getCurrentLoginUserId();
		command.setAgentId(agentId);
		Agent curAgent = agentService.findById(agentId);
		command.setAgentType(curAgent.getAgentType());
		if (AgentType.CITY == curAgent.getAgentType()) {
			command.setQueryCounty(true);
			command.setQueryPersonal(true);
		}else if (AgentType.COUNTY == curAgent.getAgentType()) {
			command.setQueryPersonal(true);
		}
		
		command.setQueryMerchant(true);
		command.setQueryzjPersonl(true);
		MyPartnerAndProfitDto result = getMyPartnerAndProfits(command, request.getPageNo(), request.getPageSize());
		
		// 查询用户
		if (StringUtils.isNotBlank(command.getKeyword())) {
			List<MyMerchantProfitList>  myProfits = result.getMyProfits();
			if(CollectionUtils.isEmpty(myProfits)){
				myProfits = new ArrayList<>();
			}
			
			ApiRequest<Void> param = new ApiRequest<>();
			param.setPageNo(request.getPageNo());
			int pageSize = request.getPageSize().intValue() - myProfits.size();
			param.setPageSize(pageSize);
			ApiResponse<UserProfitListDto> user = null;
			if(pageSize > 0){
				user = findCustomerPartnerAndTel(param, command.getKeyword());
			}
			List<UserProfitDto> userList = new ArrayList<>();
			if(user != null && user.getData() != null && CollectionUtils.isNotEmpty(user.getData().getList())){
				userList = user.getData().getList();
			}
			
			int userNum = 0;
			for (int i = myProfits.size(); i < request.getPageSize(); i++) {
				if(userList.size() > userNum){
					UserProfitDto source = userList.get(userNum);
					MyMerchantProfitList target = new MyMerchantProfitList();
					target.setName(source.getUserName());
					target.setMobile(source.getMobile());
					target.setTxnCount(source.getTxnCount());
					target.setTxnAmount(new BigDecimal(source.getTxnAmount()));
					target.setTotalProfitFee(new BigDecimal(source.getTotalProfitFee()));
					target.setUserId(source.getUserId());
					target.setPhoto(source.getPhoto());
					target.setRegisterTime(source.getRegisterTime());
					target.setPartnerType(PartnerType.USER);
					myProfits.add(target);
					userNum+=1;
				}
			}
			result.setMyProfits(myProfits);
		}
		
		return ApiResponse.createSuccess(result);
	}

	private MyPartnerAndProfitDto getMyPartnerAndProfits(MyPartnerQueryCommand command, Integer pageNo,
			Integer pageSize) {
		PartnerPageDto partnerPage = partnerService.findMyPartners(command, pageNo, pageSize);
		List<String> countyIds = new ArrayList<>();
		List<String> personalIds = new ArrayList<>();
		List<String> merchantIds = new ArrayList<>();
		List<MyMerchantProfitList> myProfits = new ArrayList<>();
		if (null != partnerPage.getList() && !partnerPage.getList().isEmpty()) {
			for (PartnerDto partner : partnerPage.getList()) {
				if (AgentType.COUNTY == partner.getAgentType()) {
					countyIds.add(partner.getId());
				} else if (AgentType.PERSONAL == partner.getAgentType()) {
					personalIds.add(partner.getId());
				} else if (partner.getAgentType() == null) {
					merchantIds.add(partner.getId());
				}
			}

			// Map用于快速获取分润结果
			Map<String, Object> districtMap = new HashMap<>();
			if (command.isQueryCounty() && !countyIds.isEmpty()) {
				// 县代分润查询
				QueryDistrictCommand countyApiRequest = new QueryDistrictCommand();
				countyApiRequest.setOwnerId(command.getAgentId());
				countyApiRequest.setOwnerType(command.getAgentType().value().toString());
				countyApiRequest.setQueryData(countyIds);
				ResultDto<List<QueryDistrictResponse>> countyResultDto = myPartnerApiService
						.queryDistrict(countyApiRequest);
				for (QueryDistrictResponse response : countyResultDto.getData()) {
					districtMap.put(response.getAgentNo(), response);
				}
			}

			if (command.isQueryPersonal() && !personalIds.isEmpty()) {
				// 业务员分润查询
				QueryDistrictCommand personalApiRequest = new QueryDistrictCommand();
				personalApiRequest.setOwnerId(command.getAgentId());
				personalApiRequest.setOwnerType(command.getAgentType().value().toString());
				personalApiRequest.setQueryData(personalIds);
				ResultDto<List<QueryDistrictResponse>> personalResultDto = myPartnerApiService
						.findConSalesmanSum(personalApiRequest);
				for (QueryDistrictResponse response : personalResultDto.getData()) {
					districtMap.put(response.getAgentNo(), response);
				}
			}

			if (command.isQueryMerchant() && !merchantIds.isEmpty()) {
				// 商户伙伴分润查询
				QueryDistrictCommand merchantApiRequest = new QueryDistrictCommand();
				merchantApiRequest.setOwnerId(command.getAgentId());
				merchantApiRequest.setOwnerType(command.getAgentType().value().toString());
				merchantApiRequest.setQueryData(merchantIds);
				ResultDto<List<QueryMerchantResponse>> merchantResultDto = myPartnerApiService
						.queryMerchant(merchantApiRequest);
				for (QueryMerchantResponse response : merchantResultDto.getData()) {
					districtMap.put(response.getMerchantNo(), response);
				}
			}

			for (PartnerDto partner : partnerPage.getList()) {
				MyMerchantProfitList dto = new MyMerchantProfitList();
				dto.setCompanyName(partner.getName2());
				dto.setCooperationTime(partner.getCreationTime());
				dto.setName(partner.getName());
				dto.setMobile(partner.getMobile());
				dto.setId(partner.getId());
				dto.setAgentAreaid(partner.getAgentAreaid());
				dto.setAvatarUrl(PicHandler.addPrefixUrlStatic(partner.getAvatarUrl()));
				dto.setPartnerType(partner.getAgentType() == null ? PartnerType.MERCHANT
						: PartnerType.valueOf(partner.getAgentType().name()));
				if (AgentType.PERSONAL == partner.getAgentType()) {
					dto.setExCode(partner.getId());
				}
				dto.setIsArea(partner.getIsArea());
				dto.setLinkType(partner.getLinkType());
				dto.setExternalOperationName(partner.getExternalOperationName());
				if (districtMap.containsKey(partner.getId())) {
					if (partner.getAgentType() != null) {
						QueryDistrictResponse resp = (QueryDistrictResponse) districtMap.get(partner.getId());
						dto.setTotalProfitFee(resp.getTotalProfitFee());
						dto.setTxnCount(resp.getTxnCount());
					} else {
						QueryMerchantResponse resp = (QueryMerchantResponse) districtMap.get(partner.getId());
						dto.setTotalProfitFee(resp.getTotalProfitFee());
						dto.setTxnCount(resp.getTotalCount());
					}
				}
				myProfits.add(dto);
			}
		}
		MyPartnerAndProfitDto result = new MyPartnerAndProfitDto();
		result.setTotal(partnerPage.getTotal());
		result.setPageNum(partnerPage.getPageNum());
		result.setPageSize(partnerPage.getPageSize());
		result.setPages(partnerPage.getPages());
		result.setSize(partnerPage.getSize());
		result.setMyProfits(myProfits);
		return result;
	}

	// 查询我的收益相关
	private void findMyProfitDetail(Map<String, BigDecimal> result, BigDecimal profitAmount, String profitType,
			AgentType agentType, Boolean hasCounty) {
		result.put(MY_PROFIT, profitAmount);
		if (agentType == AgentType.CITY) {
			if (hasCounty) {
				if (profitType.equals(ProfitType.CITY_SERVICE_FEE.value())) {
					result.put(MY_SERVICE_FEE, profitAmount);
				}
			} else {
				if (profitType.equals(ProfitType.CITY_OR_COUNT_SERVICE_FEE.value())) {
					result.put(MY_SERVICE_FEE, profitAmount);
				}
			}
		} else if (agentType == AgentType.COUNTY) {
			if (hasCounty) {
				if (profitType.equals(ProfitType.CITY_OR_COUNT_SERVICE_FEE.value())) {
					result.put(MY_SERVICE_FEE, profitAmount);
				}
			}
		} else if (agentType == AgentType.PERSONAL) {
			if (profitType.equals(ProfitType.PERSONAL_SERVICE_FEE.value())) {
				result.put(MY_SERVICE_FEE, profitAmount);
			}
		}
		if (profitType.equals(ProfitType.AGENT_DEVELOP_FEE.value())) {
			result.put("myDevFee", profitAmount);
		}
	}

	// 查询县代收益相关
	private Map<String, BigDecimal> findCountyProfitDetail(BigDecimal profitAmount, String profitType,
			Boolean hasCounty) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		if (hasCounty) {
			result.put(COUNTY_PROFIT, profitAmount);
			if (profitType.equals(ProfitType.CITY_OR_COUNT_SERVICE_FEE.value())) {
				result.put(COUNTY_OF_SERVICE_FEE, profitAmount);
			}
			if (profitType.equals(ProfitType.AGENT_DEVELOP_FEE.value())) {
				result.put("countyOfDevFee", profitAmount);
			}
		}
		return result;
	}

	// 查询业务员收益相关
	private Map<String, BigDecimal> findPersonalProfitDetail(BigDecimal profitAmount, String profitType) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		result.put(PERSON_PROFIT, profitAmount);
		if (profitType.equals(ProfitType.AGENT_DEVELOP_FEE.value())) {
			result.put("personOfDevFee", profitAmount);
		}
		if (profitType.equals(ProfitType.PERSONAL_SERVICE_FEE.value())) {
			result.put("personOfDevFee", profitAmount);
		}
		return result;
	}

	// 查询我的总分润
	private Map<String, Object> getCoTotalProfit(Agent curAgent, String type) {
		QueryAssociationCommand request = new QueryAssociationCommand();
		request.setType(type);
		request.setOwnerId(curAgent.getId());
		request.setOwnerType(curAgent.getAgentType().value().toString());
		LOG.info("PartnerController.getCoTotalProfit invoking settlement project begin \n url=/conAgentSum/query param={}",JsonUtils.toJsonString(request));
		ResultDto<CooperationTotalProfitDto> coTotalProfit = myPartnerApiService.query(request);
        LOG.info("PartnerController.getCoTotalProfit invoking settlement project end \n url=/conAgentSum/query rlt={}",JsonUtils.toJsonString(coTotalProfit));
		Map<String, Object> result = new HashMap<String, Object>();
		if (coTotalProfit.getResultCode() != null && coTotalProfit.getResultCode().equals(successCode)
				&& coTotalProfit.getData() != null) {
			result.put("totalProfitFee", coTotalProfit.getData().getTotalProfitFee());
			result.put("totalCount", coTotalProfit.getData().getTotalCount());
		}
		return result;
	}

	// 查询商户日交易列表
	private MyMerchantTradeListDto getMerchantDateTradeList(Agent agent, Merchant merchant, int pageNo, int pageSize) {
		ToSPayProfitBase apiRequest = new ToSPayProfitBase();
		apiRequest.setMerchantNo(merchant.getGeileMerchantSn());
		apiRequest.setNumPerPage(pageSize);
		apiRequest.setPageNum(pageNo);
		apiRequest.setOwnerType(agent.getAgentType().value().toString());
		apiRequest.setOwnerId(agent.getId());
		ResultDto<List<QueryMerchantSumDetailResponse>> resultDto = myPartnerApiService.queryDirectMerchantAndServiceDetail(apiRequest);
		MyMerchantTradeListDto result = new MyMerchantTradeListDto();
		List<MyMerchantTradeList> myTradeList = new ArrayList<MyMerchantTradeList>();

		if (null == resultDto || null == resultDto.getResultCode() || !resultDto.getResultCode().equals(successCode)) {
			result.setData(myTradeList);
			return result;
		}
		for (QueryMerchantSumDetailResponse resp : resultDto.getData()) {
			MyMerchantTradeList trade = new MyMerchantTradeList();
			trade.setTradeTime(resp.getPayTime().toLocalDate());
			trade.setMyProfit(resp.getOwnerProfitAmount());
			trade.setTradeAmount(resp.getTxnAmount());
			trade.setPayId(resp.getPayId());
			myTradeList.add(trade);
		}
		result.setData(myTradeList);
		result.setTotal(resultDto.getTotalNum());
		result.setPageNum(pageNo);
		result.setPageSize(pageSize);
		result.setPages(getPages(resultDto.getTotalNum(), pageSize));
		return result;
	}
	
	// 查询商户交易列表
	private MyMerchantTradeListDto getMerchantTradeList(Agent loginAgent, Agent agent, Merchant merchant, int pageNo, int pageSize) {
		QueryMerchantSumDetailRequest apiRequest = new QueryMerchantSumDetailRequest();
		
		apiRequest.setOwnerId(loginAgent.getId());
		apiRequest.setOwnerType(loginAgent.getAgentType().value().toString());
		
		apiRequest.setMerchantNo(merchant.getGeileMerchantSn());
		apiRequest.setNumPerPage(pageSize);
		apiRequest.setPageNum(pageNo);
		
		apiRequest.setDistrictAgentNo(agent.getId());
		apiRequest.setDirectAgentNo(agent.getId());
		
		ResultDto<List<QueryMerchantSumDetailResponse>> resultDto = null;
		if(loginAgent.getAgentType() == AgentType.CITY &&
				agent.getAgentType() == AgentType.COUNTY){
			//市级视角，县代下商户交易明细列表2.20
			resultDto = myPartnerApiService.queryIndirectMerchantDetail(apiRequest);
		}else if(loginAgent.getAgentType() == AgentType.CITY||
				loginAgent.getAgentType() == AgentType.COUNTY){
			//市，县视角，一级业务员商户交易明细列表 2.22	查询积分联付提成明细（直接业务员贡献）
			resultDto = myPartnerApiService.queryDirectAgentNoDetail(apiRequest);
		}else{
			//业务员视角，一级业务员商户交易明细列表2.8查询商户交易提成列表
			apiRequest.setAgentNo(loginAgent.getId());
			apiRequest.setSettleFlag("Y");
			resultDto = myPartnerApiService.queryMerchantSumDetail(apiRequest);
		}
		
		MyMerchantTradeListDto result = new MyMerchantTradeListDto();
		List<MyMerchantTradeList> myTradeList = new ArrayList<MyMerchantTradeList>();

		if (null == resultDto || null == resultDto.getResultCode() || !resultDto.getResultCode().equals(successCode)) {
			result.setData(myTradeList);
			return result;
		}
		for (QueryMerchantSumDetailResponse resp : resultDto.getData()) {
			MyMerchantTradeList trade = new MyMerchantTradeList();
			trade.setTradeTime(resp.getPayTime().toLocalDate());
			trade.setMyProfit(resp.getOwnerProfitAmount());
			trade.setTradeAmount(resp.getTxnAmount());
			trade.setPayId(resp.getPayId());
			myTradeList.add(trade);
		}
		result.setData(myTradeList);
		result.setTotal(resultDto.getTotalNum());
		result.setPageNum(pageNo);
		result.setPageSize(pageSize);
		result.setPages(getPages(resultDto.getTotalNum(), pageSize));
		return result;
	}

	public Integer getPages(Long total, Integer pageSize) {
		if (pageSize == null || pageSize == 0) {
			return 0;
		}
		return (int) Math.ceil((total + (pageSize - 1)) / pageSize);
	}

	/**
	 * 转换时间当月最后一天
	 *
	 * dfs_518 <br/>
	 * 2017年6月14日 下午4:23:27 <br/>
	 * 
	 * @param date
	 *            时间
	 * @param parse
	 *            格式
	 * @return
	 * @throws ParseException
	 *
	 *             String
	 */
	public static String monthEnd(String date, String parse) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.parseDate(date, parse));
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 转换时间当月第一天
	 *
	 * dfs_518 <br/>
	 * 2017年6月14日 下午4:23:27 <br/>
	 * 
	 * @param date
	 *            时间
	 * @param parse
	 *            格式
	 * @return
	 * @throws ParseException
	 *
	 *             String
	 */
	public static String monthBegin(String date, String parse) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.parseDate(date, parse));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
	}
}
