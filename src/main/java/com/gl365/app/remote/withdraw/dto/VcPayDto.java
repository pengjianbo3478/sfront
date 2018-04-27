package com.gl365.app.remote.withdraw.dto;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/16.
 */
public class VcPayDto extends BaseDto {

    private int totalNum;//总记录数

    private List<VcPayDetailDto> data;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<VcPayDetailDto> getData() {
        return data;
    }

    public void setData(List<VcPayDetailDto> data) {
        this.data = data;
    }
}
