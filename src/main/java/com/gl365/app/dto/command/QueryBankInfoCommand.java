package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/8.
 */
public class QueryBankInfoCommand {

    @ApiModelProperty(value = "当前页", required = true)
    private Integer curPage;

    @ApiModelProperty(value = "每页容量", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "区域id", required = true)
    private Integer areaId;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行编号", required = true)
    private String bankNo;
    
    @ApiModelProperty(value = "银行名称")
    private String keyword;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
		this.bankName = keyword;
	}
    
    
}
