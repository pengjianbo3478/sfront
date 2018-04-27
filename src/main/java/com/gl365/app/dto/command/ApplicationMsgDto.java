package com.gl365.app.dto.command;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ApplicationMsgDto {

	@ApiModelProperty(value = "上次点击待审核的时间，不传时存在待审核的则返回true")
	private LocalDateTime time;

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
