package com.gl365.app.exception;

/**
 * @author Chen, Zhuhui
 */
public class GeiLeSalesServiceException extends RuntimeException {

    private final String code;

    public GeiLeSalesServiceException(String errorCode, String message) {
        super(message);
        this.code = errorCode;
    }

    public String getCode() {
        return code;
    }
}
