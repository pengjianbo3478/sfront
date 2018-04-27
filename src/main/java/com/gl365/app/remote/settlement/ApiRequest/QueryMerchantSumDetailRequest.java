package com.gl365.app.remote.settlement.ApiRequest;

/**
 * Created by wangmeihua on 2017/6/10.
 */
public class QueryMerchantSumDetailRequest {
	
	private String ownerType;
	private String ownerId;

    private String agentNo;
    
    private String merchantNo;
    private String settleFlag;//Y已清算  N  未清算
    
    private String districtAgentNo;
    
    private String  directAgentNo;
    /**
     * 每页记录数
     */
    private int numPerPage;
    /**
     * 页码
     */
    private int pageNum;

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

	public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

	public String getSettleFlag() {
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}

	public String getDistrictAgentNo() {
		return districtAgentNo;
	}

	public void setDistrictAgentNo(String districtAgentNo) {
		this.districtAgentNo = districtAgentNo;
	}

	public String getDirectAgentNo() {
		return directAgentNo;
	}

	public void setDirectAgentNo(String directAgentNo) {
		this.directAgentNo = directAgentNo;
	}
	
	
}
