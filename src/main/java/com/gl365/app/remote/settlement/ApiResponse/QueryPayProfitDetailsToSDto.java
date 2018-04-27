package com.gl365.app.remote.settlement.ApiResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by wangmeihua on 2017/6/22.
 */
public class QueryPayProfitDetailsToSDto {
    private String payId;
    private String merchantNo;
    private String merchantName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;
    private String parentProfitType;
    private String profitType;
    private String ownerType;
    private String ownerId;
    private String saleType;
    private BigDecimal txnAmount;
    private BigDecimal marcketFee;
    private BigDecimal baseProfitRate;
    private BigDecimal baseProfitAmount;
    private BigDecimal ownerProfitRate;
    private BigDecimal ownerProfitAmount;
    private String validFlag;
    private String settleFlag;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settleDate;

    private String remark ;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;
    private String organ_agent_no;
    private String province_agent_no;
    private String city_agent_no;
    private String directAgentNo;
    private String inviteAgentNo;
    private String directUserFlag;
    private String districtAgentNo;
    private String userId;
    private String userName;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
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

    public String getDistrictAgentNo() {
        return districtAgentNo;
    }

    public void setDistrictAgentNo(String districtAgentNo) {
        this.districtAgentNo = districtAgentNo;
    }
}
