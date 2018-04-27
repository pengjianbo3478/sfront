package com.gl365.app.web;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.Agent;
import com.gl365.app.dto.ApiPageResponse;
import com.gl365.app.dto.ApiRequest;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.BriefTxDto;
import com.gl365.app.dto.DevoteDto;
import com.gl365.app.dto.Merchant;
import com.gl365.app.dto.MyProfitSummary;
import com.gl365.app.dto.PageResult;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.TotalTxListDto;
import com.gl365.app.dto.TxDetailsDto;
import com.gl365.app.dto.YesterdayList;
import com.gl365.app.dto.command.TotalTxListCommand;
import com.gl365.app.dto.command.TxQueryCommand;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.settlequery.TxnAmountSumResp;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.remote.profit.dto.YesterdayListDto;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.MerchantService;
import com.gl365.app.remote.settlement.MyPartnerApiService;
import com.gl365.app.remote.settlement.MyPartnerQueryApiService;
import com.gl365.app.remote.settlement.ResultDto;
import com.gl365.app.remote.settlement.ApiRequest.AgentProfitSumQueryCommand;
import com.gl365.app.remote.settlement.ApiRequest.DailyDevoteQueryCommand;
import com.gl365.app.remote.settlement.ApiRequest.DailyProfitQueryCommand;
import com.gl365.app.remote.settlement.ApiRequest.ProfitingTransactionQueryCommand;
import com.gl365.app.remote.settlement.ApiRequest.QueryAssociationCommand;
import com.gl365.app.remote.settlement.ApiResponse.AgentProfitSumDto;
import com.gl365.app.remote.settlement.ApiResponse.CooperationTotalProfitDto;
import com.gl365.app.remote.settlement.ApiResponse.DailyDevoteResp;
import com.gl365.app.remote.settlement.ApiResponse.DailyProfitDto;
import com.gl365.app.remote.settlement.ApiResponse.FindPayResponse;
import com.gl365.app.remote.settlement.ApiResponse.ProfitingTransactionDetailsDto;
import com.gl365.app.remote.settlement.ApiResponse.QueryMerchantSumDetailResponse;
import com.gl365.app.utils.BigDecimaluitl;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;
import com.google.common.collect.ImmutableList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Chen, Zhuhui
 */
@Api(tags = "PROFIT", description = "收益和交易相关")
@RequestMapping("/api/myProfit")
@RestController
public class MyProfitController extends BaseController {
	private static final String BIG_DECIMAL_0 = "0.00";
	private static final Logger LOG = LoggerFactory.getLogger(MyProfitController.class);
	private static final String SUCCESS_CODE = "000000";
	private static final String COUNTY = "1";
	private static final String MERCHANT = "2";
	private static final String PERSONAL = "3";
	private static final String USER = "4";
	private static final String OTHER = "5";
	ProfitingTransactionQueryCommand command = new ProfitingTransactionQueryCommand();
	@Autowired
	private MyPartnerApiService myPartnerApiService;
	@Autowired
	private MyPartnerQueryApiService myPartnerQueryApiService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private MerchantService merchantService;

	@ApiOperation(value = "查询今日益及累计收益")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/myNowProfitSummary", method = RequestMethod.GET)
	public ApiResponse<MyProfitSummary> getNowSummary() throws Exception {
		String currentUserId = getCurrentLoginUserId();
		Agent currentUser = agentService.findById(currentUserId);

		MyProfitSummary profitSummary = new MyProfitSummary();
		profitSummary.setYesterday(BigDecimal.ZERO);
		profitSummary.setTotal(BigDecimal.ZERO);

		// 昨日收益
		// 计算昨日时间
		DailyProfitQueryCommand c1 = new DailyProfitQueryCommand();
		c1.setOwnerId(currentUserId);
		c1.setOwnerType(currentUser.getAgentType());
		ResultDto<BigDecimal> rlt = myPartnerQueryApiService.queryProfitAmount(c1);
		if ( ReturnCode.System.SUCCESS.getCode().equals(rlt.getResultCode())) {
			profitSummary.setNowday(rlt.getData());
		}

		// 累计收益
		AgentProfitSumQueryCommand c2 = new AgentProfitSumQueryCommand();
		c2.setQueryData(ImmutableList.of(currentUserId));
		c2.setOwnerType(currentUser.getAgentType());
		List<AgentProfitSumDto> profitSum = myPartnerApiService.queryAgentProfitSum(c2).getData();
		if (profitSum != null && profitSum.size() > 0) {
			profitSummary.setTotal(profitSum.get(0).getTotalProfitFee());
		}
		return ApiResponse.createTypedSuccess(profitSummary);
	}
	
