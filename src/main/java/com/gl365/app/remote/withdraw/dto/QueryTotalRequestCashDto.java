package com.gl365.app.remote.withdraw.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class QueryTotalRequestCashDto extends BaseDto {

    @ApiModelProperty("累计提现金额")
    private  BigDecimal[] data;
    
    private Integer totalNum;

	public BigDecimal[] getData() {
		return data;
	}

	public void setData(BigDecimal[] data) {
		this.data = data;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
