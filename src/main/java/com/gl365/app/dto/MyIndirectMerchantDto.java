package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/17.
 */
public class MyIndirectMerchantDto extends PageInfoDto {
    private List<Merchant> list;

    public List<Merchant> getList() {
        return list;
    }

    public void setList(List<Merchant> list) {
        this.list = list;
    }
}
