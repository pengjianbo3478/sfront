package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/17.Info
 */
public class MyAgentPartnerDto extends PageInfoDto {
    private List<Agent> list;
    @ApiModelProperty("数据列表")
    public List<Agent> getList() {
        return list;
    }

    public void setList(List<Agent> list) {
        this.list = list;
    }
}
