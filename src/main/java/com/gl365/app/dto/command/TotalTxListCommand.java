package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class TotalTxListCommand implements Serializable {

	private static final long serialVersionUID = 5249521738308812883L;

	private boolean previousMonth;

	private LocalDate currentMonth;

	@ApiModelProperty("是否上一个月")
	public boolean isPreviousMonth() {
		return previousMonth;
	}

	public void setPreviousMonth(boolean previousMonth) {
		this.previousMonth = previousMonth;
	}

	@ApiModelProperty("当前月份,第一次请求不需要传该参数")
	public LocalDate getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(LocalDate currentMonth) {
		this.currentMonth = currentMonth;
	}
}
