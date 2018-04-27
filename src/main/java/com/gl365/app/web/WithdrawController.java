package com.gl365.app.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

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

import com.gl365.app.dto.ApiRequest;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.remote.account.AccountService;
import com.gl365.app.remote.account.command.QueryBalanceCommand;
import com.gl365.app.remote.account.dto.QueryBalanceDto;
import com.gl365.app.remote.profit.ProfitConfigService;
import com.gl365.app.remote.profit.dto.OneProfitConfigDto;
import com.gl365.app.remote.settlement.BankAccountInfoService;
import com.gl365.app.remote.settlement.MyPartnerQueryApiService;
import com.gl365.app.remote.settlement.ResultDto;
import com.gl365.app.remote.settlement.ApiResponse.BankAccountDto;
import com.gl365.app.remote.settlement.ApiResponse.SingleBankDto;
import com.gl365.app.remote.withdraw.WithDrawHandler;
import com.gl365.app.remote.withdraw.WithDrawService;
import com.gl365.app.remote.withdraw.command.CashCommand;
import com.gl365.app.remote.withdraw.command.QueryVoucherListCommand;
import com.gl365.app.remote.withdraw.dto.BalanceAndTotalDto;
import com.gl365.app.remote.withdraw.dto.BaseDto;
import com.gl365.app.remote.withdraw.dto.DeductionDto;
import com.gl365.app.remote.withdraw.dto.DetailDto;
import com.gl365.app.remote.withdraw.dto.QueryTotalRequestCashDto;
import com.gl365.app.remote.withdraw.dto.VoucherDto;
import com.gl365.app.remote.withdraw.dto.VoucherListDto;
import com.gl365.app.utils.BigDecimaluitl;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
@Api(description = "提现相关", tags = "WITHDRAW")
@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController extends BaseController {

    @Autowired
    private WithDrawService withDrawService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfitConfigService profitConfigService;

    @Autowired
    private WithDrawHandler withDrawHandler;
    
    @Autowired
	private BankAccountInfoService bankAccountInfoService;
    
    @Autowired
    private MyPartnerQueryApiService myPartnerQueryApiService;

    private static final Logger LOG = LoggerFactory.getLogger(WithdrawController.class);

    @ApiOperation("申请提现")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/withdrawApplication", method = RequestMethod.POST)
    public ApiResponse<Void> withdrawApplication(@RequestBody CashCommand command) {

        AgentLoginDto agent = getCurrentUser();
        command.setOwnerId(agent.getId());
        command.setOwnerType(agent.getAgentType().value() + "");
		LOG.info("WithdrawController.withdrawApplication invoking settlement project begin \n url=/bankAccountInfo/querySingle param={}",JsonUtils.toJsonString(command));
		SingleBankDto rlt = bankAccountInfoService.querySingle(command);
		LOG.info("WithdrawController.withdrawApplication invoking settlement project end \n rlt={}",JsonUtils.toJsonString(rlt));
		if (null == rlt || !ReturnCode.System.SUCCESS.getCode().equals(rlt.getResultCode()) || rlt.getData() == null) {
			LOG.info("提现申请失败：没有可用的银行卡");
			return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_DATA_EXECEPTION.getCode(), "银行卡无效");
		}
		BankAccountDto bankInfo = rlt.getData();
		command.setBankType(bankInfo.getFftBankType());
		command.setBankAccountNo(bankInfo.getBankAccountNo());
		command.setBankAccountName(bankInfo.getBankAccountName());
		command.setBankName(bankInfo.getBankName());
		command.setBankNo(bankInfo.getBankNo());
		command.setBankAccountType(bankInfo.getBankAccountType());
		command.setCertNo(bankInfo.getCertNo());
		command.setMobile(bankInfo.getMobile());
		BaseDto dto = withDrawService.withdrawApplication(command);
        if ("000000".equals(dto.getResultCode())) {
            return ApiResponse.createSuccess("");
        } else {
            return ApiResponse.createResponseWithMessage(dto.getResultCode(), dto.getResultDesc());
        }

    }


    @ApiOperation("查询用户余额和累计提现金额")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/sum", method = RequestMethod.POST)
    public ApiResponse<BalanceAndTotalDto> sum() {

        BalanceAndTotalDto dto = new BalanceAndTotalDto();
        AgentLoginDto agent = getCurrentUser();

        BaseCommand command = new BaseCommand();
        command.setOwnerId(agent.getId());
        command.setOwnerType(agent.getAgentType().value() + "");
        QueryTotalRequestCashDto totalRequestCashDto = withDrawService.queryTotalVcCashRequest(command);
        if ("000000".equals(totalRequestCashDto.getResultCode())) {
            if (totalRequestCashDto.getData().length > 0) {
                dto.setTotalRequestCash(BigDecimaluitl.setScaleStr(totalRequestCashDto.getData()[0]));
            } else {
                dto.setTotalRequestCash(BigDecimaluitl.setScaleStr(null));
            }
        } else {
            return ApiResponse.createResponseWithMessage(totalRequestCashDto.getResultCode(), totalRequestCashDto.getResultDesc());
        }

        QueryBalanceCommand queryBalanceCommand = new QueryBalanceCommand();
        queryBalanceCommand.setAccountId(agent.getId());
        queryBalanceCommand.setAccountType(agent.getAgentType().value() + "");
        QueryBalanceDto balanceDto = accountService.queryBalance(queryBalanceCommand);
        if ("000000".equals(balanceDto.getResultCode())) {
        	BigDecimal balance = balanceDto.getResultData() == null ? new BigDecimal(0) : balanceDto.getResultData().getBalance();
            dto.setBalance(BigDecimaluitl.setScaleStr(balance));
            return ApiResponse.createSuccess(dto);
        } else {
            return ApiResponse.createResponseWithMessage(balanceDto.getResultCode(), balanceDto.getResultDesc());
        }
    }

    @ApiOperation("查询手续费和税费的利率")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/queryCashPercent", method = RequestMethod.POST)
    public ApiResponse<DeductionDto> queryCashPercent() {

    	//TAX1 市代分润款税费费率%
        //TAX2 县代分润款税费费率%
    	AgentLoginDto loginDto =  getCurrentUser();
    	String code= null;
    	if(loginDto.getAgentType() == AgentType.CITY){
    		code ="TAX1";
    	} else if(loginDto.getAgentType() == AgentType.COUNTY){
    		code ="TAX2";
    	} 
    	
    	DeductionDto dto = new DeductionDto();
    	if(StringUtils.isNotBlank(code)){
    		OneProfitConfigDto taxDto = profitConfigService.findByCode(code);
    		if (!"000000".equals(taxDto.getResultCode())) {
    			return ApiResponse.createResponseWithMessage(taxDto.getResultCode(), taxDto.getResultDesc());
    		}
    		dto.setTaxPercent(taxDto.getData().getValue());
    	}

        //FEE1 提现手续费（对公，元） 
        //FEE2 提现手续费（对私，元）
    	/*AgentLoginDto agent = getCurrentUser();
    	BaseCommand command = new BaseCommand();
        command.setOwnerId(agent.getId());
        command.setOwnerType(agent.getAgentType().value() + "");
		LOG.info("WithdrawController.withdrawApplication invoking settlement project begin \n url=/bankAccountInfo/querySingle param={}",JsonUtils.toJsonString(command));
		SingleBankDto rlt = bankAccountInfoService.querySingle(command);
		LOG.info("WithdrawController.withdrawApplication invoking settlement project end \n rlt={}",JsonUtils.toJsonString(rlt));
		if (null == rlt || !ReturnCode.System.SUCCESS.getCode().equals(rlt.getResultCode()) || rlt.getData() == null) {
			LOG.info("提现申请失败：没有可用的银行卡");
			return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_DATA_EXECEPTION.getCode(), "银行卡无效");
		}
		BankAccountDto bankInfo = rlt.getData();*/
		code = "FEE2";
		/*if("01".equals(bankInfo.getBankAccountType())){
			code = "FEE1";
		}*/
        OneProfitConfigDto feeDto = profitConfigService.findByCode("FEE2");
        if (!"000000".equals(feeDto.getResultCode())) {
            return ApiResponse.createResponseWithMessage(feeDto.getResultCode(), feeDto.getResultDesc());
        }

        dto.setFeePercent(BigDecimaluitl.setScaleStr(feeDto.getData().getValue()));
        

        return ApiResponse.createSuccess(dto);
    }

    @ApiOperation("我的提现申请列表")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiResponse<VoucherDto> list(@RequestBody ApiRequest<Void> request) {

        AgentLoginDto agent = getCurrentUser();
        QueryVoucherListCommand command = new QueryVoucherListCommand();
        command.setNumPerPage(request.getPageSize());
        command.setPageNum(request.getPageNo());
        command.setOwnerId(agent.getId());
        command.setOwnerType(agent.getAgentType().value() + "");
        command.setQueryType("1");
        VoucherListDto dto = withDrawService.list(command);

        if ("000000".equals(dto.getResultCode())) {
            return ApiResponse.createSuccess(dto.getData());
        } else {
            return ApiResponse.createResponseWithMessage(dto.getResultCode(), dto.getResultDesc());
        }
    }


	@ApiOperation("提现申请详情")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/detail/{voucherId}", method = RequestMethod.GET)
	public ApiResponse<DetailDto> detail(@PathVariable("voucherId") String voucherId) {
		return withDrawHandler.detail(voucherId);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation(value = "累积分润查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = BalanceAndTotalDto.class)
	@PostMapping("/query/totalProfitFee")
	public Object querytotalProfitFee(HttpServletRequest request) {
		logger.info(String.format("累积分润查询>>>入参：operatorId {%s}", getCurrentLoginUserId()));

		AgentLoginDto agent = getCurrentUser();
		BaseCommand command = new BaseCommand();
		command.setOwnerType(agent.getAgentType().value() + "");
		command.setOwnerId(agent.getId());

		ResultDto<BalanceAndTotalDto> dto = myPartnerQueryApiService.querytotalProfitFee(command);
		if ("000000".equals(dto.getResultCode())) {
			return ApiResponse.createSuccess(dto.getData());
		} else {
			return ApiResponse.createResponseWithMessage(dto.getResultCode(), dto.getResultDesc());
		}
	}


}