	@ApiOperation(value = "查询昨日收益及累计收益")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/myProfitSummary", method = RequestMethod.GET)
	public ApiResponse<MyProfitSummary> getSummary() throws Exception {
		String currentUserId = getCurrentLoginUserId();
		Agent currentUser = agentService.findById(currentUserId);

		MyProfitSummary profitSummary = new MyProfitSummary();
		profitSummary.setYesterday(BigDecimal.ZERO);
		profitSummary.setTotal(BigDecimal.ZERO);

		// 昨日收益
		// 计算昨日时间
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		DailyProfitQueryCommand c1 = new DailyProfitQueryCommand();
		c1.setFromDate(yesterday);
		c1.setToDate(yesterday);
		c1.setNumPerPage(1);
		c1.setPageNum(1);
		c1.setOwnerId(currentUserId);
		c1.setOwnerType(currentUser.getAgentType());
		LOG.info("MyProfitController.queryDailyProfit invoking settlement(2.18 查询机构提成日报) project begin \n url=/profitAgentDaily/query param={}",JsonUtils.toJsonString(c1));
		ResultDto<List<DailyProfitDto>> rlt = myPartnerApiService.queryDailyProfit(c1);
		LOG.info("MyProfitController.queryDailyProfit invoking settlement(2.18 查询机构提成日报) project end \n rlt={}",JsonUtils.toJsonString(rlt));
		List<DailyProfitDto> dailyProfit = rlt.getData();
		if (dailyProfit != null && dailyProfit.size() > 0) {
			profitSummary.setYesterday(dailyProfit.get(0).getTotalProfitFee());
		}

		// 累计收益
		AgentProfitSumQueryCommand c2 = new AgentProfitSumQueryCommand();
		c2.setQueryData(ImmutableList.of(currentUserId));
		c2.setOwnerType(currentUser.getAgentType());
		List<AgentProfitSumDto> profitSum = myPartnerApiService.queryAgentProfitSum(c2).getData();
		if (profitSum != null && profitSum.size() > 0) {
			profitSummary.setTotal(profitSum.get(0).getTotalProfitFee());
		}
		return ApiResponse.createTypedSuccess(profitSummary);
	}

	@ApiOperation(value = "查询交易列表")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/txList", method = RequestMethod.POST)
	public Object listTransactions(@RequestBody ApiRequest<TxQueryCommand> request) throws Exception {
		String currentUserId = getCurrentLoginUserId();
		Agent currentUser = agentService.findById(currentUserId);

		command.setFromDate(request.getData().getStartTime());
		command.setToDate(request.getData().getEndTime());
		command.setOwnerId(currentUserId);
		command.setOwnerType(currentUser.getAgentType());
		command.setMerchantName(request.getData().getKeyword());
		command.setNumPerPage(request.getPageSize());
		command.setPageNum(request.getPageNo());

		ResultDto<List<ProfitingTransactionDetailsDto>> apiResult = myPartnerQueryApiService.queryProfitingTransactionDetails(command);
		List<ProfitingTransactionDetailsDto> result = apiResult.getData();
		List<BriefTxDto> ret;
		if (result != null) {
			ret = result.stream().map(r -> {
				BriefTxDto dto = new BriefTxDto();
				dto.setMerchantName(r.getMerchantName());
				dto.setMerchantId(r.getMerchantNo());
				dto.setTid(r.getPayId());
				dto.setAmount(r.getTxnAmount());
				dto.setProfit(r.getOwnerProfitAmount());
				// 清算系统查询时候是根据清算时间查询,所以此处取清算时间,而非交易时间  modify 20170717 xty
				//dto.setTransactionTime(r.getPayTime());
				dto.setSettleTime(r.getSettleDate());
				dto.setSettleFlag(r.getSettleFlag());
				return dto;
			}).collect(Collectors.toList());
		} else {
			ret = new ArrayList<>();
		}
		PageResult<BriefTxDto> pagedRet = new PageResult<>();
		pagedRet.setData(ret);
		pagedRet.setPageSize(request.getPageSize());
		pagedRet.setPageNo(request.getPageNo());
		pagedRet.setTotalItems(apiResult.getTotalNum());
		pagedRet.setTotalPages(getPages(pagedRet.getTotalItems(),pagedRet.getPageSize()));
		return ApiPageResponse.createPagedNoErrorResponse(pagedRet, null);

	}

