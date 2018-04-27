package com.gl365.app.remote.profit.dto;

import com.gl365.app.remote.withdraw.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class OneProfitConfigDto extends BaseDto {

    @ApiModelProperty("提成方案")
    private ProfitConfigDto data;

    public ProfitConfigDto getData() {
        return data;
    }

    public void setData(ProfitConfigDto data) {
        this.data = data;
    }
}
