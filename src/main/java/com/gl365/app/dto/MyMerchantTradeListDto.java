package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class MyMerchantTradeListDto extends PageInfoDto{
    List<MyMerchantTradeList> data;

    @ApiModelProperty(value = "数据列表")
    public List<MyMerchantTradeList> getData() {
        return data;
    }

    public void setData(List<MyMerchantTradeList> data) {
        this.data = data;
    }
}
