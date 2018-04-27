package com.gl365.app.service;

import com.gl365.app.dto.CaptchaCodeResult;

/**
 * 验证码相关
 */
public interface CaptchaCodeService {

	CaptchaCodeResult generateCaptchaCode();

}
