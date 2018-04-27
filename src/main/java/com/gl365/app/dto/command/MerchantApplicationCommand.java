package com.gl365.app.dto.command;

import com.gl365.app.dto.enum_type.DeviceType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *   新增进件的申请详情
 */
public class MerchantApplicationCommand implements Serializable {
	
	private static final long serialVersionUID = 6427521756637528647L;

	private Integer numMobileDevice;

	private DeviceType mobileDeviceType;

	private Integer numImmobileDevice;

	private DeviceType immobileDeviceType;

	private Integer numSmartDevice;

	private DeviceType smartDeviceType = DeviceType.BUY;//只能为购买

	private Boolean applyForQrcode;


	@ApiModelProperty("可移动设备数量")
	public Integer getNumMobileDevice() {
		return numMobileDevice;
	}

	public void setNumMobileDevice(Integer numMobileDevice) {
		this.numMobileDevice = numMobileDevice;
	}

	@ApiModelProperty("可移动设备类型。1：购买；2：租赁")
	public DeviceType getMobileDeviceType() {
		return mobileDeviceType;
	}

	public void setMobileDeviceType(DeviceType mobileDeviceType) {
		this.mobileDeviceType = mobileDeviceType;
	}

	@ApiModelProperty("固定设备数量")
	public Integer getNumImmobileDevice() {
		return numImmobileDevice;
	}

	public void setNumImmobileDevice(Integer numImmobileDevice) {
		this.numImmobileDevice = numImmobileDevice;
	}

	@ApiModelProperty("固定设备类型。1：购买；2：租赁")
	public DeviceType getImmobileDeviceType() {
		return immobileDeviceType;
	}

	public void setImmobileDeviceType(DeviceType immobileDeviceType) {
		this.immobileDeviceType = immobileDeviceType;
	}

	@ApiModelProperty("智能设备数量")
	public Integer getNumSmartDevice() {
		return numSmartDevice;
	}

	public void setNumSmartDevice(Integer numSmartDevice) {
		this.numSmartDevice = numSmartDevice;
	}

	@ApiModelProperty("智能设备类型。固定为1。1：购买")
	public DeviceType getSmartDeviceType() {
		return smartDeviceType;
	}

	public void setSmartDeviceType(DeviceType smartDeviceType) {
		this.smartDeviceType = smartDeviceType;
	}

	@ApiModelProperty("是否申请二维码。0：不申请；1：申请")
	public Boolean getApplyForQrcode() {
		return applyForQrcode;
	}

	public void setApplyForQrcode(Boolean applyForQrcode) {
		this.applyForQrcode = applyForQrcode;
	}
}
