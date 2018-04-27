package com.gl365.app.remote.settlement.ApiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.AgentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Chen, Zhuhui
 */
public class AgentProfitSumDto {

    private AgentType ownerType;

    private String ownerId;

    private BigDecimal totalProfitFee;

    private BigDecimal profitMarcketFee;

    private BigDecimal profitPayFee;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

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
