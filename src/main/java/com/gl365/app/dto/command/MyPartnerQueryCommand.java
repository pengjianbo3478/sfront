package com.gl365.app.dto.command;

import com.gl365.app.constraints.NonSpecialCharacters;
import com.gl365.app.dto.enum_type.AgentType;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/19.
 */
public class MyPartnerQueryCommand {

    private String agentId;

    private AgentType agentType;

    @NonSpecialCharacters
    private String keyword;
    
    private boolean crossAgent = false;

    private boolean queryCounty = false;

    private boolean queryMerchant = false;

    private boolean queryPersonal = false;
    
    private boolean queryzjPersonl = false;

    @ApiModelProperty(value = "要搜索代理/业务员id",hidden = true)
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @ApiModelProperty(value = "要搜索的代理/业务员类型",hidden = true)
    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }

    @ApiModelProperty(value = "搜索关键字，只搜索县级代理商名字/公司名、业务员名字/公司名、商户名字",required = false)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @ApiModelProperty(value = "是否查询县级代理商，注意只有市级代理能查询",required = false, hidden = true)
    public boolean isQueryCounty() {
        return queryCounty;
    }

    public void setQueryCounty(boolean queryCounty) {
        this.queryCounty = queryCounty;
    }

    @ApiModelProperty(value = "是否查询商户",required = false, hidden = true)
    public boolean isQueryMerchant() {
        return queryMerchant;
    }

    public void setQueryMerchant(boolean queryMerchant) {
        this.queryMerchant = queryMerchant;
    }

    @ApiModelProperty(value = "是否查询业务员",required = false, hidden = true)
    public boolean isQueryPersonal() {
        return queryPersonal;
    }

    public void setQueryPersonal(boolean queryPersonal) {
        this.queryPersonal = queryPersonal;
    }

    @ApiModelProperty(value = "直接业务",required = false, hidden = true)
	public boolean isQueryzjPersonl() {
		return queryzjPersonl;
	}

	public void setQueryzjPersonl(boolean queryzjPersonl) {
		this.queryzjPersonl = queryzjPersonl;
	}
	
	@ApiModelProperty(value = "true:跨区域")
	public boolean isCrossAgent() {
		return crossAgent;
	}

	public void setCrossAgent(boolean crossAgent) {
		this.crossAgent = crossAgent;
	}
}
