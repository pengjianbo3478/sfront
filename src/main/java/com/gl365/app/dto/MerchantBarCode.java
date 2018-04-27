package com.gl365.app.dto;

import java.util.Date;

public class MerchantBarCode extends MerchantBarCodeKey {
    private String tMerchantId;
    
    private Integer status;

    private Date createTime;

    private Date modifyTime;
    
    public MerchantBarCode(){
    	
    }
    public MerchantBarCode(String tMerchantId,Integer status,String applicationId){
    	this.tMerchantId = tMerchantId;
    	this.status = status;
    	setApplicationId(applicationId);
    }

    public String gettMerchantId() {
        return tMerchantId;
    }

    public void settMerchantId(String tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
}