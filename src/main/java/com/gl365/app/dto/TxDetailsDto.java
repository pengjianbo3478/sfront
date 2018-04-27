package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Chen, Zhuhui
 */
public class TxDetailsDto {

    private String merchantName;

    private String merchantSn;

    private String posDeviceId;

    private String bankCardNo;

    private String bankName;

    private LocalDateTime transactionTime;

    private BigDecimal amount;

    private String agentName;

    private BigDecimal profitCountyAgent = BigDecimal.ZERO;

    private BigDecimal feeCountyAgent = BigDecimal.ZERO;

    private BigDecimal profitPersonalAgent = BigDecimal.ZERO;

    private BigDecimal feePersonalAgent = BigDecimal.ZERO;

    private BigDecimal profitOfMine = BigDecimal.ZERO;

    private BigDecimal feeOfMine = BigDecimal.ZERO;

    /**
     * 返回消费商户名称
     */
    @ApiModelProperty(value = "消费商户名称", required = true)
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 返回商户SN（给乐公司商户SN）
     */
    @ApiModelProperty(value = "商户SN（给乐SN）", required = true)
    public String getMerchantSn() {
        return merchantSn;
    }

    public void setMerchantSn(String merchantSn) {
        this.merchantSn = merchantSn;
    }

    /**
     * 返回终端号
     */
    @ApiModelProperty(value = "终端号", required = true)
    public String getPosDeviceId() {
        return posDeviceId;
    }

    public void setPosDeviceId(String posDeviceId) {
        this.posDeviceId = posDeviceId;
    }

    /**
     * 返回交易银行卡卡号
     */
    @ApiModelProperty(value = "交易银行卡卡号", required = true)
    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * 返回交易银行名
     */
    @ApiModelProperty(value = "交易银行名", required = true)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 返回交易时间
     */
    @ApiModelProperty(value = "交易时间", required = true)
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * 返回交易金额
     */
    @ApiModelProperty(value = "交易金额", required = true)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 返回所属代理商名称
     */
    @ApiModelProperty(value = "所属代理商名称", required = true)
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 返回县级代理商收益。当交易不涉及县级代理商分润时，不返回此字段
     */
    @ApiModelProperty(value = "县级代理商收益。当交易不涉及县级代理商分润时，不返回此字段")
    public BigDecimal getProfitCountyAgent() {
        return profitCountyAgent;
    }

    public void setProfitCountyAgent(BigDecimal profitCountyAgent) {
        this.profitCountyAgent = profitCountyAgent;
    }

    /**
     * 返回县级代理商服务费
     */
    @ApiModelProperty(value = "县级代理商服务费")
    public BigDecimal getFeeCountyAgent() {
        return feeCountyAgent;
    }

    public void setFeeCountyAgent(BigDecimal feeCountyAgent) {
        this.feeCountyAgent = feeCountyAgent;
    }

    /**
     * 返回业务员收益
     */
    @ApiModelProperty(value = "业务员收益")
    public BigDecimal getProfitPersonalAgent() {
        return profitPersonalAgent;
    }

    public void setProfitPersonalAgent(BigDecimal profitPersonalAgent) {
        this.profitPersonalAgent = profitPersonalAgent;
    }

    /**
     * 返回业务员发展商户费
     */
    @ApiModelProperty(value = "业务员发展商户费")
    public BigDecimal getFeePersonalAgent() {
        return feePersonalAgent;
    }

    public void setFeePersonalAgent(BigDecimal feePersonalAgent) {
        this.feePersonalAgent = feePersonalAgent;
    }

    /**
     * 返回当前登录代理商的分润
     */
    @ApiModelProperty(value = "当前登录代理商的分润", required = true)
    public BigDecimal getProfitOfMine() {
        return profitOfMine;
    }

    public void setProfitOfMine(BigDecimal profitOfMine) {
        this.profitOfMine = profitOfMine;
    }

    /**
     * 返回当前登录代理商的服务费/发展商户费
     */
    @ApiModelProperty(value = "当前登录代理商的服务费/发展商户费", required = true)
    public BigDecimal getFeeOfMine() {
        return feeOfMine;
    }

    public void setFeeOfMine(BigDecimal feeOfMine) {
        this.feeOfMine = feeOfMine;
    }
}
