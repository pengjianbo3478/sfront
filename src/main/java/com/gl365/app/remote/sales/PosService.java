package com.gl365.app.remote.sales;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.MyPosesDto;
import com.gl365.app.dto.PosDetailDto;
import com.gl365.app.dto.ResultForSDto;
import com.gl365.app.dto.command.CheckPosCommand;
import com.gl365.app.dto.command.InstallPosCommand;
import com.gl365.app.dto.command.PosDetailCommand;

/**
 * Created by qiuchaojie on 2017/6/12.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface PosService {


    @RequestMapping(value = "/pos/myList/{agentId}", method = RequestMethod.POST)
    MyPosesDto myList(@PathVariable("agentId") String agentId,
                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                      @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

    @RequestMapping(value = "/pos/installApplication", method = RequestMethod.POST)
    void installApplication(@RequestBody InstallPosCommand command);

    @RequestMapping(value = "/pos/checkPos", method = RequestMethod.POST)
    ResultForSDto<PosDetailDto> checkPos(@RequestBody CheckPosCommand command);

    @RequestMapping(value = "/pos/uninstallPos", method = RequestMethod.POST)
    PosDetailDto uninstallPos(@RequestBody InstallPosCommand command);

    @RequestMapping(value = "/pos/detail", method = RequestMethod.POST)
    PosDetailDto detail(@RequestBody PosDetailCommand command);

}
