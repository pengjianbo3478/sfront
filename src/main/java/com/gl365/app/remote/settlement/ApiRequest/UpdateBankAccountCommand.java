package com.gl365.app.remote.settlement.ApiRequest;

import com.gl365.app.dto.command.BaseCommand;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/8.
 */
public class UpdateBankAccountCommand extends BaseCommand{

    @ApiModelProperty(value = "银行总行名称", required = true)
    private String bankMainName;
    
    @ApiModelProperty(value = "银行卡号", required = true)
    private String bankAccountNo;

    @ApiModelProperty(value = "银行卡户名", required = true)
    private String bankAccountName;

    @ApiModelProperty(value = "开户支行银行名称", required = true)
    private String bankName;

    @ApiModelProperty(value = "开户支行银行号", required = true)
    private String bankNo;

    @ApiModelProperty(value = "账户类型.01-对公，02-对私", dataType = "java.lang.String", required = true)
    private String bankAccountType;

    @ApiModelProperty(value = "身份证", hidden = true)
    private String certNo;
    
    @ApiModelProperty(value = "手机号")
	private String mobile;

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @ApiModelProperty(value = "修改人", hidden = true)
    private String modifyBy;
    

    public String getBankMainName() {
		return bankMainName;
	}

	public void setBankMainName(String bankMainName) {
		this.bankMainName = bankMainName;
	}

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

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    
}
