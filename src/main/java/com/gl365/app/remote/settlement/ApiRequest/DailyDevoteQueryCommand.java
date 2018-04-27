package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.enum_type.AgentType;

import java.time.LocalDate;

/**
 * 查询机构所得贡献日报
 */
public class DailyDevoteQueryCommand {

	private AgentType ownerType;

	private String ownerId;

	/**
	 * 贡献类型(可以为空)
	 */
	private String type;

	private LocalDate fromDate;

	private LocalDate toDate;

	public AgentType getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(AgentType ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
}
