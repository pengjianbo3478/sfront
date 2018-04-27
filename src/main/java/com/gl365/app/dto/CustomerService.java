package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.CustomerServiceRequestStatus;
import com.gl365.app.dto.enum_type.CustomerServiceRequestType;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Chen, Zhuhui
 */
public class CustomerService {

    private String id;

    private Agent agent;

    private Merchant merchant;

    private CustomerServiceRequestType type;

    private CustomerServiceRequestStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public CustomerServiceRequestType getType() {
        return type;
    }

    public void setType(CustomerServiceRequestType type) {
        this.type = type;
    }

    public CustomerServiceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerServiceRequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
