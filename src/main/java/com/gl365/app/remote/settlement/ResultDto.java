package com.gl365.app.remote.settlement;

import java.io.Serializable;

import com.gl365.app.dto.ReturnCode;

/**
 * Created by wangmeihua on 2017/6/8.
 */
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = 5598379493227689413L;

	/**
	 * result : 响应码
	 */
	private String resultCode;

	/**
	 * description ： 响应描述
	 */
	private String resultDesc;

	/**
	 * data : 结果数据
	 */
	private T data;

	private Long totalNum;

	public ResultDto() {

	}

	public ResultDto(T data) {
		this.data = data;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		if (ReturnCode.System.NEED_LOGIN.getCode().equals(resultCode)) {
			this.resultCode = ReturnCode.System.PARAM_NULL.getCode();
		} else {
			this.resultCode = resultCode;
		}
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
}
