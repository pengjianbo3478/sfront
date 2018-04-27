package com.gl365.app.remote.account.dto;

import com.gl365.app.remote.withdraw.dto.BaseDto;
import com.gl365.app.remote.withdraw.dto.CashDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class QueryBalanceDto extends BaseDto {

    @ApiModelProperty("data体")
    private CashDto resultData;

	public CashDto getResultData() {
		return resultData;
	}

	public void setResultData(CashDto resultData) {
		this.resultData = resultData;
	}

}
