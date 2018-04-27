package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/15.
 */
public class ApplicationStatusCountDto {

    private int status;
    private int count = 0;

    public ApplicationStatusCountDto(int status) {
        this.status = status;
    }

    @ApiModelProperty(value = "前端对应：待审核：1；审核中：2；通过：3；拒绝：4；撤回：5。")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ApiModelProperty(value = "数量")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
