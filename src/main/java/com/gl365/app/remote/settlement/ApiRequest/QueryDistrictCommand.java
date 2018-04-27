package com.gl365.app.remote.settlement.ApiRequest;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/8.
 */
public class QueryDistrictCommand {
    private String ownerType;
    private String ownerId;
    
    private String  parentAgentType;//收益机构类型 String 2 否 
    private String parentAgentNo;// 收益机构 String 32 否 

    private List<String> queryData;

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getQueryData() {
        return queryData;
    }

    public void setQueryData(List<String> queryData) {
        this.queryData = queryData;
    }

	public String getParentAgentType() {
		return parentAgentType;
	}

	public void setParentAgentType(String parentAgentType) {
		this.parentAgentType = parentAgentType;
	}

	public String getParentAgentNo() {
		return parentAgentNo;
	}

	public void setParentAgentNo(String parentAgentNo) {
		this.parentAgentNo = parentAgentNo;
	}
}
