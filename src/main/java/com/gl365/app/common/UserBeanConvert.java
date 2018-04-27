package com.gl365.app.common;

import com.gl365.app.dto.Command;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("userBeanConvert")
public class UserBeanConvert {

	/**
	 * 头信息转基础指令
	 */
	public static void Headers2Command(Command command, HttpServletRequest request) {
		command.setToken(request.getHeader(HttpParamConstant.Headers.GL_TOKEN));
		command.setDeviceId(request.getHeader(HttpParamConstant.Headers.GL_DEVICE_ID));
		command.setClientId(request.getHeader(HttpParamConstant.Headers.GL_CLIENT_ID));
		command.setClientVer(request.getHeader(HttpParamConstant.Headers.GL_CLIENT_VER));
		command.setTimestamp(request.getHeader(HttpParamConstant.Headers.GL_TIMESTAMP));
		command.setSign(request.getHeader(HttpParamConstant.Headers.GL_REQ_SIGN));
	}
}
