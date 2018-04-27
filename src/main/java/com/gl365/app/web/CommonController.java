package com.gl365.app.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.Agent;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.AreaDto;
import com.gl365.app.dto.CaptchaCodeResult;
import com.gl365.app.dto.MerchantIndustry;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.ValidateDto;
import com.gl365.app.dto.command.FeedbackCommand;
import com.gl365.app.dto.command.FindMyAgentPartnerCommand;
import com.gl365.app.dto.command.ValidIdCardCommand;
import com.gl365.app.dto.command.validateCaptchaCommand;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.remote.gd.GdService;
import com.gl365.app.remote.gd.GdServiceImpl;
import com.gl365.app.remote.gd.dto.AreaDataDto;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.FeedbackService;
import com.gl365.app.remote.sales.SmsService;
import com.gl365.app.remote.sts.GeiLeStsService;
import com.gl365.app.service.CaptchaCodeService;
import com.gl365.app.service.MerchantFacadeService;
import com.gl365.app.service.RedisService;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.TimeOutCache;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

/**
 * Created by caoyilong on 2017/5/24.
 */
@Api(description = "基础相关", tags = "COMMON")
@RestController
@RequestMapping("/api/basic")
public class CommonController extends BaseController {

	private static final String AREA_INFO_BY_ID_1 = "AREA_INFO_BY_ID_1";

	private static final String CAPTCHA_CODE_KEY = "captcha_code";

	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private GeiLeStsService aliYunOssService;

	@Autowired
	private GdService gdService;
	
	@Autowired
	private GdServiceImpl gdServiceImpl;

	@Autowired
	private MerchantFacadeService merchantFacadeService;

	@Autowired
	private CaptchaCodeService captchaCodeService;

	@Autowired
	private ValidatorFacadeService validatorFacadeService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private AgentService agentService;

	private static final String IS_HIDE = "Y";// Y已经被代理的区域不显示,N显示所有

	@ApiOperation("申请ALiYunOss的key-cyl")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/generateKey", method = RequestMethod.POST)
	public ApiResponse<Object> generateKey() throws IOException {
		return ApiResponse.createSuccess(aliYunOssService.generateOssKey());
	}

	@ApiOperation("获取地区,1获得省数据-cyl")
	@RequestMapping(value = "/areas/{areaId}", method = RequestMethod.POST)
	public ApiResponse<List<AreaDataDto>> getAreaData(@PathVariable("areaId") Integer areaId) {
		List<AreaDataDto> result = gdService.getAreaInfoById(areaId);
		return ApiResponse.createSuccess(result);
	}

	@ApiOperation("获取区域名称")
	@RequestMapping(value = "/area/name/{areaId}", method = RequestMethod.POST)
	public ApiResponse<List<AreaDataDto>> getAreaName(@PathVariable("areaId") Integer areaId) {
		return ApiResponse.createSuccess(gdServiceImpl.getAllAreaById1().get(areaId));
	}

	@ApiOperation("获取地区,支持是否隐藏已经被代理的区域")
	@RequestMapping(value = "/areas/queryAreaData", method = RequestMethod.POST)
	public ApiResponse<List<AreaDataDto>> queryAreaData(@RequestBody @Validated AreaDto areaDto) {
		List<AreaDataDto> result = gdService.getAreaInfoById(areaDto.getAreaId());
		AgentLoginDto agentLoginDto = getCurrentUser();
		if (IS_HIDE.equals(areaDto.getHide()) && agentLoginDto != null
				&& agentLoginDto.getAgentType() == AgentType.CITY) {
			FindMyAgentPartnerCommand command = new FindMyAgentPartnerCommand();
			command.setAgentType(AgentType.COUNTY);
			command.setUpstreamAgentId(agentLoginDto.getId());
			List<Agent> agents = agentService.findMyAgentByUpstreamAgentIdList(command);
			for (AreaDataDto areaDataDto : result) {
				for (Agent agent : agents) {
					if (null != areaDataDto.getId() && areaDataDto.getId().equals(agent.getActingAreaId())) {
						areaDataDto.setIsHide(IS_HIDE);
					}
				}
			}
			return ApiResponse.createSuccess(result);
		}
		return ApiResponse.createSuccess(result);
	}
	
	@ApiOperation("获取全国地区ID与地区名字映射表")
	@RequestMapping(value = "/areasIdMap", method = RequestMethod.GET)
	public ApiResponse<Map<Integer, String>> getAreasIdMap() {
		// List<AreaDataDto> areas = gdService.getAreaInfoById(1);
		List<AreaDataDto> areas = (List<AreaDataDto>) TimeOutCache.getInstance().get(AREA_INFO_BY_ID_1);
		if (CollectionUtils.isEmpty(areas)) {
			areas = gdService.getAreaInfoById(1);
			TimeOutCache.getInstance().put(AREA_INFO_BY_ID_1, areas);
		}
		Map<Integer, String> mapping = new HashMap<>();
		areas.forEach((action) -> mapping.put(action.getId(), action.getName()));
		return ApiResponse.createSuccess(mapping);
	}

