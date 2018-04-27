package com.gl365.app.remote.withdraw.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class VoucherListDto extends BaseDto{

    @ApiModelProperty("提现申请列表")
    private List<VoucherDto> data;

    public List<VoucherDto> getData() {
        return data;
    }

    public void setData(List<VoucherDto> data) {
        this.data = data;
    }
}
