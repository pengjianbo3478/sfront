package com.gl365.app.dto.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ForgotPasswordCommand {
	
	@NotNull(message = "手机号不能为空")
    private String mobile;
	
	@Length(max = 32, min=32, message = "密码格式错误")
	@NotNull(message = "密码不能为空")
    private String password;
	
	@NotNull(message = "密钥不能为空")
	private String token;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