	@ApiOperation("获取行业类别-cyl")
	@RequestMapping(value = "/getMerchantIndustry", method = RequestMethod.POST)
	public ApiResponse<List<MerchantIndustry>> getMerchantIndustry() {
		return ApiResponse.createSuccess(merchantFacadeService.queryMerchantIndustry());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation("获取行业ID与行业名字映射表-cyl")
	@RequestMapping(value = "/industriesIdMap", method = RequestMethod.GET)
	public ApiResponse<Map<Integer, String>> getIndustriesIdMap() {
		return ApiResponse.createSuccess(merchantFacadeService.getIndustriesIdMap());
	}

	/**
	 * 验证码
	 */
	@ApiOperation("图形验证码-cyl")
	@RequestMapping(value = "/captcha", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
	@ApiResponses({
			@io.swagger.annotations.ApiResponse(response = BufferedImage.class, message = "返回的验证码图片", code = 200) })
	public BufferedImage captchaCode(HttpServletRequest request) throws Exception {
		String key = request.getParameter("key");
		LOG.info("图形验证码 captchaCode begin, reqParam={}", JsonUtils.toJsonString(key));
		Long beginTime = System.currentTimeMillis();
		try {
			if (StringUtils.isBlank(key)) {
				return null;
			}
			CaptchaCodeResult result = captchaCodeService.generateCaptchaCode();
			// 有效期为1800s
			String redisKey = CAPTCHA_CODE_KEY + "_" + key;
			redisService.set(redisKey, result.getCode(), 1800L);
			Long endTime = System.currentTimeMillis();
			LOG.info("图形验证码 captchaCode end,rlt={},time={}ms", result.getCode(), (endTime - beginTime));
			return result.getImage();
		} catch (Exception e) {
			LOG.error("图形验证码 captchaCode exception,e:{}", e);
			return null;
		}
	}

	@ApiOperation("验证图形验证码-cyl")
	@RequestMapping(value = "/validateCaptcha", method = RequestMethod.POST)
	public ApiResponse validateCaptcha(@RequestBody validateCaptchaCommand validateCaptchaCommand) {
		LOG.info("验证图形验证码 validateCaptcha begin, reqParam={}", JsonUtils.toJsonString(validateCaptchaCommand));
		if (StringUtils.isBlank(validateCaptchaCommand.getCode())
				|| StringUtils.isBlank(validateCaptchaCommand.getKey())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		String redisKey = CAPTCHA_CODE_KEY + "_" + validateCaptchaCommand.getKey();
		String code = (String) redisService.get(redisKey);
		LOG.info("缓存的图形验证码  code={}", code);
		if (code != null) {
			// 验证通过
			if (code.equalsIgnoreCase(validateCaptchaCommand.getCode())) {
				LOG.info("图形验证码成功", code);
				// 删除redis里面的code
				redisService.del(redisKey);
				return ApiResponse.createSuccess(null);
			} else {
				LOG.info("图形验证码失败");
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.IMAGE_CODE_ERROR);
			}
		}
		return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.IMAGE_CODE_TIME_OUT);

	}

	@ApiOperation("实名认证-cyl")
	@RequestMapping(value = "/validIdCard", method = RequestMethod.POST)
	public ApiResponse<ValidateDto> validIdCard(@RequestBody ValidIdCardCommand validIdCardCommand) {
		if (StringUtils.isBlank(validIdCardCommand.getCardId()) || StringUtils.isBlank(validIdCardCommand.getName())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		ValidateDto resp = new ValidateDto();
		boolean rlt = validatorFacadeService.validIdCard(validIdCardCommand.getCardId(), validIdCardCommand.getName());
		resp.setResult(rlt);
		return ApiResponse.createResponseWithPayload(resp, ReturnCode.System.SUCCESS.getCode(),
				rlt ? ReturnCode.System.SUCCESS.getMsg() : "实名认证失败");
	}

	@ApiOperation("用于查看发送出去的验证码-cyl，参数说明mobile:手机号，type:验证码类型，暂时为1:注册，2：修改密码，4：修改手机号")
	@RequestMapping(value = "/queryCode/{mobile}/{type}", method = RequestMethod.GET)
	public Object queryCode(@PathVariable("mobile") String mobile, @PathVariable("type") Integer type) {
		return smsService.queryCode(mobile, type);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("意见反馈")
	@RequestMapping(value = "/createFeedback", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<Void> createFeedback(@Validated @RequestBody FeedbackCommand command) {
		if (command.getContent() == null || command.getContent().length() > 300) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_ERROR);
		}
		String applicantId = getCurrentLoginUserId();
		command.setAgentId(applicantId);
		feedbackService.addFeedBack(command);
		return ApiResponse.createSuccess(null);
	}
}
