package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/13.
 */
public class FindAgentApplicationsListDto extends PageInfoDto{

    private List<ApplicationSimple> list;
    @ApiModelProperty("数据列表")
    public List<ApplicationSimple> getList() {
        return list;
    }

    public void setList(List<ApplicationSimple> list) {
        this.list = list;
    }
}
