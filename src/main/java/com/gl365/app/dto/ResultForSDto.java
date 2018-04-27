package com.gl365.app.dto;

import java.io.Serializable;

/**
 * Created by xty on 2017/6/27.
 */
public class ResultForSDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * result : 响应码
     */
    private String code;

    /**
     * description ： 响应描述
     */
    private String message;

    /**
     * data : 结果数据
     */
    private T data;

	public ResultForSDto() {

    }

	public ResultForSDto(T data) {
        this.data = data;
    }
	
	public ResultForSDto(ReturnCode.System source) {
		this.code = source.getCode();
		this.message = source.getMsg();
	}

	public ResultForSDto(ReturnCode.System source, T data) {
		this.code = source.getCode();
		this.message = source.getMsg();
		this.data = data;
	}

	public ResultForSDto(ReturnCode.System source, String message, T data) {
		this.code = source.getCode();
		this.message = message;
		this.data = data;
	}
	
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

