package com.gl365.app.handler;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.SignParamConvert;
import com.gl365.app.dto.Agent;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.Command;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.UpdatePasswordCommand;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.dto.users.LoginCommand;
import com.gl365.app.remote.account.AccountService;
import com.gl365.app.remote.account.dto.CommonDTO;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.settlement.BankAccountInfoService;
import com.gl365.app.remote.settlement.ApiRequest.UpdateBankAccountCommand;
import com.gl365.app.remote.settlement.ApiResponse.SynchronizeBankResultDto;
import com.gl365.app.security.PersistTokenService;
import com.gl365.app.service.RedisService;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.RSAUtils;
import com.gl365.app.web.permission.PermissionAspect;

@Component("agentHandler")
public class AgentHandler {
	private static final String _0_0_1 = "0.0.1";
	private static final String QUIT = "QUIT_";
	private static final Integer Max_ERROR_COUNT = 5;// 密码最大错误次数
	private static final String PASSWORD_ERR_REDIS_PREFIX = "PASSWORD_VALIDATE";// 密码错误key前缀
	public static final String LOGIN_REDIS_PREFIX = "LOGIN_REDIS_PREFIX";// 密码错误key前缀
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AgentService agentService;
	@Autowired
	private BankAccountInfoService bankAccountInfoService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ValidatorFacadeService validatorFacadeService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private PersistTokenService tokenService;
	
	@Value("${validator.uncheck}")
	private String UN_CHECK;

