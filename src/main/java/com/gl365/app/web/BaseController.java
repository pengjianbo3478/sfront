package com.gl365.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.service.RedisService;

/**
 * Created by caoyilong on 2017/6/2.
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Autowired
	private RedisService redisService;

	public HttpSession getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@ModelAttribute
	public void init(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		this.session = session;
		this.request = request;
		this.response = response;
	}

	/**
	 * @return 返回当前正在调用接口的用户ID
	 */
	public String getCurrentLoginUserId() {
		String id = null;
		Object operatorId = request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		if (operatorId != null && !"null".equals(operatorId)) {
			id = operatorId.toString();
		}
		
		logger.info("=====getCurrentLoginUserId=====>" + id);
		return id;
	}

	/**
	 * @return 当前登录用户的基本信息
	 */
	public AgentLoginDto getCurrentUser() {
		AgentLoginDto agentLoginDto = (AgentLoginDto) redisService.get(getCurrentLoginUserId());
		return agentLoginDto;
	}
}
