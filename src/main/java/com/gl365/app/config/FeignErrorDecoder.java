package com.gl365.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl365.app.exception.GeiLeSalesServiceException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Chen, Zhuhui
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    public static final class ExceptionDescription {
        private String className;
        private String code;
        private String message;
        private String stackTrace;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 400) {
            try {
                ExceptionDescription ed = objectMapper.readerFor(ExceptionDescription.class).readValue(response.body().asInputStream());
                return new HystrixBadRequestException(ed.getMessage(), new GeiLeSalesServiceException(ed.getCode(), ed.getMessage()));
            } catch (IOException e) {
                return FeignException.errorStatus(methodKey, response);
            }
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
