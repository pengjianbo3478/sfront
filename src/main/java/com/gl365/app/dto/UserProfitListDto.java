package com.gl365.app.dto;

import com.gl365.app.dto.users.PageInfoDto;
import com.gl365.app.utils.BigDecimaluitl;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangmeihua on 2017/6/21.
 */
public class UserProfitListDto extends PageInfoDto{
    private List<UserProfitDto> list;
    private Long userCount;
    private BigDecimal myCooperationProfit;

    @ApiModelProperty("我的分润")
    public String getMyCooperationProfit() {
        return BigDecimaluitl.setScaleStr(myCooperationProfit);
    }

    public void setMyCooperationProfit(BigDecimal myCooperationProfit) {
        this.myCooperationProfit = myCooperationProfit;
    }

    @ApiModelProperty("用户数量")
    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }
    @ApiModelProperty(value = "数据列表")
    public List<UserProfitDto> getList() {
        return list;
    }

    public void setList(List<UserProfitDto> list) {
        this.list = list;
    }
}
