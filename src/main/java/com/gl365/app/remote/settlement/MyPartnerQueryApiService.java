package com.gl365.app.remote.settlement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.dto.settlequery.TxnAmountSumResp;
import com.gl365.app.remote.settlement.ApiRequest.DailyProfitQueryCommand;
import com.gl365.app.remote.settlement.ApiRequest.ProfitingTransactionQueryCommand;
import com.gl365.app.remote.settlement.ApiResponse.ProfitingTransactionDetailsDto;
import com.gl365.app.remote.withdraw.dto.BalanceAndTotalDto;

import io.swagger.annotations.ApiOperation;

/**
 * Created by wangmeihua on 2017/6/8.
 */
@FeignClient(name="settlequery",url="${${env:}.url.settlequery:}")
public interface MyPartnerQueryApiService {

	/**
	 * 2.36	查询账户明细(1.0.2)-------原来：2.28 查询账户明细
	 */
	@RequestMapping(value = "/payProfit/queryPage/queryPayProfitDetail", method = RequestMethod.POST)
	ResultDto<List<ProfitingTransactionDetailsDto>> queryProfitingTransactionDetails(@RequestBody ProfitingTransactionQueryCommand command);
	
	/**
	 *2.35	查询机构当日提成总金额(1.0.2)
	 */
	@RequestMapping(value = "/payProfit/queryProfitAmount", method = RequestMethod.POST)
	ResultDto<BigDecimal> queryProfitAmount(@RequestBody DailyProfitQueryCommand command);
	
	@ApiOperation(value = "2.48查询账户明细汇总(1.1.6)", notes = "2.48查询账户明细汇总(1.1.6)")
	@RequestMapping(value = "/payProfit/queryPage/queryPayProfitSum", method = RequestMethod.POST)
	public ResultDto<TxnAmountSumResp> getTxnAmountSum(@RequestBody ProfitingTransactionQueryCommand req);
	
	/**
	 * 2.49	累积分润/分润款余额查询(1.1.6)
	 *
	 */
	@RequestMapping(value = "/profitAgentSum/queryAgentProfitFee", method = RequestMethod.POST)
	ResultDto<BalanceAndTotalDto> querytotalProfitFee(@RequestBody BaseCommand command);
	
}
