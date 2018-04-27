package com.gl365.app.remote.sales;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.AgentScheduleFlow;
import com.gl365.app.dto.ApplicationPosDto;
import com.gl365.app.dto.FindAgentApplicationsListDto;
import com.gl365.app.dto.LinkMerchantDto;
import com.gl365.app.dto.LinkMerchantListDto;
import com.gl365.app.dto.MerchantApplicationFullDetailsDto;
import com.gl365.app.dto.MerchantScheduleFlow;
import com.gl365.app.dto.command.ApplicationApproveRequest;
import com.gl365.app.dto.command.GetMerchantApplicationDetailRequest;
import com.gl365.app.remote.ClientConfig;
import com.gl365.app.remote.sales.ApiRequest.ApplicationScheduleQueryConditions;
import com.gl365.app.remote.sales.ApiRequest.FindStatusNumByConditions;

/**
 * Created by wangmeihua on 2017/6/2.
 */
@FeignClient(name="sales", configuration = ClientConfig.class, url="${${env:}.url.sales:}")
public interface ApplicationScheduleService {
    @RequestMapping(value = "/api/applicationSchedule/findAgentApplications" ,method = RequestMethod.POST)
    FindAgentApplicationsListDto findAgentApplications(@RequestBody ApplicationScheduleQueryConditions request,
                                                                @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
                                                                @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

    @RequestMapping(value = "/api/applicationSchedule/findStatsNumByConditions" ,method = RequestMethod.POST)
    Map<String, Integer> findStatsNumByConditions(@RequestBody FindStatusNumByConditions request);

    @RequestMapping(value = "/api/applicationSchedule/approve",method=RequestMethod.POST)
    void approveApplication(@RequestBody ApplicationApproveRequest request);

    @RequestMapping(value = "/api/applicationSchedule/reject")
    void rejectApplication(@RequestBody ApplicationApproveRequest request);

    @RequestMapping(value = "/api/applicationSchedule/withdraw")
    void withdrawApplication(@RequestBody ApplicationApproveRequest request);

    @RequestMapping(value = "/api/applicationSchedule/getMerchantApplicationDetail")
    MerchantApplicationFullDetailsDto getMerchantApplicationDetail(@RequestBody GetMerchantApplicationDetailRequest request );


    @RequestMapping(value = "/api/applicationSchedule/getMerchantScheduleFlow")
    List<MerchantScheduleFlow> getMerchantScheduleFlow(@RequestBody GetMerchantApplicationDetailRequest request);
    
    @RequestMapping(value = "/api/applicationSchedule/getPosDetailForApply")
    ApplicationPosDto getPosDetailForApply(@RequestBody GetMerchantApplicationDetailRequest request);

    @RequestMapping(value = "/api/applicationSchedule/getAgentScheduleFlow")
    List<AgentScheduleFlow> getAgentScheduleFlow(@RequestBody GetMerchantApplicationDetailRequest request);
    
    @RequestMapping(value = "/merchant/findLinkMerchantByAgentId")
	LinkMerchantListDto findLinkMerchantByAgentId(@RequestBody LinkMerchantDto linkMerchant,
            @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);
}
