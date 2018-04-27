package com.gl365.app.remote.settlement.ApiResponse;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/10.
 */
public class BanksDto {

    @ApiModelProperty("返回码")
    private String resultCode;

    @ApiModelProperty("返回描述")
    private String getResultCodeDesc;

    @ApiModelProperty("总记录数")
    private int totalNum;

    @ApiModelProperty("银行基本")
    private List<BankListDto> data;


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getGetResultCodeDesc() {
        return getResultCodeDesc;
    }

    public void setGetResultCodeDesc(String getResultCodeDesc) {
        this.getResultCodeDesc = getResultCodeDesc;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<BankListDto> getData() {
        return data;
    }

    public void setData(List<BankListDto> data) {
        this.data = data;
    }
}
