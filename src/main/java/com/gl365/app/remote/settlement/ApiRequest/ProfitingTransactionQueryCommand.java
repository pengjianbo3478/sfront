package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.enum_type.AgentType;

import java.time.LocalDate;

/**
 * @author Chen, Zhuhui
 */
public class ProfitingTransactionQueryCommand {

    private AgentType ownerType;

    private String ownerId;

    private String merchantName;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer numPerPage;

    private Integer pageNum;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

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
