package com.gl365.app.remote.profit;

import com.gl365.app.remote.profit.command.QueryOneCommand;
import com.gl365.app.remote.profit.command.UpdateProfitConfigCommand;
import com.gl365.app.remote.profit.dto.OneAgentProfitDto;
import com.gl365.app.remote.profit.dto.OneProfitConfigDto;
import com.gl365.app.remote.withdraw.dto.BaseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
@FeignClient(name="settlement",url="${${env:}.url.settlement:}")
public interface ProfitConfigService {

    /**
     * 查询分润设置
     */
    @RequestMapping(value = "/agentProfitConfig/queryOne",method = RequestMethod.POST)
    OneAgentProfitDto queryOne(@RequestBody QueryOneCommand command);

    /**
     * 分润设置
    */
    @RequestMapping(value = "/agentProfitConfig/setAgentProfitConfig",method = RequestMethod.POST)
    BaseDto setProfitConfig(@RequestBody UpdateProfitConfigCommand command);


    /**
     * 查询多条提成方案
     */
    @RequestMapping(value = "/profitconfig/findByCode/{code}", method = RequestMethod.POST)
    OneProfitConfigDto findByCode(@PathVariable("code") String code);

}
