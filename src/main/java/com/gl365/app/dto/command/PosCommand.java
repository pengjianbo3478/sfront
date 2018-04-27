package com.gl365.app.dto.command;

import com.gl365.app.dto.enum_type.PosBrandEnum;
import com.gl365.app.dto.enum_type.PosType;
import com.gl365.app.dto.enum_type.PosVersionEnum;

import io.swagger.annotations.ApiModelProperty;

public class PosCommand {

	@ApiModelProperty(value = "posSn", example = "SG513547314410001", required = true)
	private String posSn;

	@ApiModelProperty(value = "POS机终端ID", example = "157444444001")
	private String posDeviceId;

	@ApiModelProperty(value = "POS机类型。1：固定机；2：移动机：3：智能机", example = "3")
	private PosType posType;

	@ApiModelProperty(value = "品牌:06(惠尔丰),01(百富)", example = "06", required = true)
	private PosBrandEnum brand;

	@ApiModelProperty(value = "型号:VX675(0601), VX680(0602), A920(0105)", example = "0602", required = true)
	private PosVersionEnum version;

	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}

	public String getPosDeviceId() {
		return posDeviceId;
	}

	public void setPosDeviceId(String posDeviceId) {
		this.posDeviceId = posDeviceId;
	}

	public PosType getPosType() {
		return posType;
	}

	public void setPosType(PosType posType) {
		this.posType = posType;
	}

	public PosBrandEnum getBrand() {
		return brand;
	}

	public void setBrand(PosBrandEnum brand) {
		this.brand = brand;
	}

	public PosVersionEnum getVersion() {
		return version;
	}

	public void setVersion(PosVersionEnum version) {
		this.version = version;
	}
}
