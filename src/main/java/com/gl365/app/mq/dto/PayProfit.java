package com.gl365.app.mq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.GsonUtils;

/**
 * 积分联付提成明细实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017年-4月-8日 创建
 */
public class PayProfit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	private long id;

	/**
	 * 交易单号
	 */
	private String payId;
	
	/** 原交易流水号 */
	private String origPayId;

	/**
	 * 给乐商户号
	 */
	private String merchantNo;

	/**
	 * 支付时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;

	/**
	 * 提成大类 1：营销费提成 2：支付手续费提成 3：首笔交易奖励 4：收款奖励 5：打赏奖励
	 */
	private String parentProfitType;
	/**
	 * 提成分类： 00支付手续费提成（不参与营销费提成） 01给乐平台提成 02直接发展商户提成 03发展商户提成(市级) 04发展商户提成(县级)
	 * 05发展商户提成(推广员) 06发展会员提成(机构) 07发展会员提成(员工) 08发展会员提成(店长) 09代理城市服务费提成
	 * 10代理城市服务费成(市级间接) 11服务公司服务费提成
	 */
	private String profitType;

	/**
	 * 提成对象
	 */
	private String ownerId;

	/**
	 * 提成对象类型 1：省级运营商 2：市级运营商 3：县级运营商 4：业务员机构 5：联盟商家商户 6：员工,店长,会员 7：企业协会机构
	 * 8：积分机构 9：电子商城 10：积分商城 11：政府机构 12：银行机构 13：支付公司
	 */
	private String ownerType;

	/**
	 * 单据类型 0：对应于消费确认、网上消费、预授权完成确认 1：对应于退货
	 */
	private String saleType;

	/** 直接业务员(二级业务员) */
	private String directAgentNo;

	/** 直接业务员所属推广业务员（如果为空，说明直接业务员直属市县代） */
	private String inviteAgentNo;

	/**
	 * 是否商家直接发展会员 Y：员工、店长为空时 N：员工、店长任一不为空时
	 */
	private String directUserFlag;

	/** 发展商户机构（非代理商） */
	private String organAgentNo;
	
	/** 省代 */
	private String provinceAgentNo;
	
	/** 市代 */
	private String cityAgentNo;
	
	/** 在“10代理城市服务费成(市级间接)”的情况下，记录是哪个县代做的贡献 */
	private String districtAgentNo;

	/** 用户ID */
	private String userId;

	/** 用户姓名 */
	private String userName;

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
	 * 是否有效， N：无效 Y：有效
	 */
	private String validFlag;

	/**
	 * 清算状态 Y：已清算 N：待清算
	 */
	private String settleFlag;

	/**
	 * 清算日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate settleDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;
	
	/**
	 * 服务费类型: 1：县级 2:商户  3:业务员  4:用户	 
	 */
	//private String serviceFeeType;
	
	/**
	 * 商户名称	 
	 */
	private String merchantName;
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayId() {
		return this.payId;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantNo() {
		return this.merchantNo;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getProfitType() {
		return this.profitType;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerType() {
		return this.ownerType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleType() {
		return this.saleType;
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

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public BigDecimal getTxnAmount() {
		return this.txnAmount;
	}

	public void setMarcketFee(BigDecimal marcketFee) {
		this.marcketFee = marcketFee;
	}

	public BigDecimal getMarcketFee() {
		return this.marcketFee;
	}

	public void setBaseProfitRate(BigDecimal baseProfitRate) {
		this.baseProfitRate = baseProfitRate;
	}

	public BigDecimal getBaseProfitRate() {
		return this.baseProfitRate;
	}

	public void setBaseProfitAmount(BigDecimal baseProfitAmount) {
		this.baseProfitAmount = baseProfitAmount;
	}

	public BigDecimal getBaseProfitAmount() {
		return this.baseProfitAmount;
	}

	public void setOwnerProfitRate(BigDecimal ownerProfitRate) {
		this.ownerProfitRate = ownerProfitRate;
	}

	public BigDecimal getOwnerProfitRate() {
		return this.ownerProfitRate;
	}

	public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
		this.ownerProfitAmount = ownerProfitAmount;
	}

	public BigDecimal getOwnerProfitAmount() {
		return this.ownerProfitAmount;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}

	public String getSettleFlag() {
		return this.settleFlag;
	}

	public void setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
	}

	public LocalDate getSettleDate() {
		return this.settleDate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return this.modifyTime;
	}

	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public String getParentProfitType() {
		return parentProfitType;
	}

	public void setParentProfitType(String parentProfitType) {
		this.parentProfitType = parentProfitType;
	}
	
	public String getOrganAgentNo() {
		return organAgentNo;
	}

	public void setOrganAgentNo(String organAgentNo) {
		this.organAgentNo = organAgentNo;
	}

	public String getProvinceAgentNo() {
		return provinceAgentNo;
	}

	public void setProvinceAgentNo(String provinceAgentNo) {
		this.provinceAgentNo = provinceAgentNo;
	}

	public String getCityAgentNo() {
		return cityAgentNo;
	}

	public void setCityAgentNo(String cityAgentNo) {
		this.cityAgentNo = cityAgentNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getOrigPayId() {
		return origPayId;
	}

	public void setOrigPayId(String origPayId) {
		this.origPayId = origPayId;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}