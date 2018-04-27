package com.gl365.app.dto.command;

import java.util.List;

import com.gl365.app.dto.MerchantApplication;
import com.gl365.app.dto.MerchantHistory;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by caoyilong on 2017/5/31.
 */
public class MerchantAddPosCommand {


	private MerchantApplication merchantApplication;

	private MerchantApplicationCommand applicationDetail;

	private MerchantHistory merchantHistory;
	
	private List<MerchantHistory> merchantHistoryList;
	
	private List<PosCommand> commands;

	private String agentId;
	
	private String reapplyId;//重新申请Id
	
	//连锁;1统一结算连锁;2单独结算连锁
	private int linkType;

	@ApiModelProperty(value = "申请详情", hidden = true)
	public MerchantApplication getMerchantApplication() {
		return merchantApplication;
	}

	public void setMerchantApplication(MerchantApplication merchantApplication) {
		this.merchantApplication = merchantApplication;
	}

	@ApiModelProperty("申请详情")
	public MerchantApplicationCommand getApplicationDetail() {
		return applicationDetail;
	}

	public void setApplicationDetail(MerchantApplicationCommand applicationDetail) {
		this.applicationDetail = applicationDetail;
	}

	@ApiModelProperty("商户信息")
	public MerchantHistory getMerchantHistory() {
		return merchantHistory;
	}

	public void setMerchantHistory(MerchantHistory merchantHistory) {
		this.merchantHistory = merchantHistory;
	}

	@ApiModelProperty(hidden = true)
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	@ApiModelProperty(value = "POS机详情")
	public List<PosCommand> getCommands() {
		return commands;
	}

	public void setCommands(List<PosCommand> commands) {
		this.commands = commands;
	}

	@ApiModelProperty(value = "重新申请id")
	public String getReapplyId() {
		return reapplyId;
	}

	public void setReapplyId(String reapplyId) {
		this.reapplyId = reapplyId;
	}

	public List<MerchantHistory> getMerchantHistoryList() {
		return merchantHistoryList;
	}

	public void setMerchantHistoryList(List<MerchantHistory> merchantHistoryList) {
		this.merchantHistoryList = merchantHistoryList;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}
}
