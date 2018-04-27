package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/12.
 */
public class MyPosesDto extends PageInfoDto{

    @ApiModelProperty("数据列表")
    private List<MyPosDto> list;

    public List<MyPosDto> getList() {
        return list;
    }

    public void setList(List<MyPosDto> list) {
        this.list = list;
    }
}
