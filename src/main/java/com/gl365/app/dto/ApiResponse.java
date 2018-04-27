package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * 没有分页参数的返回
 *
 * @author caoyilong
 */
public class ApiResponse<T> {

	private String code;

	private String message;

	private T data;

	public static ApiResponse<Void> createNoErrorResponse(String message) {
		ApiResponse<Void> response = new ApiResponse<>();
		response.setCode(ReturnCode.System.SUCCESS.getCode());
		if (!StringUtils.isEmpty(message)) {
			response.setMessage(message);
		} else {
			response.setMessage(ReturnCode.System.SUCCESS.getMsg());
		}
		return response;
	}
	
	public static ApiResponse<Void> createErrorResponse(String message) {
		ApiResponse<Void> response = new ApiResponse<>();
		response.setCode(ReturnCode.System.SYSTEM_ERROR.getCode());
		if (!StringUtils.isEmpty(message)) {
			response.setMessage(message);
		} else {
			response.setMessage(ReturnCode.System.SYSTEM_ERROR.getMsg());
		}
		return response;
	}

	public static ApiResponse createSuccess(Object data) {
		ApiResponse r = new ApiResponse();
		r.setCode(ReturnCode.System.SUCCESS.getCode());
		r.setMessage(ReturnCode.System.SUCCESS.getMsg());
		r.setData(data);
		return r;
	}

	public static <T> ApiResponse<T> createTypedSuccess(T data) {
		ApiResponse<T> r = new ApiResponse<>();
		r.setCode(ReturnCode.System.SUCCESS.getCode());
		r.setMessage(ReturnCode.System.SUCCESS.getMsg());
		r.setData(data);
		return r;
	}

    public static ApiResponse createResponseWithMessage(String code, String message) {
        return createResponseWithPayload(null, code, message);
    }

    public static ApiResponse createResponseWithMessage(ReturnCode.System source) {
        return createResponseWithPayload(null, source.getCode(), source.getMsg());
    }

    public static ApiResponse createResponseWithMessage(ReturnCode.Bank source) {
        return createResponseWithPayload(null, source.getCode(), source.getMsg());
    }

	public static ApiResponse createResponseWithMessage(ReturnCode.AgentUser source) {
		return createResponseWithPayload(null, source.getCode(), source.getMsg());
	}

	public static ApiResponse createResponseWithMessage(ReturnCode.Voucher source) {
		return createResponseWithPayload(null, source.getCode(), source.getMsg());
	}

	public static ApiResponse createResponseWithPayload(Object data, String code, String message) {
		ApiResponse r = new ApiResponse();
		r.setCode(code);
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	/**
	 * 默认返回错误提示
	 */
	public static ApiResponse getErrorInfo() {
		ApiResponse r = new ApiResponse();
		r.setCode(ReturnCode.System.SYSTEM_ERROR.getCode());
		r.setMessage(ReturnCode.System.SYSTEM_ERROR.getMsg());
		return r;
	}

	/**
	 * 返回错误码。“000000”表示无错误
	 */
	@ApiModelProperty(value = "错误码。“000000”表示无错误", required = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回错误消息
	 */
	@ApiModelProperty(value = "错误消息", required = true)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 返回响应报文内容
	 */
	@ApiModelProperty(value = "响应报文内容", required = true)
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
