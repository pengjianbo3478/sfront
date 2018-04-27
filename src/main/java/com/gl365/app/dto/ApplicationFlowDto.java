package com.gl365.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.ApplicationFlowStatus;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class ApplicationFlowDto {

	private ApplicationFlowStatus status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;

	/**
	 * 申请者名称
	 */
	private String agentName;

	/**
	 * 邀请者名称(申请人上级)
	 */
	private String upstreamAgentName;

	/**
	 * 申请者电话
	 */
	private String agentMobile;

	/**
	 * 商户号
	 */
	private String merchantSn;

	/**
	 * 共申请POS机数量
	 */
	private String count;

	/**
	 * 处理意见
	 */
	private String comments;

	/**
	 * 合伙人合同照片地址
	 */
	private String partnerContractPicUrl;
	
	private List<String> partnerContractPicUrls;

	/**
	 * POS详情
	 */
	private List<ApplicationPosDetilDto> posDetilList;

	private List<MerchantPayAccount> merchantPayAccounts;

	private List<MerchantBarCode> barCodeList;

	@ApiModelProperty(value = "所有成功关联二维码")
	public List<MerchantBarCode> getBarCodeList() {
		return barCodeList;
	}

	public void setBarCodeList(List<MerchantBarCode> barCodeList) {
		this.barCodeList = barCodeList;
	}

	@ApiModelProperty(value = "开户信息")
	public List<MerchantPayAccount> getMerchantPayAccounts() {
		return merchantPayAccounts;
	}

	public void setMerchantPayAccounts(List<MerchantPayAccount> merchantPayAccounts) {
		this.merchantPayAccounts = merchantPayAccounts;
	}

	@ApiModelProperty(value = "状态,0：申请，1：审核，2：通过，3：拒绝，4：撤回")
	public ApplicationFlowStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationFlowStatus status) {
		this.status = status;
	}

	@ApiModelProperty(value = "")
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@ApiModelProperty(value = "申请者名称")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@ApiModelProperty(value = "申请者电话")
	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	@ApiModelProperty(value = "商户号")
	public String getMerchantSn() {
		return merchantSn;
	}

	public void setMerchantSn(String merchantSn) {
		this.merchantSn = merchantSn;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<ApplicationPosDetilDto> getPosDetilList() {
		return posDetilList;
	}

	public void setPosDetilList(List<ApplicationPosDetilDto> posDetilList) {
		this.posDetilList = posDetilList;
	}

	@ApiModelProperty(value = "处理意见")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@ApiModelProperty(value = "邀请者名称(申请人上级)")
	public String getUpstreamAgentName() {
		return upstreamAgentName;
	}

	public void setUpstreamAgentName(String upstreamAgentName) {
		this.upstreamAgentName = upstreamAgentName;
	}

	@ApiModelProperty(value = "合伙人合同照片地址")
	public String getPartnerContractPicUrl() {
		return partnerContractPicUrl;
	}

	public void setPartnerContractPicUrl(String partnerContractPicUrl) {
		this.partnerContractPicUrl = partnerContractPicUrl;
	}

	public List<String> getPartnerContractPicUrls() {
		return partnerContractPicUrls;
	}

	public void setPartnerContractPicUrls(List<String> partnerContractPicUrls) {
		this.partnerContractPicUrls = partnerContractPicUrls;
	}
	
}
