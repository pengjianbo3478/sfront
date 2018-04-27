package com.gl365.app.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 贡献相关
 */
public class DevoteDto implements Serializable {

	private static final long serialVersionUID = 5685843816532238382L;

	private String countyDevote;

	private String personalDevote;

	private String userDevote;

	private String merchantDevote;
	
	private String otherDevote;

	@ApiModelProperty("县代贡献")
	public String getCountyDevote() {
		return countyDevote;
	}

	public void setCountyDevote(String countyDevote) {
		this.countyDevote = countyDevote;
	}

	@ApiModelProperty("业务员贡献")
	public String getPersonalDevote() {
		return personalDevote;
	}

	public void setPersonalDevote(String personalDevote) {
		this.personalDevote = personalDevote;
	}

	@ApiModelProperty("用户贡献")
	public String getUserDevote() {
		return userDevote;
	}

	public void setUserDevote(String userDevote) {
		this.userDevote = userDevote;
	}

	@ApiModelProperty("商户贡献")
	public String getMerchantDevote() {
		return merchantDevote;
	}

	public void setMerchantDevote(String merchantDevote) {
		this.merchantDevote = merchantDevote;
	}

	@ApiModelProperty("其它贡献")
	public String getOtherDevote() {
		return otherDevote;
	}

	public void setOtherDevote(String otherDevote) {
		this.otherDevote = otherDevote;
	}
}

