package com.gl365.app.service;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.command.MerchantAddPosCommand;
import com.gl365.app.dto.MerchantHistory;
import com.gl365.app.dto.MerchantIndustry;

import java.util.List;
import java.util.Map;

/**
 * Created by caoyilong on 2017/6/9.
 * 商户进件
 */
public interface MerchantFacadeService {

	ApiResponse addPos(MerchantAddPosCommand merchantDto);
	
	ApiResponse addLinkMerchant(MerchantAddPosCommand merchantDto);

	MerchantHistory queryByRegNo(String regNo);

	List<MerchantIndustry> queryMerchantIndustry();

	Map<Integer, String> getIndustriesIdMap();
}
