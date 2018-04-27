package com.gl365.app.remote.settlement.ApiResponse;

import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/9.
 */
public class QueryConUserSumByAgentResponse {
    private String userId;
    private String userName;
    private Long totalCount;
    private Long txnCount;
    private BigDecimal txnAmount;
    private BigDecimal totalProfitFee;

    @ApiOperation(value = "用户ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @ApiOperation(value = "用户姓名")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
	
    @ApiOperation(value = "总的交易金额")
    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }
    @ApiOperation(value = "累积贡献")
    public BigDecimal getTotalProfitFee() {
        return totalProfitFee;
    }

    public void setTotalProfitFee(BigDecimal totalProfitFee) {
        this.totalProfitFee = totalProfitFee;
    }
}
