package com.gl365.app.remote.withdraw;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.remote.settlement.ResultDto;
import com.gl365.app.remote.withdraw.command.CashCommand;
import com.gl365.app.remote.withdraw.command.QueryVoucherListCommand;
import com.gl365.app.remote.withdraw.command.VcPayDetailCommand;
import com.gl365.app.remote.withdraw.dto.BalanceAndTotalDto;
import com.gl365.app.remote.withdraw.dto.BaseDto;
import com.gl365.app.remote.withdraw.dto.CalculateVcTxnDto;
import com.gl365.app.remote.withdraw.dto.QueryTotalRequestCashDto;
import com.gl365.app.remote.withdraw.dto.VcPayDto;
import com.gl365.app.remote.withdraw.dto.VoucherListDto;

/**
 * Created by qiuchaojie on 2017/6/15.
 */

@FeignClient(name="settlement",url="${${env:}.url.settlement:}")
public interface WithDrawService {

    /**
     * 申请提现
     */
    @RequestMapping(value = "/vcCashRequest/applyVcCashRequest", method = RequestMethod.POST)
    BaseDto withdrawApplication(@RequestBody CashCommand command);


    /**
     * 查询累计提现金额
     */
    @RequestMapping(value = "/vcCashRequest/queryTotalVcCashRequest", method = RequestMethod.POST)
    QueryTotalRequestCashDto queryTotalVcCashRequest(@RequestBody BaseCommand command);


    /**
     * 计算手续费和税费
     */
    @RequestMapping(value = "/vcPay/calculateVcTxn", method = RequestMethod.POST)
    CalculateVcTxnDto calculateVcTxn(@RequestBody CashCommand command);


    /**
     * 提现列表
     */
    @RequestMapping(value = "/vcCashRequest/queryVcCashRequest", method = RequestMethod.POST)
    VoucherListDto list(@RequestBody QueryVoucherListCommand command);


    /**
     * 提现的代付详情
     */
    @RequestMapping(value = "/vcPay/queryVcPayByVoucherId", method = RequestMethod.POST)
    VcPayDto vcPayDetail(@RequestBody VcPayDetailCommand command);
    
}
