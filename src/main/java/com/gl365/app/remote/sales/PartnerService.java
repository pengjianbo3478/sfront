package com.gl365.app.remote.sales;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.PartnerPageDto;
import com.gl365.app.dto.command.MyPartnerQueryCommand;

/**
 * Created by ryan on 2017/6/20.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface PartnerService {

    @RequestMapping(value = "/api/partner/findMyPartners", method = RequestMethod.POST)
    PartnerPageDto findMyPartners(@RequestBody MyPartnerQueryCommand conditions,
                                 @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo ,
                                 @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);
}
