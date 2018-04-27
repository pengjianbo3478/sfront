package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class MonthSettlementListDto extends PageInfoDto {
    private List<MonthSettlement> list;

    @ApiModelProperty(value = "数据列表")
    public List<MonthSettlement> getList() {
        return list;
    }

    public void setList(List<MonthSettlement> list) {
        this.list = list;
    }
}
