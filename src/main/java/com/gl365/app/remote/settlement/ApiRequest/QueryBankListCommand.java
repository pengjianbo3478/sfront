package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.command.BaseCommand;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/10.
 */
public class QueryBankListCommand extends BaseCommand{

    @ApiModelProperty("银行卡号")
    private String bankAccountNo;

    @ApiModelProperty("银行卡户名")
    private String bankAccountName;

    @ApiModelProperty("开户支行银行名称")
    private String bankName;

    @ApiModelProperty("每页记录数")
    private String numPerPage;

    @ApiModelProperty("页码")
    private String pageNum;


    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(String numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
