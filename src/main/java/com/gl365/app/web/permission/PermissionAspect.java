package com.gl365.app.web.permission;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.dto.Agent;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class PermissionAspect {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public static final Long LIVE_TIME = 86400L;

	@Autowired
	private RedisService redisService;

	@Autowired
	private AgentService agentService;

	@Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(permission)")
	public Object requestPermission(ProceedingJoinPoint proceedingJoinPoint, Permission permission) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (null == request) {
			LOG.error("requestPermission handler error : target:[{}] no param : HttpServletRequest");
			return ApiResponse.createResponseWithMessage(ReturnCode.System.REQUEST_IS_NULL);
		}
		// 请求路径
		String path = request.getServletPath();
		//这个userId就是operatorId，因为框架中的securityFilter里面定死了GL_APP_USER_ID无法改为operatorId
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		if (StringUtils.isEmpty(operatorId) || "null".equals(operatorId)) {
			LOG.error("requestPermission session's operatorId is null,must login first");
			return ApiResponse.createResponseWithMessage(ReturnCode.System.NEED_LOGIN);
		}

		// 从redis中获取operator，如果redis中没有，则从数据库取
		AgentLoginDto agentLoginDto = (AgentLoginDto) redisService.get(operatorId);
		if (null == agentLoginDto) {
			Agent agent = agentService.findById(operatorId);
			if (null == agent) {
				LOG.error("requestPermission ===> agentService.findById,operatorId={}, operator:{}", operatorId, agent);
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
			} else {
				agentLoginDto = new AgentLoginDto();
				agentLoginDto.setAgentType(agent.getAgentType());
				agentLoginDto.setId(agent.getId());
				agentLoginDto.setMobile(agent.getMobile());
				String token = request.getHeader(HttpParamConstant.Headers.GL_TOKEN);
				agentLoginDto.setToken(token);
				agentLoginDto.setName(agent.getName());
				agentLoginDto.setAvatarUrl((agent.getAvatarUrl() == null ? "" : PicHandler.addPrefixUrlStatic(agent.getAvatarUrl())));
				//设置代理区域
				agentLoginDto.setActingAreaId(agent.getActingAreaId());
				agentLoginDto.setStatus(agent.getStatus());
				agentLoginDto.setCity(agent.getCity());
				agentLoginDto.setCreationTime(agent.getCreationTime());
				redisService.set(operatorId, agentLoginDto, LIVE_TIME);
			}
		}

		return proceedingJoinPoint.proceed();
	}
}
