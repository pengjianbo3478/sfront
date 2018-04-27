package com.gl365.app.remote.settlement.ApiResponse;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/9.
 */
public class QueryDistrictResponse {
    private String agentType;
    private String agentNo;
    private Long totalCount;
    private Long txnCount;
    private BigDecimal totalProfitFee;
    private BigDecimal txnAmount;

    @ApiModelProperty(value = "提成对象类型")
    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }
    @ApiModelProperty(value = "代理商id")
    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }
    
    public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		if(totalCount != null){
			this.txnCount = totalCount;
		}
	}

	@ApiModelProperty(value = "总的交易笔数")
	public Long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
		if(txnCount != null){
			this.totalCount = txnCount;
		}
	}
	
    @ApiModelProperty(value = "累积贡献")
    public BigDecimal getTotalProfitFee() {
        return totalProfitFee;
    }

    public void setTotalProfitFee(BigDecimal totalProfitFee) {
        this.totalProfitFee = totalProfitFee;
    }
    @ApiModelProperty(value = "总的交易金额")
    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }
}
