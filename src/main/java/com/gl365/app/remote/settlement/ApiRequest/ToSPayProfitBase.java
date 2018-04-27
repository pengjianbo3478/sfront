package com.gl365.app.remote.settlement.ApiRequest;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * S端 2.21查询积分联付提成明细（直接发展商户贡献+服务费贡献）请求参数
 * @author lch
 *
 */
public class ToSPayProfitBase{
	
	/**
	 * 提成对象类型
	 */
	private String ownerType;

	/**
	 * 提成对象
	 */
	private String ownerId;

	/**
	 * 开始查询日期 可为空
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;

	/**
	 * 结束查询日期 可为空
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	
	/**
	 * 商户号
	 */
	private String merchantNo;	
	
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
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
}
