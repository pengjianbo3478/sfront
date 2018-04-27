package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * < 基础指令  >
 * <p>
 * 包含常规请求携带的参数
 *
 * @author hui.li 2017年4月12日 - 下午1:07:08
 * @Since 1.0
 */
public class Command implements Serializable {

	private static final long serialVersionUID = 4595251210060842443L;

	/**
	 * Token ： 保持登录状态的凭证
	 */
	private String token;

	/**
	 * DeviceId : 客户端设备ID
	 * 如手机的IMEI
	 */
	private String deviceId;

	/**
	 * ClientId : 客户端类型ID
	 */
	private String clientId;

	/**
	 * ClientVer : 客户端软件版本号
	 * 格式是X.X.X。
	 * 命名规则是：大版本号.子版本号.补丁版本号
	 */
	private String clientVer;

	/**
	 * Timestamp : 时间戳
	 */
	private String timestamp;

	/**
	 * Sign : 签名,基于RSA加密算法
	 */
	private String sign;


	@ApiModelProperty("系统用来识别用户的令牌，可选，用户没有进行登录时接口中不含此值")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@ApiModelProperty("设备号，如为手机可采用IMEI码作为设备号，PC可采用硬盘号作为设备号。")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@ApiModelProperty("客户端识别码，客户端识别码，参考如下：\n" +
			"gllife.app.ios 给乐生活APP苹果客户端\n" +
			"gllife.app.android 给乐生活APP安卓客户端\n" +
			"glMerchant.app.ios 给乐商户APP苹果客户端\n" +
			"glMerchant.app.android 给乐商户APP安卓客户端\n" +
			"glMerchant.pc.web 给乐商户PC机浏览器客户端")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@ApiModelProperty("客户端版本号，参考如：6.1.0")
	public String getClientVer() {
		return clientVer;
	}

	public void setClientVer(String clientVer) {
		this.clientVer = clientVer;
	}

	@ApiModelProperty("时间戳")
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@ApiModelProperty("签名")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}


}
