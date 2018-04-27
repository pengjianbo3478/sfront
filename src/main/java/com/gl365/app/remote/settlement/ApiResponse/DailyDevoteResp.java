package com.gl365.app.remote.settlement.ApiResponse;

import com.gl365.app.dto.enum_type.AgentType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 查询机构所得贡献日报
 */
public class DailyDevoteResp implements Serializable {

	private static final long serialVersionUID = -1814210133147427386L;

	/**
	 * 清算日期
	 */
	private LocalDate settleDate;

	/**
	 * 提成对象类型
	 */
	private AgentType ownerType;

	/**
	 * 提成对象
	 */
	private String ownerId;
	/**
	 * 贡献类型
	 **/
	private String type;
	/**
	 * 对提成对象的提成贡献
	 */
	private BigDecimal totalProfitFee;
	/**
	 * 累积提成笔数
	 */
	private Long totalCount;

	public LocalDate getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
	}

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

	public BigDecimal getTotalProfitFee() {
		return totalProfitFee;
	}

	public void setTotalProfitFee(BigDecimal totalProfitFee) {
		this.totalProfitFee = totalProfitFee;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