	public ApiResponse login(LoginCommand command, HttpServletRequest request) throws Exception {
		LOG.info("用户登录,参数：[{}]", ToStringBuilder.reflectionToString(command));
		// 1.签名验证
		if (!RSAUtils.verify(SignParamConvert.signConvert4Login(command).getBytes(), command.getSign())) {
			//验签失败
			return ApiResponse.createResponseWithMessage(ReturnCode.System.VERIFY_SIGN_FAIL);
		}
		
		// 2.获取用户信息
		String username = command.getUsername();
		Boolean isMobile = isChinaPhoneLegal(username);
		Agent agentUser;
		if (isMobile) {
			agentUser = agentService.findByMobile(username);
			
			if (null == agentUser && _0_0_1.equals(command.getApiVersion())) {
				agentUser = agentService.findQuitByMobile(username);
				if (null == agentUser || StringUtils.isBlank(agentUser.getId())) {
					return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.NO_USER_INFO_ERROR);
				}
				agentUser.setId(QUIT+agentUser.getId());
			}
			
			if (null == agentUser || StringUtils.isBlank(agentUser.getId())) {
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.NO_USER_INFO_ERROR);
			}
		} else {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.ID_PWD_MATCHING_ERROR);
		}
		String password = agentUser.getPassword();
		String id = agentUser.getId();
		//3.密码验证
		ApiResponse<?> validateUserRt = validateUser(command, agentUser, password);
		if (!ReturnCode.System.SUCCESS.getCode().equals(validateUserRt.getCode())) {
			return validateUserRt;
		}
		//状态不是已审核
		if (!agentUser.getStatus().equals(AgentStatus.APPROVED) && !agentUser.getStatus().equals(AgentStatus.QUIT)) {
			// 用户状态
			if (agentUser.getStatus().equals(AgentStatus.PENDING)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.USER_PENDING);
			}
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.USER_STATUS_ERROR);
		}

		// 4.保存Token
		String token = UUID.randomUUID().toString();
		Long liveTime = 2592000L;
		tokenService.updateToken(token, id, liveTime);
		redisService.set(LOGIN_REDIS_PREFIX + id, token, liveTime);
		
		// 6.构建出参
		AgentLoginDto agentLoginDto = new AgentLoginDto();
		agentLoginDto.setAgentType(agentUser.getAgentType());
		agentLoginDto.setId(id);
		agentLoginDto.setMobile(agentUser.getMobile());
		agentLoginDto.setToken(token);
		agentLoginDto.setName(agentUser.getName());
		agentLoginDto.setAvatarUrl((agentUser.getAvatarUrl() == null ? "" : PicHandler.addPrefixUrlStatic(agentUser.getAvatarUrl())));
		//设置代理区域
		agentLoginDto.setActingAreaId(agentUser.getActingAreaId());
		agentLoginDto.setStatus(agentUser.getStatus());
		agentLoginDto.setCity(agentUser.getCity());
		agentLoginDto.setCreationTime(agentUser.getCreationTime());
		agentLoginDto.setUpstreamAgent(agentUser.getUpstreamAgent());
		agentLoginDto.setUpstreamNonPersonalAgent(agentUser.getUpstreamNonPersonalAgent());
		agentLoginDto.setOpenId(agentUser.getOpenId());
		request.getSession(true).setAttribute(HttpParamConstant.Session.GL_APP_USER_ID, id);

		//清除密锁定次数
		redisService.del(PASSWORD_ERR_REDIS_PREFIX + id);
	
		//缓存
		LOG.info("用户缓存 id={},agentLoginDto={},time={}",id,JsonUtils.toJsonString(agentLoginDto),PermissionAspect.LIVE_TIME);
		redisService.set(id, agentLoginDto, PermissionAspect.LIVE_TIME);
		return ApiResponse.createSuccess(agentLoginDto);

	}

	//校验身份
	private ApiResponse<?> validateUser(LoginCommand command, Agent agent, String password) {
		try {
			String id = agent.getId();
			String key = PASSWORD_ERR_REDIS_PREFIX + id;
			Integer count = (Integer) redisService.get(key);
			if (count == null) {
				count = 0;
			}
			if (count > (Max_ERROR_COUNT - 1)) {
				//TODO:暂时没做锁定的
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PASSWORD_COUNT_ERROR);
			}
			// 密码输错次数校验
			if (!password.equals(command.getPassword())) {
				count += 1;
				// redis自动解除冻结
				LocalDateTime cureTime = LocalDateTime.now();
				int time = 86400 - ((cureTime.getHour() * 60 * 60) + (cureTime.getMinute() * 60) + cureTime.getSecond());
				redisService.set(key, count, new Long((long) time));
				ApiResponse<?> rs = ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PASSWORD_COUNT_ERROR);
				Integer errCount = Max_ERROR_COUNT - count;
				if (count == 1) {
					rs.setMessage("密码输入错误");
				} else {
					rs.setMessage("您已输错" + count + "次,还有" + errCount + "次机会");
					if(count == Max_ERROR_COUNT){
						rs = ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PASSWORD_COUNT_ERROR);
					}
				}
				return rs;
			}

			return ApiResponse.createNoErrorResponse(ReturnCode.System.SUCCESS.getMsg());
		} catch (Exception e) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
	}

	private String getValueBykey(String key) {
		String persistToken = tokenService.getPersistToken(key);
		if (null != persistToken) {
			String[] ss = persistToken.split(",");
			if ((null != ss) && (2 == ss.length)) {
				String value = ss[0];
				return value;
			}
		}
		return null;
	}


	public ApiResponse<?> logout(HttpServletRequest request, Command command) {
		LOG.info("用户注销,参数：[{}]", ToStringBuilder.reflectionToString(command));
		try {
			// 1.签名验证不通过
			if (!RSAUtils.verify(SignParamConvert.signConvert4Normal(command).getBytes(), command.getSign())) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.VERIFY_SIGN_FAIL);
			} else if (StringUtils.isBlank(tokenService.getPersistToken(command.getToken()))) {
				// 2.Token验证不通过
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NEED_LOGIN);
			} else {
			// 3.更新Token
			HttpSession session = request.getSession(false);
			if (null != session) {
				session.invalidate();
			}
			tokenService.clearToken(command.getToken());
			return ApiResponse.createResponseWithMessage(ReturnCode.System.SUCCESS);
			}
		} catch (Exception e) {
			LOG.error("注销失败,错误：{}", e);
			return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
		}
	}


	public ApiResponse<?> updatePassWord(UpdatePasswordCommand command) {

		if (StringUtils.isBlank(command.getOldPassword()) || StringUtils.isBlank(command.getNewPassword())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}

		if (command.getOldPassword().equals(command.getNewPassword())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PWD_SAME_ERROR);
		} 
		
		return agentService.updatePassword(command);

	}


	public SynchronizeBankResultDto updateBankAccount(UpdateBankAccountCommand command, String idCardNo, String name) {
		String isPublic = "1";
		//0:默认验证所有,1:不验证对公法人,2:不验证对私法人,3:不验证对私非法人
		if ("02".equals(command.getBankAccountType())) {
			isPublic = "2";
		}
		boolean isPassed = true;
		if(StringUtils.isBlank(UN_CHECK)||!UN_CHECK.contains(isPublic)){
			isPassed = validatorFacadeService.validBankCard(command.getBankAccountNo(), idCardNo, name);
		}
		if (isPassed == false) {
			return null;
		}
		
		//根据银行卡判断所属银行
		Boolean bankBinRlt = checkBankBin(command.getBankMainName(), command.getBankAccountNo());
		if(!bankBinRlt){
			return null;
		}
		
		//调用清算保存银行卡信息
		LOG.info("AgentHandler.updateBankAccount invoking settlement project begin \n url=/bankAccountInfo/synchroBankAccountInfo param={}",JsonUtils.toJsonString(command));
		SynchronizeBankResultDto rlt = bankAccountInfoService.synchronizeBankAccountInfo(command);
		LOG.info("AgentHandler.updateBankAccount invoking settlement project end \n rlt={}",JsonUtils.toJsonString(rlt));
		return rlt;
	}


	/**
	 * 手机号码匹配格式
	 */
	private boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		//String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		String regExp = "^1(3|4|5|7|8)\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	@Deprecated
	public void unlock(String mobile) {
		Agent agent = agentService.findByMobile(mobile);
		if(agent!=null){
			String id = agent.getId();
			String key = PASSWORD_ERR_REDIS_PREFIX + id;
			redisService.del(key);
		}
	}

	//根据银行卡判断所属银行
	public Boolean checkBankBin(String bankMainName,String bankAccountNo) {
		if(StringUtils.isBlank(bankMainName) || StringUtils.isBlank(bankAccountNo)){
			return false;
		}
		//根据银行卡判断所属银行
		LOG.info("AgentHandler.updateBankAccount invoking account project begin \n url=/bindinfo/queryBankInfoByCardNo/{cardNo} bankAccountNo={}",bankAccountNo);
		CommonDTO checkCardNo = accountService.queryBankInfoByCardNo(bankAccountNo);
		LOG.info("AgentHandler.updateBankAccount invoking account project end \n rlt={}",JsonUtils.toJsonString(checkCardNo));
		//没查到卡bin信息就不校验
		if(StringUtils.isNotBlank(checkCardNo.getBankName())){
			if(!checkCardNo.getBankName().contains(bankMainName)){
				String str = bankMainName.replace("银行", "");
				for (int i = 0; i < str.length(); i++) {
					if(!checkCardNo.getBankName().contains(str.substring(i, i+1))){
						return false;
					}
				}
				return true;
			}
			return true;
		}
		return true;
	}

}
