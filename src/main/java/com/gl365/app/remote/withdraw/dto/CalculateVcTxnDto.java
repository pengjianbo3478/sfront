package com.gl365.app.remote.withdraw.dto;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
public class CalculateVcTxnDto extends BaseDto{

    private DeductionDto data;

    public DeductionDto getData() {
        return data;
    }

    public void setData(DeductionDto data) {
        this.data = data;
    }
}
