package com.gl365.app.remote.account;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.app.dto.wxpay.OpenAccountReq;
import com.gl365.app.dto.wxpay.WalletUrlReq;
import com.gl365.app.dto.wxpay.WalletUrlRsp;
import com.gl365.app.remote.account.command.QueryBalanceCommand;
import com.gl365.app.remote.account.dto.CommonDTO;
import com.gl365.app.remote.account.dto.QueryBalanceDto;
import com.gl365.app.remote.settlement.ResultDto;

/**
 * Created by qiuchaojie on 2017/6/17.
 */
@FeignClient(name = "account", url = "${${env:}.url.account:}")
public interface AccountService {

	/**
	 * 查询余额
	 */
	@RequestMapping(value = "/profitAccount/queryProfitAccount", method = RequestMethod.POST)
	QueryBalanceDto queryBalance(@RequestBody QueryBalanceCommand command);

	/**
	 * 根据银行卡查询银行信息
	 */
	@RequestMapping(value = "/bindinfo/queryBankInfoByCardNo/{cardNo}", method = RequestMethod.POST)
	public CommonDTO queryBankInfoByCardNo(@PathVariable("cardNo") String cardNo);

	/**
	 * 开户
	 * 
	 * @param openAccountReq
	 * @return
	 */
	@RequestMapping(value = "/openAccount/open", method = RequestMethod.POST)
	ResultDto<WalletUrlRsp> openAct(@RequestBody OpenAccountReq openAccountReq);

	/**
	 * 查询钱包地址
	 * 
	 * @param walletUrlReq
	 * @return
	 */
	@RequestMapping(value = "/openAccount/walletUrl", method = RequestMethod.POST)
	ResultDto<WalletUrlRsp> getWalletUrl(@RequestBody WalletUrlReq walletUrlReq);
	
	@RequestMapping(value = "/openAccount/findWalletUrlByOpenId", method = RequestMethod.POST)
	ResultDto<WalletUrlRsp> findWalletUrlByOpenId(@RequestBody WalletUrlReq walletUrlReq);
	
	
	
	/**
	 * 开户状态
	 * 
	 * @param walletUrlReq
	 * @return
	 */
	@RequestMapping(value = "/openAccount/query/openResult", method = RequestMethod.POST)
	ResultDto<WalletUrlRsp> openResult(@RequestBody WalletUrlReq walletUrlReq);

}
