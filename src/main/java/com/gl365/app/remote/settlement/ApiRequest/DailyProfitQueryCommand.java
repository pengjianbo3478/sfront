package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.enum_type.AgentType;

import java.time.LocalDate;

/**
 * @author Chen, Zhuhui
 */
public class DailyProfitQueryCommand {

    private LocalDate fromDate;

    private LocalDate toDate;

    private AgentType ownerType;

    private String ownerId;

    private Integer numPerPage;

    private Integer pageNum;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public AgentType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(AgentType ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
