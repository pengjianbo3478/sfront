package com.gl365.app.remote.settlement;

import com.gl365.app.remote.settlement.ApiRequest.*;
import com.gl365.app.remote.settlement.ApiResponse.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangmeihua on 2017/6/8.
 */
@FeignClient(name="settlement",url="${${env:}.url.settlement:}")
public interface MyPartnerApiService {
	/**
	 * 2.6 查询县级对市级贡献积累
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conDistrictSum/queryDistrict",method = RequestMethod.POST)
	ResultDto<List<QueryDistrictResponse>> queryDistrict(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.11查询机构直接发展商户提成
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conMerchantSum/queryMerchant" ,method = RequestMethod.POST)
	ResultDto<List<QueryMerchantResponse>> queryMerchant(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.13查询直接业务员提成贡献
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conSalesmanSum/findConSalesmanSum" ,method = RequestMethod.POST)
	ResultDto<List<QueryDistrictResponse>> findConSalesmanSum(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.16查询每个发展用户对机构累积贡献
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conUserSum/queryConUserSumByAgent" ,method = RequestMethod.POST)
	ResultDto<List<QueryConUserSumByAgentResponse>> queryConUserSumByAgent(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.7查询县级提成详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conDistrictMerSum/queryDistrictMer" ,method = RequestMethod.POST)
	ResultDto<List<QueryDistrictMerResponse>> queryDistrictMer(@RequestBody QueryDistrictCommand request);
	
	/**
	 * 2.11查询机构直接发展商户提成
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conMerchantSum/queryMerchant" ,method = RequestMethod.POST)
	ResultDto<List<QueryDistrictMerResponse>> queryMer(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.8查询商户交易提成列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryMerchantSumDetail" ,method = RequestMethod.POST)
	ResultDto<List<QueryMerchantSumDetailResponse>> queryMerchantSumDetail(@RequestBody QueryMerchantSumDetailRequest request);
	
	/**
	 * 2.20间接发展商户贡献
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryIndirectMerchantDetail" ,method = RequestMethod.POST)
	ResultDto<List<QueryMerchantSumDetailResponse>> queryIndirectMerchantDetail(@RequestBody QueryMerchantSumDetailRequest request);
	
	/**
	 * 2.22	查询积分联付提成明细（直接业务员贡献）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryDirectAgentNoDetail" ,method = RequestMethod.POST)
	ResultDto<List<QueryMerchantSumDetailResponse>> queryDirectAgentNoDetail(@RequestBody QueryMerchantSumDetailRequest request);
	
	
	
	/**
	 * 2.21查询积分联付提成明细（直接发展商户贡献+服务费贡献）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryDirectMerchantAndServiceDetail" ,method = RequestMethod.POST)
	ResultDto<List<QueryMerchantSumDetailResponse>> queryDirectMerchantAndServiceDetail(@RequestBody ToSPayProfitBase request);

	/**
	 * 2.15查询下级业务员提成贡献
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conSecondSalesmanSum/queryConSecondSalesmanSum" ,method = RequestMethod.POST)
	ResultDto<List<QueryDistrictResponse>> queryConSecondSalesmanDaily(@RequestBody QueryDistrictCommand request);

	/**
	 * 2.5查询机构所得贡献累积信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conAgentSum/query" ,method = RequestMethod.POST)
	ResultDto<CooperationTotalProfitDto> query(@RequestBody QueryAssociationCommand request);

	/**
	 * 2.9查询单笔交易详情
	 * @return
	 */
	@RequestMapping(value = "/paymain/findByPayId/{payId}",method = RequestMethod.POST)
	FindPayResponse findByPayId(@PathVariable("payId") String payId);

	/**
	 * 2.10查询单笔交易提成明细
	 * @return
	 */
	@RequestMapping(value = "/payProfit/findByPayId/{payId}",method = RequestMethod.POST)
	ResultDto<List<QueryMerchantSumDetailResponse>> findByPayDetailId(@PathVariable("payId") String payId);

	/**
	 * 2.18 查询机构提成日报
	 */
	@RequestMapping(value = "/profitAgentDaily/query", method = RequestMethod.POST)
	ResultDto<List<DailyProfitDto>> queryDailyProfit(@RequestBody DailyProfitQueryCommand command);

	/**
	 * 2.24 查询机构提成结存信息
	 */
	@RequestMapping(value = "/profitAgentSum/queryProfitAgentSumByIds", method = RequestMethod.POST)
	ResultDto<List<AgentProfitSumDto>> queryAgentProfitSum(@RequestBody AgentProfitSumQueryCommand command);

	/**
	 * 2.28 查询账户明细
	 */
	@RequestMapping(value = "/payProfit/queryPayProfitDetail", method = RequestMethod.POST)
	ResultDto<List<ProfitingTransactionDetailsDto>> queryProfitingTransactionDetails(@RequestBody ProfitingTransactionQueryCommand command);

	/**
	 * 2.29.3查询月结单
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "/vcAgentMonth/queryVcAgentMonth", method = RequestMethod.POST)
	ResultDto<List<QueryVcAgentMonthDto>> queryVcAgentMonth(@RequestBody QueryVcAgentMonthCommand command);

	/**
	 * 2.29.8查询代付单明细记录
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "/vcPay/queryVcPayByVoucherId", method = RequestMethod.POST)
	ResultDto<List<QueryVcPayByIdDto>> queryVcPayByVoucherId(@RequestBody QueryVcPayByIdCommand command);

	@RequestMapping(value = "/payProfit/queryPayProfitDetailsToS", method = RequestMethod.POST)
	ResultDto<List<QueryPayProfitDetailsToSDto>> queryPayProfitDetailsToS(@RequestBody QueryPayProfitDetailsToSCommand ccmmand);
	/**
	 * 2.33 查询机构所得贡献日报
	 */
	@RequestMapping(value = "/conAgentDaily/query", method = RequestMethod.POST)
	ResultDto<List<DailyDevoteResp>> queryConAgentSum(@RequestBody DailyDevoteQueryCommand command);

	/**
	 * 2.34	查询单笔清算流水
	 * @return
	 */
	@RequestMapping(value = "/settlementmergetxn/findByPayId/{payId}",method = RequestMethod.POST)
	FindPayResponse mergetxnFindByPayId(@PathVariable("payId") String payId);
}
