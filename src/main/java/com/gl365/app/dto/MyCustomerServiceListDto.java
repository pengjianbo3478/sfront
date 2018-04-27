package com.gl365.app.dto;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.CustomerServiceRequestStatus;
import com.gl365.app.dto.enum_type.CustomerServiceRequestType;
import com.gl365.app.dto.users.PageInfoDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by qiuchaojie on 2017/6/5.
 */
public class MyCustomerServiceListDto extends PageInfoDto{

    @ApiModelProperty("数据列表")
    private List<MyCustomerServices> list;

    public List<MyCustomerServices> getList() {
        return list;
    }

    public void setList(List<MyCustomerServices> list) {
        this.list = list;
    }

    static class MyCustomerServices {

        @ApiModelProperty(value = "售后服务id", required = true, dataType = "java.lang.String")
        private String requestId;

        @ApiModelProperty(value = "申请售后服务的业务员id。如果返回值是null，代表是平台发起的售后服务", dataType = "java.lang.String")
        private String requestAgentId;

        @ApiModelProperty(value = "售后服务类型。10-法人账户变更，20-非法人账户变更，30-机具故障，40-退机", required = true, dataType = "java.lang.Integer")
        private CustomerServiceRequestType requestType;

        @ApiModelProperty(value = "售后服务状态。10-待平台处理(处理中)，20-待代理商处理(代理商视角-待处理，业务员视角-处理中)，30-待业务员处理(代理商视角-处理中，业务员视角-待处理)，40-已完成", required = true, dataType = "java.lang.Integer")
        private CustomerServiceRequestStatus requestStatus;

        @ApiModelProperty(value = "商户名称", dataType = "java.lang.String")
        private String merchantName;

        @ApiModelProperty(value = "商户联系人名字", dataType = "java.lang.String")
        private String merchantContactName;

        @ApiModelProperty(value = "商户联系人电话", dataType = "java.lang.String")
        private String merchantContactMobile;

        @ApiModelProperty(value = "商户合作伙伴的名字", dataType = "java.lang.String")
        private String merchantCooperationAgentName;

        @ApiModelProperty(value = "商户合作伙伴的电话", dataType = "java.lang.String")
        private String merchantCooperationAgentMobile;

        @ApiModelProperty(value = "发展该商户的业务员", dataType = "java.lang.String", hidden = true)
        private String merchantAgentId;

        @ApiModelProperty(value = "是否属于自己发展的商户", dataType = "java.lang.Boolean")
        private boolean isBelong;

        @ApiModelProperty(value = "售后服务新建时间")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime creationTime;
        
        private String merchantId;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getRequestAgentId() {
            return requestAgentId;
        }

        public void setRequestAgentId(String requestAgentId) {
            this.requestAgentId = requestAgentId;
        }

        public CustomerServiceRequestType getRequestType() {
            return requestType;
        }

        public void setRequestType(CustomerServiceRequestType requestType) {
            this.requestType = requestType;
        }

        public CustomerServiceRequestStatus getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(CustomerServiceRequestStatus requestStatus) {
            this.requestStatus = requestStatus;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantContactName() {
            return merchantContactName;
        }

        public void setMerchantContactName(String merchantContactName) {
            this.merchantContactName = merchantContactName;
        }

        public String getMerchantContactMobile() {
            return merchantContactMobile;
        }

        public void setMerchantContactMobile(String merchantContactMobile) {
            this.merchantContactMobile = merchantContactMobile;
        }

        public String getMerchantCooperationAgentName() {
            return merchantCooperationAgentName;
        }

        public void setMerchantCooperationAgentName(String merchantCooperationAgentName) {
            this.merchantCooperationAgentName = merchantCooperationAgentName;
        }

        public String getMerchantCooperationAgentMobile() {
            return merchantCooperationAgentMobile;
        }

        public void setMerchantCooperationAgentMobile(String merchantCooperationAgentMobile) {
            this.merchantCooperationAgentMobile = merchantCooperationAgentMobile;
        }

        public String getMerchantAgentId() {
            return merchantAgentId;
        }

        public void setMerchantAgentId(String merchantAgentId) {
            this.merchantAgentId = merchantAgentId;
        }

        public boolean isBelong() {
            return isBelong;
        }

        public void setBelong(boolean belong) {
            isBelong = belong;
        }

        public LocalDateTime getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(LocalDateTime creationTime) {
            this.creationTime = creationTime;
        }

		public String getMerchantId() {
			return merchantId;
		}

		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
        
    }
}
