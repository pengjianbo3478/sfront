package com.gl365.app.remote.member;

import com.gl365.app.remote.member.api_req.QueryUserInfoCommand;
import com.gl365.app.remote.member.api_resp.QueryUserInfoDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by wangmeihua on 2017/6/20.
 */
@FeignClient(name="member",url="${${env:}.url.member:}")
public interface MemberApiService {
    @RequestMapping(value = "/member/user/channel/queryUserInfo",method = RequestMethod.POST)
    ResultDto<QueryUserInfoDto> queryUserInfo(@RequestBody QueryUserInfoCommand userRltForSDto);
}
