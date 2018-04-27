package com.gl365.app.dto.command;

import com.gl365.app.dto.enum_type.InvitePartnerType;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by caoyilong on 2017/6/6.
 */
public class InvitePartnerCommand {


	private InvitePartnerType type;

	private Integer actingAreaId;

	@ApiModelProperty("邀请合伙人的类型，1：县级代理、2：业务员、3：用户")
	public InvitePartnerType getType() {
		return type;
	}

	public void setType(InvitePartnerType type) {
		this.type = type;
	}

	@ApiModelProperty("代理区域，仅仅限于邀请县代理")
	public Integer getActingAreaId() {
		return actingAreaId;
	}

	public void setActingAreaId(Integer actingAreaId) {
		this.actingAreaId = actingAreaId;
	}
}
