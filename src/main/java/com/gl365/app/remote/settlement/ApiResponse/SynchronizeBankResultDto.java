package com.gl365.app.remote.settlement.ApiResponse;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/10.
 */
public class SynchronizeBankResultDto {

    @ApiModelProperty("返回码")
    private String resultCode;

    @ApiModelProperty("返回描述")
    private String resultDesc;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
