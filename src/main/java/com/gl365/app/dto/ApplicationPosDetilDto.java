package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class ApplicationPosDetilDto {
	
	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * POS机SN
	 */
	private String posSn;
	
	/**
	 * 型号
	 */
	private String version;
	
	/**
	 * POS机终端ID
	 */
	private String posDeviceId;
	
	@ApiModelProperty("1：支付公司通过申请；2：已绑定；3：已解绑")
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPosDeviceId() {
		return posDeviceId;
	}

	public void setPosDeviceId(String posDeviceId) {
		this.posDeviceId = posDeviceId;
	}
}
