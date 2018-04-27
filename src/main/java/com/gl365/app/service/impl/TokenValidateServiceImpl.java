package com.gl365.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gl365.app.common.SignParamConvert;
import com.gl365.app.dto.Command;
import com.gl365.app.security.TokenValidateService;
import com.gl365.app.utils.RSAUtils;

@Component
public class TokenValidateServiceImpl implements TokenValidateService {
	private static final Logger LOG = LoggerFactory.getLogger(TokenValidateServiceImpl.class);

	@Override
	public boolean validate(String token, String deviceId, String clientId, String clientVer, String timestamp,
			String reqSign) {
		LOG.debug("\nGL_DEVICE_ID {}\nGL_CLIENT_ID {}\nGL_CLIENT_VER {}\nGL_REQ_SIGN {}\nGL_TIMESTAMP {}", deviceId,
				clientId, clientVer, reqSign, timestamp);
		boolean IsValidSign = false;
		try {
			Command command = new Command();
			command.setToken(token);
			command.setDeviceId(deviceId);
			command.setClientId(clientId);
			command.setClientVer(clientVer);
			command.setTimestamp(timestamp);
			command.setSign(reqSign);
			String data = SignParamConvert.signConvert4Normal(command);
			if (!StringUtils.isBlank(data)) {
				IsValidSign = RSAUtils.verify(data.getBytes(), reqSign);
			}
		} catch (Exception e) {
			LOG.error("签名验证异常导致失败", e);
			IsValidSign = false;
		}

		return IsValidSign;
	}

	public boolean validate(Command command) {
		LOG.debug(ToStringBuilder.reflectionToString(command));
		boolean IsValidSign = false;
		try {
			String data = SignParamConvert.signConvert4Normal(command);
			if (!StringUtils.isBlank(data)) {
				IsValidSign = RSAUtils.verify(data.getBytes(), command.getSign());
			}
		} catch (Exception e) {
			LOG.error("签名验证异常导致失败", e);
			IsValidSign = false;
		}

		return IsValidSign;
	}

}
