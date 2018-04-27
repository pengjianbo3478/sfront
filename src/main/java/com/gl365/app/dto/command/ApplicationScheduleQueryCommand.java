package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by wangmeihua on 2017/6/5.
 */
public class ApplicationScheduleQueryCommand {

    private Boolean team;
    private int status = 0;
    private String keyword;
    private String linkMerchantId;

    @ApiModelProperty(value = "前端对应：全部：0；待审核：1；审核中：2；通过：3；拒绝：4；撤回：5。")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ApiModelProperty(value = "是否为团队", example = "true")
    public Boolean getTeam() {
        return team;
    }

    public void setTeam(Boolean team) {
        this.team = team;
    }

    @ApiModelProperty(value = "搜索关键字", example = "xx公司名称")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

	public String getLinkMerchantId() {
		return linkMerchantId;
	}

	public void setLinkMerchantId(String linkMerchantId) {
		this.linkMerchantId = linkMerchantId;
	}
    
    
}
