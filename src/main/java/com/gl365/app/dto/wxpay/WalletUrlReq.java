package com.gl365.app.dto.wxpay;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class WalletUrlReq extends BaseEntity {
	private static final long serialVersionUID = -476642436947346477L;

	@Length(max = 32, message = "接口流水号不能大于32")
	@ApiModelProperty(value = "接口流水号", hidden = true, example = "13213434")
	private String pono;

	@ApiModelProperty(value = "用户Id", hidden = true, example = "68e971158a6142f49f1cc7f67968dbeb")
	private String puserNo;
	
	@ApiModelProperty(value = "用户类型:2：市级运营商3：县级运营商4：业务员机构5：联盟商家商户6：员工,店长,会员7：企业协会机构", hidden = true)
	private String puserType;

	@ApiModelProperty(value = "接入类型：1：PC，2：WAP，3：APP", hidden = true, example = "3")
	private String accessType = "3";
	
	@ApiModelProperty(value = "开户渠道", hidden = true, example = "10003")
	private String openChannel = "10003";
	
	@ApiModelProperty(value = "商家：0：结算款; 其它：1：分润款，默认值", hidden = true, example = "1")
	private String accType = "1";
	
	@ApiModelProperty(hidden = true)
	private String openId;
	
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public String getPuserNo() {
		return puserNo;
	}

	public void setPuserNo(String puserNo) {
		this.puserNo = puserNo;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getOpenChannel() {
		return openChannel;
	}

	public void setOpenChannel(String openChannel) {
		this.openChannel = openChannel;
	}

	public String getPuserType() {
		return puserType;
	}

	public void setPuserType(String puserType) {
		this.puserType = puserType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
