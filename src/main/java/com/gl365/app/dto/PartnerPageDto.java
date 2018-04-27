package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by ryan on 2017/6/20.
 */
public class PartnerPageDto extends PageInfoDto {
    private List<PartnerDto> list;
    @ApiModelProperty("数据列表")
    public List<PartnerDto> getList() {
        return list;
    }

    public void setList(List<PartnerDto> list) {
        this.list = list;
    }
}
