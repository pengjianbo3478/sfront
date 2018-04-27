package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/13.
 */
public class MerchantSnAndName {

    @ApiModelProperty("商户号")
    private String name;

    @ApiModelProperty("商户名称")
    private String merchantSn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantSn() {
        return merchantSn;
    }

    public void setMerchantSn(String merchantSn) {
        this.merchantSn = merchantSn;
    }
}
