package com.gl365.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.dto.enum_type.CustomerServiceRequestStatus;
import com.gl365.app.dto.enum_type.CustomerServiceRequestType;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by qiuchaojie on 2017/6/7.
 */
public class CustomerServiceRequestFlowDto {

    @ApiModelProperty(value = "id.(在这里加点说明)详情页里面“处理中”显示的时间为上一环节的结束时间，所以当返回数组只有1条的时候，该时间直接显示申请的时间。如果有2条，则显示第二条数据的时间即可", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @ApiModelProperty(value = "售后服务类型:10-法人账户变更, 20-非法人账户变更, 30-机具故障, 40-退机", dataType = "java.lang.Integer")
    private CustomerServiceRequestType type;

    @ApiModelProperty(value = "当前状态:10-待平台处理，20-待代理商处理，30-待业务员处理，40-已完成", dataType = "java.lang.Integer")
    private CustomerServiceRequestStatus currentStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public CustomerServiceRequestType getType() {
        return type;
    }

    public void setType(CustomerServiceRequestType type) {
        this.type = type;
    }

    public CustomerServiceRequestStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CustomerServiceRequestStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
}
