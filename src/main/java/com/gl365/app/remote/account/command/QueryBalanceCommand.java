package com.gl365.app.remote.account.command;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
public class QueryBalanceCommand {

    private String accountId;

    private String accountType;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
