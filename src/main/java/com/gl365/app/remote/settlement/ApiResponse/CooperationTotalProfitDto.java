package com.gl365.app.remote.settlement.ApiResponse;

import java.math.BigDecimal;

/**
 * Created by wangmeihua on 2017/6/15.
 */
public class CooperationTotalProfitDto {
    private BigDecimal totalProfitFee;
    private Long totalCount;

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
