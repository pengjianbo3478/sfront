package com.gl365.app.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.CustomerServiceRequestStatus;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Chen, Zhuhui
 */
public class CustomerServiceRequestFlow {

    private String id;

    private CustomerService request;

    private Agent targetingAgent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    private CustomerServiceRequestStatus previousStatus;

    private CustomerServiceRequestStatus currentStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerService getRequest() {
        return request;
    }

    public void setRequest(CustomerService request) {
        this.request = request;
    }

    public Agent getTargetingAgent() {
        return targetingAgent;
    }

    public void setTargetingAgent(Agent targetingAgent) {
        this.targetingAgent = targetingAgent;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public CustomerServiceRequestStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(CustomerServiceRequestStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    public CustomerServiceRequestStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CustomerServiceRequestStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
}
