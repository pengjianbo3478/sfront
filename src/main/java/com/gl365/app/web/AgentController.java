package com.gl365.app.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.aspect.Log;
import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.UserBeanConvert;
import com.gl365.app.dto.Agent;
import com.gl365.app.dto.AgentIndexDto;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.Command;
import com.gl365.app.dto.MyRankingDto;
import com.gl365.app.dto.ResultForSDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.dto.command.ForgotPasswordCommand;
import com.gl365.app.dto.command.IndexCommand;
import com.gl365.app.dto.command.UpdateMobileCommand;
import com.gl365.app.dto.command.UpdateNickNameCommand;
import com.gl365.app.dto.command.UpdatePasswordCommand;
import com.gl365.app.dto.sms.SendSMSReq;
import com.gl365.app.dto.sms.SendSMSType;
import com.gl365.app.dto.sms.VerifySMSReq;
import com.gl365.app.dto.users.AgentCheckBankBin;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.dto.users.LoginCommand;
import com.gl365.app.handler.AgentHandler;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.SmsService;
import com.gl365.app.remote.settlement.BankAccountInfoService;
import com.gl365.app.remote.settlement.ApiRequest.UpdateBankAccountCommand;
import com.gl365.app.remote.settlement.ApiResponse.BankAccountDto;
import com.gl365.app.remote.settlement.ApiResponse.SingleBankDto;
import com.gl365.app.remote.settlement.ApiResponse.SynchronizeBankResultDto;
import com.gl365.app.service.AgentFacadeService;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.RSAUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关(代理)
 * Created by caoyilong on 2017/6/1.
 */
