package com.gl365.app.remote.member;

import com.gl365.app.remote.member.api_req.QueryUserInfoCommand;
import com.gl365.app.remote.member.api_resp.QueryUserInfoDto;
import com.gl365.app.remote.member.api_resp.UserDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmeihua on 2017/6/22.
 */
@Service("merchantApiServiceDummyImpl")
public class MerchantApiServiceDummyImpl implements MemberApiService{
    private final static boolean isDummy = false;
    @Autowired
    private MemberApiService merchantApiService;

    @Override
    public ResultDto<QueryUserInfoDto> queryUserInfo(QueryUserInfoCommand command) {
        if(isDummy){
            ResultDto<QueryUserInfoDto> resultDto = new ResultDto<QueryUserInfoDto>();
            QueryUserInfoDto queryUserInfoDto = new QueryUserInfoDto();
            List<UserDetailDto> dtos = new ArrayList<UserDetailDto>();
            for(int i=0 ; i<10 ;i++){
                UserDetailDto dto = new UserDetailDto();
                dto.setMobilePhone("13200000000");
                dto.setRealName("hhh");
                dto.setUserId("1111");
                dtos.add(dto);
            }
            queryUserInfoDto.setList(dtos);
            resultDto.setResult("000000");
            resultDto.setData(queryUserInfoDto);
            return resultDto;
        }else{
            return merchantApiService.queryUserInfo(command);
        }

    }
}
