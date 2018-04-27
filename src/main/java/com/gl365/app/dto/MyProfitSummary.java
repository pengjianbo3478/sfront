package com.gl365.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import com.gl365.app.utils.BigDecimaluitl;

/**
 * @author Chen, Zhuhui
 */
@ApiModel(description = "昨日今月收益统计模型")
public class MyProfitSummary {

	private BigDecimal nowday;
	
    private BigDecimal yesterday;

    private BigDecimal total;

    @ApiModelProperty(value = "今日现在收益")
    public String getNowday() {
    	return BigDecimaluitl.setScaleStr(nowday);
	}

	public void setNowday(BigDecimal nowday) {
		this.nowday = nowday;
	}

	/**
     * 返回昨日收益
     */
    @ApiModelProperty(value = "昨日收益")
    public String getYesterday() {
        return BigDecimaluitl.setScaleStr(yesterday);
    }

    public void setYesterday(BigDecimal yesterday) {
        this.yesterday = yesterday;
    }

    /**
     * 返回累计收益
     */
    @ApiModelProperty(value = "累计收益", required = true)
    public String getTotal() {
        return BigDecimaluitl.setScaleStr(total);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
