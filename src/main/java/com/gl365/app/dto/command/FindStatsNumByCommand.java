package com.gl365.app.dto.command;

import com.gl365.app.dto.Command;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/12.
 */
public class FindStatsNumByCommand {
    private Boolean team;

    @ApiModelProperty(value = "是不是团队" ,required = true)
    public Boolean getTeam() {
        return team;
    }

    public void setTeam(Boolean team) {
        this.team = team;
    }
}
