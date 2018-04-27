package com.gl365.app.remote.settlement.ApiRequest;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * Created by wangmeihua on 2017/6/18.
 */
public class QueryVcAgentMonthCommand {
    private String queryType;
    private String ownerType;
    private String ownerId;
    private String voucherId;
    private String state;
    private String fromMonth;
    private String toMonth;
    private int numPerPage;
    private int getNumPerPage;

    @ApiModelProperty(value = "查询类型")
    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @ApiModelProperty(value = "提成对象类型")
    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
    @ApiModelProperty(value = "提成对象")
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @ApiModelProperty(value = "申请单号")
    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
    @ApiModelProperty(value = "状态")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @ApiModelProperty(value = "开始月份")
    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }
    @ApiModelProperty(value = "结束月份")
    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }


    @ApiModelProperty(value = "每页记录数")
    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
    @ApiModelProperty(value = "页码")
    public int getGetNumPerPage() {
        return getNumPerPage;
    }

    public void setGetNumPerPage(int getNumPerPage) {
        this.getNumPerPage = getNumPerPage;
    }
}
