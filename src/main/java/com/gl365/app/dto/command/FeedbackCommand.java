package com.gl365.app.dto.command;

import com.gl365.app.constraints.NonSpecialCharacters;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by ryan on 2017/6/17.
 */
public class FeedbackCommand {
    private String agentId;

    @NotNull(message = "反馈内容不能为空")
    @NonSpecialCharacters
    private String content;

    @ApiModelProperty(hidden = true)
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @ApiModelProperty(value = "反馈内容", required = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
