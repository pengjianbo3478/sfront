package com.gl365.app.dto.command;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * @author Chen, Zhuhui
 */
public class TxQueryCommand {

    private String keyword;

    private LocalDate startTime;

    private LocalDate endTime;

    /**
     * 返回搜索关键字
     */
    @ApiModelProperty(value = "搜索关键字")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 返回搜索时间范围的开始时间
     */
    @ApiModelProperty(value = "搜索时间范围的开始时间")
    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    /**
     * 返回搜索时间范围的结束时间（不包含结束时间）
     */
    @ApiModelProperty(value = "搜索时间范围的结束时间（不包含结束时间）", required = true)
    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}
