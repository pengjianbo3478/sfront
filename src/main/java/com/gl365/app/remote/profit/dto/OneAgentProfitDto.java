package com.gl365.app.remote.profit.dto;

import com.gl365.app.remote.withdraw.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
public class OneAgentProfitDto extends BaseDto {

    @ApiModelProperty("总记录数")
    private int totalNum;

    @ApiModelProperty("业务员分润设置")
    private AgentProfitDto data;


    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public AgentProfitDto getData() {
        return data;
    }

    public void setData(AgentProfitDto data) {
        this.data = data;
    }
}
