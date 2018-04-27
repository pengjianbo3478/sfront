package com.gl365.app.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.dto.wxpay.OpenAccountReq;
import com.gl365.app.dto.wxpay.WalletUrlReq;
import com.gl365.app.dto.wxpay.WalletUrlRsp;
import com.gl365.app.remote.account.AccountService;
import com.gl365.app.remote.settlement.ResultDto;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "钱包相关接口")
@RestController
@RequestMapping("/api/wxpay/act")
public class OpenActWxPayController extends BaseController {
	private static final String _009001 = "009001";

	private static final String _1 = "1";

	private static final String _999999 = "999999";

	private static final Logger logger = LoggerFactory.getLogger(OpenActWxPayController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private ValidatorFacadeService validatorFacadeService;

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation(value = "开户", httpMethod = "POST", notes = "传递复杂对象信息，json格式传递数据", response = WalletUrlRsp.class)
	@PostMapping("/open")
	public Object openAct(HttpServletRequest request, @RequestBody @Validated OpenAccountReq openAccountReq) {
		logger.info("==========openAct====begin===>" + openAccountReq);
		openAccountReq.setPono(UUID.randomUUID().toString().replaceAll("-", ""));
		AgentLoginDto agentLoginDto = getCurrentUser();
		openAccountReq.setPuserNo(agentLoginDto.getId());
		openAccountReq.setPuserType("" + agentLoginDto.getAgentType().value());

		if (_1.equals(openAccountReq.getProperty())) {
			ReturnCode.System ret = checking(openAccountReq.getCorpName(), openAccountReq.getBusLicNo(),
					openAccountReq.getTaxRegCerNo(), openAccountReq.getLegalPerName(), openAccountReq.getLegalPerNo(),
					openAccountReq.getCorpBankAcc(), openAccountReq.getCorpBankName(), openAccountReq.getBusContacts(),
					openAccountReq.getBusContactsPerNo(), openAccountReq.getBusContactsMobile());
			if (ret != null) {
				return ApiResponse.createResponseWithMessage(ret);
			}

			// 1:进行实名认证
			if (!validatorFacadeService.validIdCard(openAccountReq.getLegalPerNo(), openAccountReq.getLegalPerName())) {
				logger.error("企业法人实名认证不通过, idCardNo:" + openAccountReq.getLegalPerNo() + "name: "
						+ openAccountReq.getLegalPerName());
				// 实名认证不通过
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR);
			}

			if (!validatorFacadeService.validIdCard(openAccountReq.getBusContactsPerNo(),
					openAccountReq.getBusContacts())) {
				logger.error("企业联系人实名认证不通过, idCardNo:" + openAccountReq.getBusContactsPerNo() + "name: "
						+ openAccountReq.getBusContacts());
				// 实名认证不通过
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR);
			}

		} else {
			// 1:进行实名认证
			if (!validatorFacadeService.validIdCard(openAccountReq.getCerNo(), openAccountReq.getUserName())) {
				logger.error(
						"个人实名认证不通过, idCardNo:" + openAccountReq.getCerNo() + "name: " + openAccountReq.getUserName());
				// 实名认证不通过
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR);
			}
		}

		ResultDto<WalletUrlRsp> resultDto = accountService.openAct(openAccountReq);
		logger.info("==========openAct====end===>" + JsonUtils.toJsonString(resultDto));
		return ApiResponse.createResponseWithPayload(resultDto.getData(), resultDto.getResultCode(),
				resultDto.getResultDesc());
	}

	private ReturnCode.System checking(String... str) {
		for (String string : str) {
			if (StringUtils.isBlank(string)) {
				return ReturnCode.System.PARAM_NULL;
			}
		}
		return null;
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation(value = "查询钱包地址", httpMethod = "POST", notes = "009001:(9001)获取地址失败,用户不存在(需要去开启);<br/>000099:服务器错误，请稍后重试", response = WalletUrlRsp.class)
	@PostMapping("/wallet/url/get")
	public Object getWalletUrl(HttpServletRequest request) {
		WalletUrlReq walletUrlReq = new WalletUrlReq();
		AgentLoginDto agentLoginDto = getCurrentUser();
		walletUrlReq.setPono(UUID.randomUUID().toString().replaceAll("-", ""));
		
		ResultDto<WalletUrlRsp> resultDto = null;
		if(AgentStatus.QUIT == agentLoginDto.getStatus() && StringUtils.isNotBlank(agentLoginDto.getOpenId())){
			walletUrlReq.setOpenId(agentLoginDto.getOpenId());
			logger.info("==========getWalletUrl==Account==begin===>"+ JsonUtils.toJsonString(walletUrlReq));
			resultDto = accountService.findWalletUrlByOpenId(walletUrlReq);
		}else{
			walletUrlReq.setPuserType("" + agentLoginDto.getAgentType().value());
			walletUrlReq.setPuserNo(agentLoginDto.getId());
			logger.info("==========getWalletUrl==Account==begin===>"+ JsonUtils.toJsonString(walletUrlReq));
			resultDto = accountService.getWalletUrl(walletUrlReq);
		}
		logger.info("==========getWalletUrl==Account==end===>" + JsonUtils.toJsonString(resultDto));
		if (_999999.equals(resultDto.getResultCode())
				|| (_009001.equals(resultDto.getResultCode()) && !resultDto.getResultDesc().contains("用户不存在"))) {
			resultDto.setResultCode(ReturnCode.System.SYSTEM_ERROR.getCode());
		}
		
		if("A10004".equals(resultDto.getResultCode())){
			resultDto.setResultCode(_009001);
		}
		logger.info("==========getWalletUrl====end===>" + JsonUtils.toJsonString(resultDto));
		return ApiResponse.createResponseWithPayload(resultDto.getData(), resultDto.getResultCode(),
				resultDto.getResultDesc());
	}
	
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation(value = "查询钱包开户状态", httpMethod = "POST", notes = "009001:(9001)获取地址失败,用户不存在(需要去开启);<br/>000099:服务器错误，请稍后重试", response = WalletUrlRsp.class)
	@PostMapping("/wallet/info")
	public Object getWalletInfo(HttpServletRequest request) {
		WalletUrlReq walletUrlReq = new WalletUrlReq();
		AgentLoginDto agentLoginDto = getCurrentUser();
		if(AgentStatus.QUIT == agentLoginDto.getStatus()){
			WalletUrlRsp rsp = new WalletUrlRsp();
			if(StringUtils.isNotBlank(agentLoginDto.getOpenId())){
				//钱包开启状态：0未开户，1开户中，2开户失败，3开户成功
				rsp.setStatus("3");
			}else {
				rsp.setStatus("0");
			}
			logger.info("==========离职，被转移合伙人直接返回:openid存在返回3开户成功，否者0未开户====end===>" + JsonUtils.toJsonString(rsp));
			return ApiResponse.createResponseWithPayload(rsp, ReturnCode.System.SUCCESS.getCode(),
					ReturnCode.System.SUCCESS.getMsg());
		}
		walletUrlReq.setPuserNo(agentLoginDto.getId());
		walletUrlReq.setPuserType("" + agentLoginDto.getAgentType().value());
		walletUrlReq.setPono(UUID.randomUUID().toString().replaceAll("-", ""));

		logger.info("==========getWalletInfo====begin===>" + JsonUtils.toJsonString(walletUrlReq));
		ResultDto<WalletUrlRsp> resultDto = accountService.openResult(walletUrlReq);
		logger.info("==========getWalletInfo====end===>" + JsonUtils.toJsonString(resultDto));
		return ApiResponse.createResponseWithPayload(resultDto.getData(), resultDto.getResultCode(),
				resultDto.getResultDesc());
	}
}
