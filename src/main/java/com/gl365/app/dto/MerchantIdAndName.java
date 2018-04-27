package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/13.
 */
public class MerchantIdAndName {

    @ApiModelProperty("商户Id")
    private String id;

    @ApiModelProperty("商户名称")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
