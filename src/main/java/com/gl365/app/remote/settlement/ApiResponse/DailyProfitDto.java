package com.gl365.app.remote.settlement.ApiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.AgentType;

import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Chen, Zhuhui
 */
public class DailyProfitDto {

    private LocalDate settleDate;

    private AgentType ownerType;

    private String ownerId;

    private Long totalCount;
    private Long txnCount;

    private BigDecimal txnAmount;

    private BigDecimal totalMarcketFee;

    private BigDecimal totalProfitFee;

    private BigDecimal profitMarcketFee;

    private BigDecimal profitPayFee;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

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

    @ApiOperation(value = "总的交易笔数")
    public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		if(totalCount != null){
			this.txnCount = totalCount;
		}
	}
	
	@ApiOperation(value = "总的交易笔数")
	public Long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
		if(txnCount != null){
			this.totalCount = txnCount;
		}
	}
	

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public BigDecimal getTotalMarcketFee() {
        return totalMarcketFee;
    }

    public void setTotalMarcketFee(BigDecimal totalMarcketFee) {
        this.totalMarcketFee = totalMarcketFee;
    }

    public BigDecimal getTotalProfitFee() {
        return totalProfitFee;
    }

    public void setTotalProfitFee(BigDecimal totalProfitFee) {
        this.totalProfitFee = totalProfitFee;
    }

    public BigDecimal getProfitMarcketFee() {
        return profitMarcketFee;
    }

    public void setProfitMarcketFee(BigDecimal profitMarcketFee) {
        this.profitMarcketFee = profitMarcketFee;
    }

    public BigDecimal getProfitPayFee() {
        return profitPayFee;
    }

    public void setProfitPayFee(BigDecimal profitPayFee) {
        this.profitPayFee = profitPayFee;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
