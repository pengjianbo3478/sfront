package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class SubAgentTradeListDto extends PageInfoDto{
    List<SubAgentTradeDetailDto> data ;

    @ApiModelProperty(value = "数据列表")
    public List<SubAgentTradeDetailDto> getData() {
        return data;
    }

    public void setData(List<SubAgentTradeDetailDto> data) {
        this.data = data;
    }
}