	@ApiOperation(value = "查询交易明细")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/tx/{tid}", method = RequestMethod.GET)
	public ApiResponse<TxDetailsDto> listTransactionDetails(
			@ApiParam("交易ID")
			@PathVariable("tid") String tid) throws Exception {
		TxDetailsDto ret = new TxDetailsDto();
		FindPayResponse info1 = myPartnerApiService.findByPayId(tid);
		List<QueryMerchantSumDetailResponse> info2 = myPartnerApiService.findByPayDetailId(tid).getData();
		if (info1 != null && info2 != null) {
			ret.setMerchantName(info1.getMerchantName());
			ret.setMerchantSn(info1.getMerchantNo());
			ret.setPosDeviceId(info1.getTerminal());
			ret.setBankCardNo(info1.getCardNo());
			// TODO: 接口缺少返回银行名称字段
			ret.setBankName("-");
			ret.setTransactionTime(info1.getPayTime());
			ret.setAmount(info1.getTotalAmount());

			// TODO: 分润信息如何归入9个字段进行显示暂不清晰

			Merchant merchant = merchantService.findByGeileMerchantSn(info1.getMerchantNo());
			Agent agent = agentService.findWithRefById(merchant.getAgent().getId());
			if (agent.getAgentType() == AgentType.PERSONAL) {
				agent = agent.getUpstreamNonPersonalAgent();
			}
			ret.setMerchantName(agent.getCompanyName() == null ? agent.getName() : agent.getCompanyName());
		}

		return ApiResponse.createTypedSuccess(ret);
	}

	@ApiOperation(value = "查看累计的贡献-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/getTotalDevote", method = RequestMethod.POST)
	public ApiResponse<DevoteDto> getTotalDevote() {
		AgentLoginDto agentLoginDto = getCurrentUser();
		DevoteDto result = new DevoteDto();
		QueryAssociationCommand query = new QueryAssociationCommand();
		query.setOwnerId(agentLoginDto.getId());
		switch (agentLoginDto.getAgentType()) {
			case CITY:
				query.setOwnerType("2");
				//县级
				query.setType(COUNTY);
				ResultDto<CooperationTotalProfitDto> coTotalProfit = myPartnerApiService.query(query);
				if (coTotalProfit.getResultCode() != null && coTotalProfit.getResultCode().equals(SUCCESS_CODE)) {
					result.setCountyDevote(coTotalProfit.getData() == null?BIG_DECIMAL_0:BigDecimaluitl.setScaleStr(coTotalProfit.getData().getTotalProfitFee()));
				}
				//用户
				query.setType(USER);
				ResultDto<CooperationTotalProfitDto> coTotalProfit2 = myPartnerApiService.query(query);
				if (coTotalProfit2.getResultCode() != null && coTotalProfit2.getResultCode().equals(SUCCESS_CODE)) {
					result.setUserDevote(coTotalProfit2.getData() == null?BIG_DECIMAL_0:BigDecimaluitl.setScaleStr(coTotalProfit2.getData().getTotalProfitFee()));
				}
				
				
				query.setType(OTHER);
				ResultDto<CooperationTotalProfitDto> coTotalProfitOther = myPartnerApiService.query(query);
				if (coTotalProfitOther.getResultCode() != null && coTotalProfitOther.getResultCode().equals(SUCCESS_CODE) 
						&& coTotalProfitOther.getData() != null) {
					result.setOtherDevote(BigDecimaluitl.setScaleStr(coTotalProfitOther.getData().getTotalProfitFee()));
				}
				
				break;
			case COUNTY:
				query.setOwnerType("3");
				//用户
				query.setType(USER);
				ResultDto<CooperationTotalProfitDto> coTotalProfit3 = myPartnerApiService.query(query);
				if (coTotalProfit3.getResultCode() != null && coTotalProfit3.getResultCode().equals(SUCCESS_CODE)) {
					result.setUserDevote(coTotalProfit3.getData() == null?BIG_DECIMAL_0:BigDecimaluitl.setScaleStr(coTotalProfit3.getData().getTotalProfitFee()));
				}
				
				query.setType(OTHER);
				ResultDto<CooperationTotalProfitDto> countyOther = myPartnerApiService.query(query);
				if (countyOther.getResultCode() != null && countyOther.getResultCode().equals(SUCCESS_CODE) 
						&& countyOther.getData() != null) {
					result.setOtherDevote(BigDecimaluitl.setScaleStr(countyOther.getData().getTotalProfitFee()));
				}
				break;
			case PERSONAL:
				query.setOwnerType("4");
				break;
			case ASSOCIATION:
				query.setOwnerType("7");
				break;
		}
		//业务员
		query.setType(PERSONAL);
		ResultDto<CooperationTotalProfitDto> coTotalProfit1 = myPartnerApiService.query(query);
		if (coTotalProfit1.getResultCode() != null && coTotalProfit1.getResultCode().equals(SUCCESS_CODE)) {
			result.setPersonalDevote(coTotalProfit1.getData() == null?BIG_DECIMAL_0:BigDecimaluitl.setScaleStr(coTotalProfit1.getData().getTotalProfitFee()));
		}
		
		//商户
		query.setType(MERCHANT);
		ResultDto<CooperationTotalProfitDto> coTotalProfit2 = myPartnerApiService.query(query);
		if (coTotalProfit2.getResultCode() != null && coTotalProfit2.getResultCode().equals(SUCCESS_CODE) ) {
			result.setMerchantDevote(coTotalProfit2.getData() == null? BIG_DECIMAL_0:BigDecimaluitl.setScaleStr(coTotalProfit2.getData().getTotalProfitFee()));
		}

		return ApiResponse.createSuccess(result);
	}

