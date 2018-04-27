package com.gl365.app.web;

import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.exception.GeiLeSalesServiceException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;

/**
 * @author Chen, Zhuhui
 */
@ControllerAdvice
public class FacadeControllerAdvice extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(FacadeControllerAdvice.class);
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = { HystrixBadRequestException.class, HystrixRuntimeException.class,
			HystrixTimeoutException.class, Exception.class })
	public ResponseEntity<ApiResponse<Void>> handleGeiLeManagementSystemException(Exception ex) throws Throwable {
		logger.error("===HystrixRuntimeException==>" + ex.getMessage() + ":" + ex.getLocalizedMessage(), ex);
		Throwable th = ex.getCause();
		if (th instanceof GeiLeSalesServiceException) {
			GeiLeSalesServiceException exception = (GeiLeSalesServiceException) th;
			ApiResponse<Void> response = new ApiResponse<>();
			response.setCode(exception.getCode());
			response.setMessage(exception.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			ApiResponse<Void> response = new ApiResponse<>();
			response.setCode(ReturnCode.System.SYSTEM_ERROR.getCode());
			response.setMessage(ReturnCode.System.SYSTEM_ERROR.getMsg());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@ExceptionHandler(value = { GeiLeSalesServiceException.class })
	public ResponseEntity<ApiResponse<Void>> handleGeiLeManagementSystemException(GeiLeSalesServiceException ex)
			throws Throwable {
		logger.error("===WxPayGatewayException==>", ex);
		ApiResponse<Void> response = new ApiResponse<>();
		response.setCode(ex.getCode());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("===MethodArgumentNotValidException==>", ex);
		BindingResult bindingResult = ex.getBindingResult();
		return ResponseEntity.badRequest().body(buildApiResponseForBindingErrors(bindingResult));
	}

	private ApiResponse<Void> buildApiResponseForBindingErrors(BindingResult bindingResult) {
		ApiResponse<Void> response = new ApiResponse<>();
		response.setCode(ReturnCode.System.PARAM_ERROR.getCode());
		String message = bindingResult.getAllErrors().stream()
				.map(oe -> messageSource.getMessage(oe, Locale.getDefault())).collect(Collectors.joining(", "));
		response.setMessage(message);
		return response;
	}
}
