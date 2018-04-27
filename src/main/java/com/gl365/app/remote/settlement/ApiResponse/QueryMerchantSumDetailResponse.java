package com.gl365.app.remote.settlement.ApiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wangmeihua on 2017/6/10.
 */
public class QueryMerchantSumDetailResponse {
	/**
	 * 流水号
	 */
	private Long id;
	/**
	 * 支付流水号
	 */
	private String payId;
	/**
	 * 给乐商户号
	 */
	private String merchantNo;
	/**
	 * 提成大类 1：营销费提成 2：支付手续费提成 3：首笔交易奖励 4：收款奖励 5：打赏奖励
	 */
	private String parentProfitType;
	/**
	 * 提成对象类型
	 */
	private String ownerType;
	/**
	 * 提成对象
	 */
	private String ownerId;
	/**
	 * 单据类型
	 */
	private String saleType;
	/**
	 * 提成分类
	 */
	private String profitType;
	/**
	 * 交易金额
	 */
	private BigDecimal txnAmount;
	/**
	 * 营销费
	 */
	private BigDecimal marcketFee;
	/**
	 * 基本提成率
	 */
	private BigDecimal baseProfitRate;
	/**
	 * 基本提成金额
	 */
	private BigDecimal baseProfitAmount;
	/**
	 * 对象提成率
	 */
	private BigDecimal ownerProfitRate;
	/**
	 * 对象提成金额
	 */
	private BigDecimal ownerProfitAmount;
	/**
	 * 是否有效
	 */
	private String validFlag;
	/**
	 * 清算状态
	 */
	private String settleFlag;
	/**
	 * 清算日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate settleDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;
	/**
	 * 发展商户机构（非代理商）
	 */
	private String organ_agent_no;
	/**
	 * 省代
	 */
	private String province_agent_no;
	/**
	 * 市代
	 */
	private String city_agent_no;
	/**
	 * 直接业务员
	 */
	private String directAgentNo;
	/**
	 * 直接业务员所属推广业务员
	 */
	private String inviteAgentNo;
	/**
	 * 是否商家直接发展会员
	 */
	private String directUserFlag;
	/**
	 * 县代
	 */
	private String districtAgentNo;
	/**
	 * 支付确认时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;
	/**
	 * 会员ID
	 */
	private String userId;
	/**
	 * 会员姓名
	 */
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getParentProfitType() {
		return parentProfitType;
	}

	public void setParentProfitType(String parentProfitType) {
		this.parentProfitType = parentProfitType;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public BigDecimal getTxnAmount() {
		if ("1".equals(saleType) && txnAmount != null) {
			return txnAmount.multiply(new BigDecimal(-1));
		}
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public BigDecimal getMarcketFee() {
		return marcketFee;
	}

	public void setMarcketFee(BigDecimal marcketFee) {
		this.marcketFee = marcketFee;
	}

	public BigDecimal getBaseProfitRate() {
		return baseProfitRate;
	}

	public void setBaseProfitRate(BigDecimal baseProfitRate) {
		this.baseProfitRate = baseProfitRate;
	}

	public BigDecimal getBaseProfitAmount() {
		return baseProfitAmount;
	}

	public void setBaseProfitAmount(BigDecimal baseProfitAmount) {
		this.baseProfitAmount = baseProfitAmount;
	}

	public BigDecimal getOwnerProfitRate() {
		return ownerProfitRate;
	}

	public void setOwnerProfitRate(BigDecimal ownerProfitRate) {
		this.ownerProfitRate = ownerProfitRate;
	}

	public BigDecimal getOwnerProfitAmount() {
		if ("1".equals(saleType) && ownerProfitAmount != null) {
			return ownerProfitAmount.multiply(new BigDecimal(-1));
		}
		return ownerProfitAmount;
	}

	public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
		this.ownerProfitAmount = ownerProfitAmount;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSettleFlag() {
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}

	public LocalDate getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrgan_agent_no() {
		return organ_agent_no;
	}

	public void setOrgan_agent_no(String organ_agent_no) {
		this.organ_agent_no = organ_agent_no;
	}

	public String getProvince_agent_no() {
		return province_agent_no;
	}

	public void setProvince_agent_no(String province_agent_no) {
		this.province_agent_no = province_agent_no;
	}

	public String getCity_agent_no() {
		return city_agent_no;
	}

	public void setCity_agent_no(String city_agent_no) {
		this.city_agent_no = city_agent_no;
	}

	public String getDirectAgentNo() {
		return directAgentNo;
	}

	public void setDirectAgentNo(String directAgentNo) {
		this.directAgentNo = directAgentNo;
	}

	public String getInviteAgentNo() {
		return inviteAgentNo;
	}

	public void setInviteAgentNo(String inviteAgentNo) {
		this.inviteAgentNo = inviteAgentNo;
	}

	public String getDirectUserFlag() {
		return directUserFlag;
	}

	public void setDirectUserFlag(String directUserFlag) {
		this.directUserFlag = directUserFlag;
	}

	public String getDistrictAgentNo() {
		return districtAgentNo;
	}

	public void setDistrictAgentNo(String districtAgentNo) {
		this.districtAgentNo = districtAgentNo;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