	@ApiOperation(value = "查看昨日的贡献-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/getYesterdayDevote", method = RequestMethod.POST)
	public ApiResponse<DevoteDto> getYesterdayDevote() {
		AgentLoginDto agentLoginDto = getCurrentUser();
		DevoteDto devoteDto = new DevoteDto();
		DailyDevoteQueryCommand command = new DailyDevoteQueryCommand();
		command.setOwnerId(agentLoginDto.getId());
		command.setOwnerType(agentLoginDto.getAgentType());
		LocalDate startTime = LocalDate.now().minusDays(1);
		LocalDate endTime = LocalDate.now();
		command.setFromDate(startTime);
		command.setToDate(endTime);
		command.setToDate(endTime);
		ResultDto<List<DailyDevoteResp>> result = myPartnerApiService.queryConAgentSum(command);
		if (result.getResultCode() != null && result.getResultCode().equals(SUCCESS_CODE) && !result.getData().isEmpty()) {
			List<DailyDevoteResp> list = result.getData();
			list.forEach((DailyDevoteResp row) -> {
				switch (row.getType()) {
					case COUNTY:
						devoteDto.setCountyDevote(BigDecimaluitl.setScaleStr(row.getTotalProfitFee()));
						break;
					case MERCHANT:
						devoteDto.setMerchantDevote(BigDecimaluitl.setScaleStr(row.getTotalProfitFee()));
						break;
					case PERSONAL:
						devoteDto.setPersonalDevote(BigDecimaluitl.setScaleStr(row.getTotalProfitFee()));
						break;
					case USER:
						devoteDto.setUserDevote(BigDecimaluitl.setScaleStr(row.getTotalProfitFee()));
						break;
					case OTHER:
						devoteDto.setOtherDevote(BigDecimaluitl.setScaleStr(row.getTotalProfitFee()));
						break;
				}
			});
		}
		
		if(agentLoginDto.getAgentType() == AgentType.CITY){
			if(StringUtils.isBlank(devoteDto.getCountyDevote())){
				devoteDto.setCountyDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getMerchantDevote())){
				devoteDto.setMerchantDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getPersonalDevote())){
				devoteDto.setPersonalDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getUserDevote())){
				devoteDto.setUserDevote(BigDecimaluitl.setScaleStr(null));
			}
		}else if(agentLoginDto.getAgentType() == AgentType.COUNTY){
			if(StringUtils.isBlank(devoteDto.getMerchantDevote())){
				devoteDto.setMerchantDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getPersonalDevote())){
				devoteDto.setPersonalDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getUserDevote())){
				devoteDto.setUserDevote(BigDecimaluitl.setScaleStr(null));
			}
		}else {
			if(StringUtils.isBlank(devoteDto.getMerchantDevote())){
				devoteDto.setMerchantDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isBlank(devoteDto.getPersonalDevote())){
				devoteDto.setPersonalDevote(BigDecimaluitl.setScaleStr(null));
			}
			
			if(StringUtils.isNotBlank(devoteDto.getOtherDevote())){
				devoteDto.setOtherDevote(null);
			}
		}
		return ApiResponse.createSuccess(devoteDto);
	}

	@ApiOperation(value = "查看昨日收益列表")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/yesterdayList", method = RequestMethod.POST)
	public ApiResponse<YesterdayListDto> yesterdayList(@RequestBody ApiRequest<Void> request) {

		AgentLoginDto agentDto = getCurrentUser();
		LocalDate toDate = LocalDate.now().minusDays(1);
		LocalDate fromDate = LocalDate.now().minusDays(1);
		
		if(request.getPageSize() == null ){
			request.setPageSize(20);
		}
		
		if(request.getPageNo() == null || request.getPageNo() < 1){
			request.setPageNo(1);
		}

		ProfitingTransactionQueryCommand command = new ProfitingTransactionQueryCommand();
		command.setOwnerId(agentDto.getId());
		command.setOwnerType(agentDto.getAgentType());
		command.setNumPerPage(request.getPageSize());
		command.setPageNum(request.getPageNo());
		command.setFromDate(fromDate);
		command.setToDate(toDate);

		ResultDto<List<ProfitingTransactionDetailsDto>> apiResult = myPartnerQueryApiService.queryProfitingTransactionDetails(command);
		LOG.info("查询账户明细/payProfit/queryPayProfitDetail,param={},apiResult={}",JsonUtils.toJsonString(command),JsonUtils.toJsonString(apiResult));
		if ("000000".equals(apiResult.getResultCode())) {
			List<ProfitingTransactionDetailsDto> result = apiResult.getData();
			List<YesterdayListDto> ret;
			if (result != null) {
				ret = result.stream().map(r -> {
					YesterdayListDto dto = new YesterdayListDto(r.getMerchantName(), r.getTxnAmount(), r.getOwnerProfitAmount(), r.getCreateTime(), r.getSettleDate(),r.getSettleFlag());
					return dto;
				}).collect(Collectors.toList());
			} else {
				ret = new ArrayList<>();
			}
			
			YesterdayList yesterdayListDto=new YesterdayList();
			yesterdayListDto.setPageSize(request.getPageSize());
			yesterdayListDto.setPageNo(request.getPageNo());
			yesterdayListDto.setTotalItems(apiResult.getTotalNum());
			yesterdayListDto.setTotalPages(getPages(yesterdayListDto.getTotalItems(),yesterdayListDto.getPageSize()));
			
			yesterdayListDto.setList(ret);
			LOG.info("查看昨日收益列表,rlt={}",JsonUtils.toJsonString(yesterdayListDto));
			return ApiResponse.createSuccess(yesterdayListDto);
		} else {
			return ApiResponse.createResponseWithMessage(apiResult.getResultCode(), apiResult.getResultDesc());
		}
	}

	@ApiOperation(value = "查看累计交易列表-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/totalTxList", method = RequestMethod.POST)
	public ApiResponse<TotalTxListDto> totalTxList(@RequestBody ApiRequest<TotalTxListCommand> request) {
		AgentLoginDto agentLoginDto = getCurrentUser();
		TotalTxListCommand totalTxListCommand = request.getData();
		TotalTxListDto totalTxListDto = new TotalTxListDto();
		LocalDate fromDate;
		LocalDate toDate;
		if (totalTxListCommand.getCurrentMonth() == null) {
			fromDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		} else {
			//是否上一个月
			if (totalTxListCommand.isPreviousMonth()) {
				//减去当前月份
				fromDate = totalTxListCommand.getCurrentMonth().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1);
			} else {
				fromDate = totalTxListCommand.getCurrentMonth().with(TemporalAdjusters.firstDayOfMonth());
			}
		}
		toDate = fromDate.with(TemporalAdjusters.lastDayOfMonth());
		//开始时间大于用户createTime的话，
		//逻辑反了  if (fromDate.isAfter(agentLoginDto.getCreationTime().toLocalDate())) {
		if (agentLoginDto.getCreationTime() != null && fromDate
				.isBefore(agentLoginDto.getCreationTime().toLocalDate().with(TemporalAdjusters.firstDayOfMonth()))) {
			LOG.info("最后月份 fromDate={}", fromDate.toString());
			totalTxListDto.setList(null == totalTxListDto.getList() ? new ArrayList<>() : totalTxListDto.getList());
			totalTxListDto.setEnd(true);
			return ApiResponse.createSuccess(totalTxListDto);
		}
		command.setFromDate(fromDate);
		command.setToDate(toDate);
		command.setOwnerId(agentLoginDto.getId());
		command.setOwnerType(agentLoginDto.getAgentType());
		if(null == request.getPageSize()){
			command.setNumPerPage(20);
		}else{
			command.setNumPerPage(request.getPageSize());
		}
		command.setPageNum(request.getPageNo());

		ResultDto<List<ProfitingTransactionDetailsDto>> apiResult = myPartnerQueryApiService.queryProfitingTransactionDetails(command);
		LOG.info("查询账户明细 payProfit/queryPayProfitDetail param={},rlt={}",JsonUtils.toJsonString(command),JsonUtils.toJsonString(apiResult));
		List<ProfitingTransactionDetailsDto> result = apiResult.getData();
		List<BriefTxDto> ret;
		if (result != null) {
			ret = result.stream().map(r -> {
				BriefTxDto dto = new BriefTxDto();
				dto.setMerchantName(r.getMerchantName());
				dto.setMerchantId(r.getMerchantNo());
				dto.setTid(r.getPayId());
				dto.setAmount(r.getTxnAmount());
				dto.setProfit(r.getOwnerProfitAmount());
				dto.setTransactionTime(r.getPayTime());
				dto.setSettleFlag(r.getSettleFlag());
				return dto;
			}).collect(Collectors.toList());
		} else {
			ret = new ArrayList<>();
		}
		totalTxListDto.setCurrentMonth(fromDate);
		totalTxListDto.setPageSize(request.getPageSize());
		totalTxListDto.setPageNo(request.getPageNo());
		totalTxListDto.setTotalItems(apiResult.getTotalNum());
		totalTxListDto.setList(ret);
		totalTxListDto.setTotalPages(getPages(totalTxListDto.getTotalItems(),totalTxListDto.getPageSize()));
		LOG.info("rlt={}",JsonUtils.toJsonString(totalTxListDto));
		return ApiResponse.createSuccess(totalTxListDto);
	}
	
	@Permission(permission = PermissionConstant.LOGIN)
	@PostMapping("/getTxnAmountSum")
	@ApiOperation(value = "查询账户明细汇总", httpMethod = "POST", response = TxnAmountSumResp.class)
	public Object getTxnAmountSum(@RequestBody ApiRequest<TxQueryCommand> request) throws Exception {
		logger.info("getTxnAmountSum begin  ===> ,req:{}", JsonUtils.toJsonString(request));
		command.setFromDate(request.getData().getStartTime());
		command.setToDate(request.getData().getEndTime());
		command.setOwnerId(getCurrentUser().getId());
		command.setOwnerType(getCurrentUser().getAgentType());
		command.setMerchantName(request.getData().getKeyword());
		command.setNumPerPage(request.getPageSize());
		command.setPageNum(request.getPageNo());

		ResultDto<TxnAmountSumResp> rlt = null;
		logger.info("getTxnAmountSum begin  ===> ,req:{}", JsonUtils.toJsonString(command));
		rlt = myPartnerQueryApiService.getTxnAmountSum(command);
		logger.info("getTxnAmountSum ===> settlequeryService.getTxnAmountSum rlt:{}", JsonUtils.toJsonString(rlt));

		if (!ReturnCode.System.SUCCESS.getCode().equals(rlt.getResultCode())) {
			return ApiResponse.createErrorResponse(rlt.getResultDesc());
		}
		
		if(rlt.getData() == null){
			return ApiResponse.createSuccess(new TxnAmountSumResp());
		}
		
		return ApiResponse.createSuccess(rlt.getData());
	}
	
	public Integer getPages(Long total, Integer pageSize){
		if(pageSize==null || pageSize == 0){
			return 0;
		}
		return (int) Math.ceil((total+(pageSize-1)) / pageSize);
	}
}