@Api(description = "代理相关", tags = "AGENT")
@RestController
@RequestMapping("/api/agent")
public class AgentController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentController.class);
	
	private static final String FORGOT_PWD_PREFIX = "FORGOT_PWD_PREFIX";// 忘记密码key前缀

	@Autowired
	private AgentHandler agentHandler;
	
	@Autowired
	private PicHandler picHandler;

	@Autowired
	private AgentService agentService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private BankAccountInfoService bankAccountInfoService;

	@Autowired
	private AgentFacadeService agentFacadeService;
	
	@Autowired
	private RedisService redisService;
	

	/**
	 * 登录
	 */
	@ApiOperation("登录-cyl")
	@PostMapping("/login")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "GL_CLIENT_ID", dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "GL_CLIENT_VER", dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "GL_DEVICE_ID", dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "GL_REQ_SIGN", dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "GL_TIMESTAMP", dataType = "string", paramType = "header")
	})
	public ApiResponse<AgentLoginDto> login(HttpServletRequest request, @RequestBody LoginCommand command) {
		try {
			LOG.info("用户登录  入参：{}", JsonUtils.toJsonString(command));
			UserBeanConvert.Headers2Command(command, request);
			if (StringUtils.isBlank(command.getUsername()) || StringUtils.isBlank(command.getDeviceName())
					|| StringUtils.isBlank(command.getDeviceVersion()) || StringUtils.isBlank(command.getDeviceId())
					|| StringUtils.isBlank(command.getClientId()) || StringUtils.isBlank(command.getClientVer())
					|| StringUtils.isBlank(command.getTimestamp()) || StringUtils.isBlank(command.getSign())) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_ERROR);
			}
			ApiResponse rlt = agentHandler.login(command, request);
			if ((!ReturnCode.System.SUCCESS.getCode().equals(rlt.getCode()))
					&& (!ReturnCode.AgentUser.USER_PENDING.getCode().equals(rlt.getCode()))
					&& (!ReturnCode.AgentUser.PASSWORD_COUNT_ERROR.getCode().equals(rlt.getCode()))){
				LOG.info("登录失败提示{}",rlt.getMessage());
				rlt.setMessage("账号或密码不正确");
			}
			return rlt;
		} catch (Exception e) {
			LOG.error("登录失败 > > >", e);
			return ApiResponse.getErrorInfo();
		}
	}


	/**
	 * 注销
	 */
	@ApiOperation("注销登录-cyl")
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ApiResponse logout(HttpServletRequest request) {
		LOG.error("用户注销 ");
		String agentId = isLogin(request);
		if (StringUtils.isBlank(agentId)) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.NEED_LOGIN);
		}
		Command command = new Command();
		UserBeanConvert.Headers2Command(command, request);
		if (StringUtils.isBlank(command.getToken()) ||
				StringUtils.isBlank(command.getDeviceId()) ||
				StringUtils.isBlank(command.getClientId()) ||
				StringUtils.isBlank(command.getClientVer()) ||
				StringUtils.isBlank(command.getTimestamp()) ||
				StringUtils.isBlank(command.getSign())
				) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		return agentHandler.logout(request, command);

	}
	
	@ApiOperation("注销转移用户登录，M端调用")
	@RequestMapping(value = "/logout/{agentId}", method = RequestMethod.POST)
	public ApiResponse logoutAgentId(HttpServletRequest request, @PathVariable("agentId")String agentId) {
		LOG.info("注销转移用户登录，M端调用" + agentId);
		if (StringUtils.isBlank(agentId)) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		
		Object token = redisService.get(AgentHandler.LOGIN_REDIS_PREFIX + agentId);
		if(null != token){
			redisService.del(String.valueOf(token));
			redisService.del(AgentHandler.LOGIN_REDIS_PREFIX + agentId);
			redisService.del(agentId);
		}
		
		return ApiResponse.createSuccess(null);

	}

	@ApiOperation("校验手机短信验证码--通用,场景码 [1:注册,2:修改密码,3:商户审核,4:修改手机号码]")
	@RequestMapping(value = "/sendMessageCode/verifyCode", method = RequestMethod.POST)
	public Object verifyCode(@RequestBody VerifySMSReq command) {
		LOG.info("校验手机短信验证码--通用 verifyCode begin,\nreqParam={}",JsonUtils.toJsonString(command));
    	Long beginTime = System.currentTimeMillis();
    	ApiResponse rlt = null;
		try {
			if (StringUtils.isBlank(command.getMobileNo()) || StringUtils.isBlank(command.getVerifyCode()) || StringUtils.isBlank(command.getType().value()+"")) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
			}
			if(command.getType().value()!=2 && StringUtils.isBlank(getCurrentLoginUserId())){
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NEED_LOGIN);
			}
			rlt = smsService.verifyCode(command);
			if((ReturnCode.System.SUCCESS.getCode().equals(rlt.getCode())) && (SendSMSType.UPDATE_PASSWORD.value() == command.getType().value())){
				//忘记密码生产唯一标示返回前段，忘记密码提交时校验
				String key = FORGOT_PWD_PREFIX+command.getMobileNo();
				String value = UUID.randomUUID().toString();
				Long liveTime = 600L;
				redisService.set(key, value, liveTime);
				Map<String, String> mapRlt = new HashMap<>();
				mapRlt.put("token", value);
				rlt.setData(mapRlt);
			}
		} catch (Exception e) {
			LOG.error("校验手机短信验证码--通用  verifyCode invoking sales project exception  ===> smsService.verifyCode exception,e:{}",e);
			rlt = ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("校验手机短信验证码--通用 verifyCode end,\nrlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
	
	//发送手机短信验证码--通用,场景码 [1:注册,2:修改密码,3:商户审核,4:修改手机号码]  统一出口
	public ApiResponse<?> sendSms(SendSMSReq command) {
		LOG.info("sendSms begin,reqParam:{}",JsonUtils.toJsonString(command));
		ApiResponse<?> resp = new ApiResponse<>();
		try{
			if (StringUtils.isBlank(command.getMobileNo()) || StringUtils.isBlank(command.getType().value()+"")) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
			}
			resp = smsService.sendSmsCode(command);
		}catch(Exception e){
			LOG.error("sendSms ===> smsService.sendSms exception,e："+e );
			resp = ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
		return resp;
	}

	@ApiOperation("发送手机短信验证码--适用于修改手机号码")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/sendMessageCode/{mobile}", method = RequestMethod.GET)
	public Object sendMessageCode(@PathVariable("mobile") String mobile) {
		SendSMSReq req = new SendSMSReq();
		req.setMobileNo(mobile);
		req.setType(SendSMSType.UPDATE_MOBILE);
		return sendSms(req);
	}


	@ApiOperation("修改手机号码")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/updateMobile", method = RequestMethod.POST)
	public ApiResponse<Void> updateMobile(@RequestBody UpdateMobileCommand command) {

		command.setAgentId(getCurrentLoginUserId());
		agentService.updateMobile(command);
		return ApiResponse.createNoErrorResponse("");
	}
	
	@ApiOperation("忘记密码发短信")
	@RequestMapping(value = "/sendFogotPasswordMsg/{mobile}", method = RequestMethod.POST)
	public Object sendFogotPasswordMsg(@PathVariable("mobile") String mobile) {
		SendSMSReq req = new SendSMSReq();
		req.setMobileNo(mobile);
		req.setType(SendSMSType.UPDATE_PASSWORD);
		return sendSms(req);
	}
	
	@ApiOperation("商户开户发短信验证码")
	@RequestMapping(value = "/sendOpenActMsg/{mobile}", method = RequestMethod.POST)
	public Object sendMerchantMsg(@PathVariable("mobile") String mobile) {
		SendSMSReq req = new SendSMSReq();
		req.setMobileNo(mobile);
		req.setType(SendSMSType.MERCHANT);
		return sendSms(req);
	}
	
	@ApiOperation("忘记密码")
	@RequestMapping(value = "/fogotPassword", method = RequestMethod.POST)
	public Object fogotPassword(@RequestBody @Validated ForgotPasswordCommand command) {
		LOG.info("忘记密码 fogotPassword begin,\nreqParam={}",JsonUtils.toJsonString(command));
		Long beginTime = System.currentTimeMillis();
    	ApiResponse<?> rlt = null;
		try {
			String key = FORGOT_PWD_PREFIX+command.getMobile();
			String token= (String) redisService.get(key);
			redisService.del(key);
			if(!command.getToken().equals(token)){
				LOG.info("忘记密码 token校验不通过");
				return ApiResponse.createErrorResponse("忘记密码校验不正确");
			}
			
			rlt = agentService.fogotPassword(command);
		} catch (Exception e) {
			LOG.error("忘记密码 fogotPassword  invoking sales project exception  ===> agentService.fogotPassword exception,e:{}",e);
			rlt = ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("忘记密码 fogotPassword end,\nrlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}


	@ApiOperation("修改密码")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
	public Object updatePassWord(HttpServletRequest request, @RequestBody UpdatePasswordCommand command) {
		LOG.info("修改密码 updatePassWord begin,\nreqParam={}",JsonUtils.toJsonString(command));
		Long beginTime = System.currentTimeMillis();
    	ApiResponse<?> rlt = null;
		try {
			command.setAgentId(isLogin(request));
			rlt = agentHandler.updatePassWord(command);
		} catch (Exception e) {
			LOG.error("修改密码 updatePassWord  invoking sales project exception  ===> agentService.updatePassword exception,e:{}",e);
			rlt = ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("修改密码 updatePassWord end,\nrlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}

	@ApiOperation(value = "修改昵称", hidden = true)
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/updateNickName", method = RequestMethod.POST)
	public ApiResponse<Void> updateNickName(@RequestBody UpdateNickNameCommand command) {
		command.setAgentId(getCurrentLoginUserId());
		agentService.updateNickName(command);
		return ApiResponse.createNoErrorResponse("");
	}


	@ApiOperation("查询银行卡")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/findMyBankAccount", method = RequestMethod.POST)
	public ApiResponse<BankAccountDto> findMyBankAccount() {
		AgentLoginDto agent = getCurrentUser();
		BaseCommand command = new BaseCommand();
		command.setOwnerId(agent.getId());
		command.setOwnerType(agent.getAgentType().value() + "");
		LOG.info("AgentController.findMyBankAccount invoking settlement project begin \n url=/bankAccountInfo/querySingle param={}",JsonUtils.toJsonString(command));
		SingleBankDto rlt = bankAccountInfoService.querySingle(command);
		LOG.info("AgentController.findMyBankAccount invoking settlement project end \n rlt={}",JsonUtils.toJsonString(rlt));
		//bankName需要显示简称,直接通过截取字符串获得
		BankAccountDto data = null;
		if(rlt != null && rlt.getData() != null){
			data = rlt.getData();
			String bankName = data.getBankName() == null ? "" : data.getBankName();
			String splitStr = "银行";
			if(bankName.contains(splitStr)){
				bankName = bankName.split(splitStr)[0]+splitStr;
			}
			data.setBankName(bankName);
		}
		return ApiResponse.createSuccess(data);
	}

	@ApiOperation("保存/更新银行卡")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/updateBankAccount", method = RequestMethod.POST)
	public ApiResponse<Void> updateBankAccount(@RequestBody UpdateBankAccountCommand command) {

		if (StringUtils.isBlank(command.getBankAccountName()) || StringUtils.isBlank(command.getBankAccountNo())
				|| StringUtils.isBlank(command.getBankAccountType()) || StringUtils.isBlank(command.getBankName())
				|| StringUtils.isBlank(command.getBankAccountNo()) || StringUtils.isBlank(command.getBankMainName())){
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		
		AgentLoginDto agent = getCurrentUser();
		Agent agent2 = agentService.findById(agent.getId());
		command.setOwnerType(agent.getAgentType().value() + "");
		command.setOwnerId(agent.getId());
		command.setModifyBy(agent.getId());
		/*if ("02".equals(command.getBankAccountType())) {
			command.setCertNo(agent2.getIdCardNo());
		}*/
		if(StringUtils.isEmpty(command.getMobile())){
			command.setMobile(agent2.getMobile());
		}
		
		logger.info("==========>"+JsonUtils.toJsonString(command)+",agent:"+JsonUtils.toJsonString(agent2));
		if(!agent2.getIdCardNo().equals(command.getCertNo())){
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.ID_CARD_DISACCORD_ERROR);
		}

		SynchronizeBankResultDto dto = agentHandler.updateBankAccount(command, agent2.getIdCardNo(), command.getBankAccountName());
		if (null == dto) {
			return ApiResponse.createResponseWithMessage(ReturnCode.Bank.BANK_CARD_NOT_VALIDATE);
		} else {
			return ApiResponse.createSuccess("");
		}

	}


	@ApiOperation("删除银行卡")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/deleteBankAccountInfo", method = RequestMethod.POST)
	public ApiResponse<Void> deleteBankAccountInfo() {

		AgentLoginDto agent = getCurrentUser();
		BaseCommand command = new BaseCommand();
		command.setOwnerId(agent.getId());
		command.setOwnerType(agent.getAgentType().value() + "");

		LOG.info("AgentController.deleteBankAccountInfo invoking settlement project begin \n url=/bankAccountInfo/deleteBankAccountInfo param={}",JsonUtils.toJsonString(command));
		SynchronizeBankResultDto rlt = bankAccountInfoService.deleteBankAccountInfo(command);
		LOG.info("AgentController.deleteBankAccountInfo invoking settlement project end \n rlt={}",JsonUtils.toJsonString(rlt));
		if (null == rlt) {
			return ApiResponse.createResponseWithMessage(rlt.getResultCode(), rlt.getResultDesc());
		} else {
			return ApiResponse.createSuccess("");
		}
	}


	/**
	 * 首页
	 */
	@ApiOperation(value = "首页获得排名接口-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ApiResponse<List<AgentIndexDto>> index(@RequestBody IndexCommand indexCommand) {
		Agent agent = agentService.findById(getCurrentLoginUserId());
		indexCommand.setCurrentUser(agent);
		return agentFacadeService.agentIndex(indexCommand);
	}


	/**
	 * 获得我的排名
	 */
	@ApiOperation(value = "获得我的排名,ranking 为排名-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "getMyRanking", method = RequestMethod.POST)
	public ApiResponse<MyRankingDto> getMyRanking(@RequestBody IndexCommand indexCommand) {
		Agent agent = agentService.findById(getCurrentLoginUserId());
		indexCommand.setCurrentUser(agent);
		return agentFacadeService.getMyRanking(indexCommand);
	}

	private String isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object userId = session.getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
			return userId == null ? null : (String) userId;
		}
		return null;
	}
	
	@ApiOperation(value = "修改头像")
	@Permission(permission = PermissionConstant.LOGIN)
	@GetMapping("/updatePhoto")
	public Object updatePhoto(@RequestParam("photo") String photo){
		LOG.info("修改头像 updatePhoto begin \n photo={}",photo);
    	Long beginTime = System.currentTimeMillis();
    	ResultForSDto<?> rlt = null;
		try {
			if (StringUtils.isBlank(photo)) {
				return new ResultForSDto<>(ReturnCode.System.PARAM_NULL);
			}
			AgentLoginDto agent = getCurrentUser();
			String agentId = null;
			if(null != agent){
				agentId = agent.getId();
			}
			String avatarUrl = picHandler.generateSaveUrl(photo);
			LOG.info("转换修改头像  \n oldPhoto={},curPhoto={},agentId={}",photo,avatarUrl,agentId);
			rlt = agentService.updatePhoto(avatarUrl,agentId);
		} catch (Exception e) {
			LOG.error("修改头像 updatePhoto invoking sales project exception  ===> agentService.updatePhoto exception,e:{}",e);
			rlt = new ResultForSDto<>(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("修改头像 updatePhoto end,rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
	
	/**
	 * 校验，测试用
	 * 
	 * @param sRequest
	 * @param command
	 * @return
	 */
	@Deprecated
	@GetMapping("/loginValidate")
	public Object loginValidate(@RequestParam("text") String text, @RequestParam("sign") String sign) {
		try {
			LOG.info("校验，测试用 > > > 入参：{}", text, sign);
			// 1.签名验证
			Boolean b = RSAUtils.verify(text.getBytes(), sign);
			if (!b) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.VERIFY_SIGN_FAIL);
			}
			return ApiResponse.createSuccess(null);
		} catch (Exception e) {
			LOG.error("校验，测试用失败 > > > 异常：{}", e);
			return ApiResponse.getErrorInfo();
		}
	}

	@Deprecated
	@GetMapping("/getSign")
	public Object loginValidate(@RequestParam("text") String text) {
		try {
			LOG.info("校验，测试用 > > > 入参：{}", text);
			// 1.签名验证
			String b = RSAUtils.sign(text.getBytes());
			String ss = URLEncoder.encode(b, "utf-8");
			return ApiResponse.createSuccess(ss);
		} catch (Exception e) {
			LOG.error("校验，测试用失败 > > > 异常：{}", e);
			return ApiResponse.getErrorInfo();
		}
	}
	
	@Deprecated
	@ApiOperation("手动解锁")
	@GetMapping("/unlock")
	public Object unlock(@RequestParam("mobile") String mobile) {
		try {
			LOG.info("手动解锁 > > > 入参：{}", mobile);
			agentHandler.unlock(mobile);
			return ApiResponse.createNoErrorResponse("SUCCESS");
		} catch (Exception e) {
			LOG.error("手动解锁 > > > 异常：{}", e);
			return ApiResponse.getErrorInfo();
		}
	}
	
	@ApiOperation("校验银行卡开户行")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/checkBankBin", method = RequestMethod.POST)
	public Object checkBankBin(@RequestBody AgentCheckBankBin command) {
		LOG.info("校验银行卡开户行 checkBankBin begin \n param={}",JsonUtils.toJsonString(command));
    	Long beginTime = System.currentTimeMillis();
    	ApiResponse<?> rlt = null;
		try {
			if (StringUtils.isBlank(command.getBankMainName()) || StringUtils.isBlank(command.getBankAccountNo())) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
			}
			Boolean result = agentHandler.checkBankBin(command.getBankMainName(),command.getBankAccountNo());
			if (result) {
				rlt = ApiResponse.createSuccess("");
			} else {
				rlt = ApiResponse.createResponseWithMessage(ReturnCode.Bank.BANK_CARD_BIN_VALIDATE);
			}
		} catch (Exception e) {
			LOG.error("校验银行卡开户行 checkBankBin invoking account project exception  ===> agentService.checkBankBin exception,e:{}",e);
			rlt = ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("校验银行卡开户行 checkBankBin end,rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
}
