package com.gl365.app.remote.settlement;

import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.remote.settlement.ApiRequest.UpdateBankAccountCommand;
import com.gl365.app.remote.settlement.ApiResponse.BanksDto;
import com.gl365.app.remote.settlement.ApiResponse.SingleBankDto;
import com.gl365.app.remote.settlement.ApiResponse.SynchronizeBankResultDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qiuchaojie on 2017/6/8.
 */
@FeignClient(name="settlement",url="${${env:}.url.settlement:}")
public interface BankAccountInfoService {

    @RequestMapping(value = "/bankAccountInfo/synchroBankAccountInfo",method = RequestMethod.POST)
    SynchronizeBankResultDto synchronizeBankAccountInfo(@RequestBody UpdateBankAccountCommand command);

    @RequestMapping(value = "/bankAccountInfo/querySingle",method = RequestMethod.POST)
    SingleBankDto querySingle(@RequestBody BaseCommand command);

    @RequestMapping(value = "/bankAccountInfo/queryBankAccountInfo",method = RequestMethod.POST)
    BanksDto queryBankList(@RequestBody BaseCommand command);

    @RequestMapping(value = "/bankAccountInfo/deleteBankAccountInfo",method = RequestMethod.POST)
    SynchronizeBankResultDto deleteBankAccountInfo(@RequestBody BaseCommand command);
}
