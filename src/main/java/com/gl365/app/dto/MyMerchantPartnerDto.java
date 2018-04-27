package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/17.
 */
public class MyMerchantPartnerDto extends PageInfoDto {
    private List<Merchant> list;
    @ApiModelProperty(value = "数据列表")
    public List<Merchant> getList() {
        return list;
    }

    public void setList(List<Merchant> list) {
        this.list = list;
    }
}
