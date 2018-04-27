package com.gl365.app.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.Company;
import com.gl365.app.dto.MerchantCacheDto;
import com.gl365.app.dto.MerchantCacheListDto;
import com.gl365.app.dto.MerchantCacheRequest;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.PosCommand;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.sales.MerchantCacheService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by qiuchaojie on 2017/6/15.
 */
@Api(description = "进件缓存数据", tags = "MerchantCache")
@RestController
@RequestMapping("/api/merchant/cache/")
public class MerchantCacheController extends BaseController {

	@Autowired
	private MerchantCacheService merchantCacheService;

	@Autowired
	private PicHandler picHandler;

	@ApiOperation(value = "查询进件数据", response = MerchantCacheDto.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "findById", method = RequestMethod.GET)
	public ApiResponse<Void> findByMobile(@RequestParam("id") Long id) {
		if (id == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		MerchantCacheDto cacheDto = merchantCacheService.findById(id);
		if (cacheDto != null) {
			if (StringUtils.isNotBlank(cacheDto.getBarCode())) {
				cacheDto.setBarCodes(JsonUtils.toBean(cacheDto.getBarCode(), ArrayList.class));
			}
			
			if (StringUtils.isNotBlank(cacheDto.getPos())) {
				cacheDto.setCommands(new Gson().fromJson(cacheDto.getPos(), new TypeToken<List<PosCommand>>() {
				}.getType()));
			}
			
			cacheDto.setTaxRegPicUrl(picHandler.addPrefixUrl(cacheDto.getTaxRegPicUrl()));
			cacheDto.setLicensePicUrl(picHandler.addPrefixUrl(cacheDto.getLicensePicUrl()));
			cacheDto.setLegalPersonIdCardFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getLegalPersonIdCardFrontPicUrl()));
			cacheDto.setLegalPersonIdCardBackPicUrl(picHandler.addPrefixUrl(cacheDto.getLegalPersonIdCardBackPicUrl()));
			cacheDto.setOrgCodePicUrl(picHandler.addPrefixUrl(cacheDto.getOrgCodePicUrl()));
			cacheDto.setContractPicUrl(picHandler.addPrefixUrl(cacheDto.getContractPicUrl()));
			cacheDto.setFacadeOfShopPicUrl(picHandler.addPrefixUrl(cacheDto.getFacadeOfShopPicUrl()));
			cacheDto.setAddressPicUrl(picHandler.addPrefixUrl(cacheDto.getAddressPicUrl()));
			cacheDto.setScopeOfOperationPicUrl(picHandler.addPrefixUrl(cacheDto.getScopeOfOperationPicUrl()));
			cacheDto.setTicketPicUrl(picHandler.addPrefixUrl(cacheDto.getTicketPicUrl()));
			cacheDto.setPrinlpEngagementLetterPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpEngagementLetterPicUrl()));
			cacheDto.setPrinlpIdHoldingPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdHoldingPicUrl()));

			cacheDto.setOpenLicensePicUrl(picHandler.addPrefixUrl(cacheDto.getOpenLicensePicUrl()));
			cacheDto.setContractNoPicUrl(picHandler.addPrefixUrl(cacheDto.getContractNoPicUrl()));
			cacheDto.setCashierDeskPicUrl(picHandler.addPrefixUrl(cacheDto.getCashierDeskPicUrl()));
			cacheDto.setOperateProductPicUrl(picHandler.addPrefixUrl(cacheDto.getOperateProductPicUrl()));
			cacheDto.setOperateFieldPicUrl(picHandler.addPrefixUrl(cacheDto.getOperateFieldPicUrl()));
			cacheDto.setPrilpBankFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrilpBankFrontPicUrl()));
			cacheDto.setPrilpBankBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrilpBankBackPicUrl()));
			cacheDto.setPrinlpIdCardFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdCardFrontPicUrl()));
			cacheDto.setPrinlpIdCardBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdCardBackPicUrl()));
			cacheDto.setPrinlpBankFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpBankFrontPicUrl()));
			cacheDto.setPrinlpBankBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpBankBackPicUrl()));
			
			cacheDto.setAdditionalContractNoPicUrl(picHandler.addPrefixUrl(cacheDto.getAdditionalContractNoPicUrl()));
		}
		return ApiResponse.createSuccess(cacheDto);
	}
	
	@ApiOperation(value = "查询连锁进件数据", response = MerchantCacheDto.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "findByLinkMerchant", method = RequestMethod.GET)
	public ApiResponse<Void> findByLinkMerchant(@RequestParam("id") Long id) {
		if (id == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		List<MerchantCacheDto> cacheDtos = merchantCacheService.findByLinkMerchant(id);
		if (CollectionUtils.isNotEmpty(cacheDtos)) {
			for (MerchantCacheDto cacheDto : cacheDtos) {
				
				if (StringUtils.isNotBlank(cacheDto.getBarCode())) {
					cacheDto.setBarCodes(JsonUtils.toBean(cacheDto.getBarCode(), ArrayList.class));
				}
				
				if (StringUtils.isNotBlank(cacheDto.getPos())) {
					cacheDto.setCommands(new Gson().fromJson(cacheDto.getPos(), new TypeToken<List<PosCommand>>() {
					}.getType()));
				}
				
				cacheDto.setTaxRegPicUrl(picHandler.addPrefixUrl(cacheDto.getTaxRegPicUrl()));
				cacheDto.setLicensePicUrl(picHandler.addPrefixUrl(cacheDto.getLicensePicUrl()));
				cacheDto.setLegalPersonIdCardFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getLegalPersonIdCardFrontPicUrl()));
				cacheDto.setLegalPersonIdCardBackPicUrl(picHandler.addPrefixUrl(cacheDto.getLegalPersonIdCardBackPicUrl()));
				cacheDto.setOrgCodePicUrl(picHandler.addPrefixUrl(cacheDto.getOrgCodePicUrl()));
				cacheDto.setContractPicUrl(picHandler.addPrefixUrl(cacheDto.getContractPicUrl()));
				cacheDto.setFacadeOfShopPicUrl(picHandler.addPrefixUrl(cacheDto.getFacadeOfShopPicUrl()));
				cacheDto.setAddressPicUrl(picHandler.addPrefixUrl(cacheDto.getAddressPicUrl()));
				cacheDto.setScopeOfOperationPicUrl(picHandler.addPrefixUrl(cacheDto.getScopeOfOperationPicUrl()));
				cacheDto.setTicketPicUrl(picHandler.addPrefixUrl(cacheDto.getTicketPicUrl()));
				cacheDto.setPrinlpEngagementLetterPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpEngagementLetterPicUrl()));
				cacheDto.setPrinlpIdHoldingPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdHoldingPicUrl()));
				
				cacheDto.setOpenLicensePicUrl(picHandler.addPrefixUrl(cacheDto.getOpenLicensePicUrl()));
				cacheDto.setContractNoPicUrl(picHandler.addPrefixUrl(cacheDto.getContractNoPicUrl()));
				cacheDto.setCashierDeskPicUrl(picHandler.addPrefixUrl(cacheDto.getCashierDeskPicUrl()));
				cacheDto.setOperateProductPicUrl(picHandler.addPrefixUrl(cacheDto.getOperateProductPicUrl()));
				cacheDto.setOperateFieldPicUrl(picHandler.addPrefixUrl(cacheDto.getOperateFieldPicUrl()));
				cacheDto.setPrilpBankFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrilpBankFrontPicUrl()));
				cacheDto.setPrilpBankBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrilpBankBackPicUrl()));
				cacheDto.setPrinlpIdCardFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdCardFrontPicUrl()));
				cacheDto.setPrinlpIdCardBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpIdCardBackPicUrl()));
				cacheDto.setPrinlpBankFrontPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpBankFrontPicUrl()));
				cacheDto.setPrinlpBankBackPicUrl(picHandler.addPrefixUrl(cacheDto.getPrinlpBankBackPicUrl()));
				
				cacheDto.setAdditionalContractNoPicUrl(picHandler.addPrefixUrl(cacheDto.getAdditionalContractNoPicUrl()));
			}
		}
		return ApiResponse.createSuccess(cacheDtos);
	}

	@ApiOperation(value = "查询列表进件数据", response = MerchantCacheListDto.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "findList", method = RequestMethod.GET)
	public ApiResponse<Void> findByIdCardNo(@RequestParam(value = "linkType", required = false) Integer linkType ,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		if(linkType == null){
			linkType = 0;
		}
		return ApiResponse.createSuccess(merchantCacheService.findList(getCurrentLoginUserId(),linkType, pageNo, pageSize));
	}

	@ApiOperation(value = "删除连锁进件数据", response = ApiResponse.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "delLinkMerchantCache", method = RequestMethod.GET)
	public ApiResponse<Void> delLinkMerchantCache(@RequestParam("id") Long id) {
		if (id == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		merchantCacheService.delLinkMerchantCache(id);
		return ApiResponse.createSuccess("");
	}
	
	@ApiOperation(value = "删除进件数据", response = ApiResponse.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "delMerchantCache", method = RequestMethod.GET)
	public ApiResponse<Void> delMerchantCache(@RequestParam("id") Long id) {
		if (id == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		merchantCacheService.delMerchantCache(id);
		return ApiResponse.createSuccess("");
	}

	@ApiOperation(value = "保存进件数据", response = ApiResponse.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "saveMerchantCache", method = RequestMethod.POST)
	public ApiResponse<Void> saveMerchantCache(@RequestBody MerchantCacheDto merchantCache) {
		merchantCache.setAgentId(getCurrentLoginUserId());

		if ("barCode".equals(merchantCache.getIsCheck())) {
			if (StringUtils.isNotBlank(merchantCache.getLicenseNo())) {
				merchantCache.setBarCodes(Arrays.asList(merchantCache.getLicenseNo()));
			}
		} else if ("Y".equals(merchantCache.getIsCheck())) {

		} else {

			if (CollectionUtils.isNotEmpty(merchantCache.getBarCodes())) {
				merchantCache.setBarCode(JsonUtils.toJsonString(merchantCache.getBarCodes()));
			}

			if (CollectionUtils.isNotEmpty(merchantCache.getCommands())) {
				merchantCache.setPos(JsonUtils.toJsonString(merchantCache.getCommands()));
			}
			
			if(merchantCache.getLinkType() == null){
				merchantCache.setLinkType(0);;
			}
			
			// 图片转换
			merchantCache.setTaxRegPicUrl(picHandler.generateSaveUrl(merchantCache.getTaxRegPicUrl()));// 税务登记证
			merchantCache.setLicensePicUrl(picHandler.generateSaveUrl(merchantCache.getLicensePicUrl()));// 营业执照图片URL
			merchantCache.setLegalPersonIdCardFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getLegalPersonIdCardFrontPicUrl()));// 法人身份证正面图片URL
			merchantCache.setLegalPersonIdCardBackPicUrl(picHandler.generateSaveUrl(merchantCache.getLegalPersonIdCardBackPicUrl()));// 法人身份证背面图片URL
			merchantCache.setOrgCodePicUrl(picHandler.generateSaveUrl(merchantCache.getOrgCodePicUrl()));// 组织机构代码证图片URL
			merchantCache.setContractPicUrl(picHandler.generateSaveUrl(merchantCache.getContractPicUrl()));// 协议照片URL
			merchantCache.setFacadeOfShopPicUrl(picHandler.generateSaveUrl(merchantCache.getFacadeOfShopPicUrl()));// 门头照图片URL
			merchantCache.setAddressPicUrl(picHandler.generateSaveUrl(merchantCache.getAddressPicUrl()));// 地址照图片URL
			merchantCache.setScopeOfOperationPicUrl(picHandler.generateSaveUrl(merchantCache.getScopeOfOperationPicUrl()));// 经营内容图片URL
			// 产品说不要
			// merchantCache.setTicketPicUrl(picHandler.generateSaveUrl(merchantCache.getTicketPicUrl()));//
			// 上传小票图片URL
			merchantCache.setPrinlpEngagementLetterPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpEngagementLetterPicUrl()));// 法人委托书图片URL
			merchantCache.setPrinlpIdHoldingPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdHoldingPicUrl()));// 法人持身份证照图片URL

			merchantCache.setOpenLicensePicUrl(picHandler.generateSaveUrl(merchantCache.getOpenLicensePicUrl()));
			merchantCache.setContractNoPicUrl(picHandler.generateSaveUrl(merchantCache.getContractNoPicUrl()));
			merchantCache.setCashierDeskPicUrl(picHandler.generateSaveUrl(merchantCache.getCashierDeskPicUrl()));
			merchantCache.setOperateProductPicUrl(picHandler.generateSaveUrl(merchantCache.getOperateProductPicUrl()));
			merchantCache.setOperateFieldPicUrl(picHandler.generateSaveUrl(merchantCache.getOperateFieldPicUrl()));
			merchantCache.setPrilpBankFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrilpBankFrontPicUrl()));
			merchantCache.setPrilpBankBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrilpBankBackPicUrl()));
			merchantCache.setPrinlpIdCardFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdCardFrontPicUrl()));
			merchantCache.setPrinlpIdCardBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdCardBackPicUrl()));
			merchantCache.setPrinlpBankFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpBankFrontPicUrl()));
			merchantCache.setPrinlpBankBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpBankBackPicUrl()));
			merchantCache.setAdditionalContractNoPicUrl(picHandler.generateSaveUrl(merchantCache.getAdditionalContractNoPicUrl()));
		}

		return ApiResponse.createSuccess(merchantCacheService.saveMerchantCache(merchantCache));
	}
	
	@ApiOperation(value = "保存连锁进件数据", response = ApiResponse.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "saveLinkMerchantCache", method = RequestMethod.POST)
	public ApiResponse<Void> saveLinkMerchantCache(@RequestBody MerchantCacheRequest request) {
		if (CollectionUtils.isEmpty(request.getMerchantCacheList())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		
		for (MerchantCacheDto merchantCache : request.getMerchantCacheList()) {
			merchantCache.setAgentId(getCurrentLoginUserId());
			
				if (CollectionUtils.isNotEmpty(merchantCache.getBarCodes())) {
					merchantCache.setBarCode(JsonUtils.toJsonString(merchantCache.getBarCodes()));
				}
				
				if (CollectionUtils.isNotEmpty(merchantCache.getCommands())) {
					merchantCache.setPos(JsonUtils.toJsonString(merchantCache.getCommands()));
				}
				
				// 图片转换
				merchantCache.setTaxRegPicUrl(picHandler.generateSaveUrl(merchantCache.getTaxRegPicUrl()));// 税务登记证
				merchantCache.setLicensePicUrl(picHandler.generateSaveUrl(merchantCache.getLicensePicUrl()));// 营业执照图片URL
				merchantCache.setLegalPersonIdCardFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getLegalPersonIdCardFrontPicUrl()));// 法人身份证正面图片URL
				merchantCache.setLegalPersonIdCardBackPicUrl(picHandler.generateSaveUrl(merchantCache.getLegalPersonIdCardBackPicUrl()));// 法人身份证背面图片URL
				merchantCache.setOrgCodePicUrl(picHandler.generateSaveUrl(merchantCache.getOrgCodePicUrl()));// 组织机构代码证图片URL
				merchantCache.setContractPicUrl(picHandler.generateSaveUrl(merchantCache.getContractPicUrl()));// 协议照片URL
				merchantCache.setFacadeOfShopPicUrl(picHandler.generateSaveUrl(merchantCache.getFacadeOfShopPicUrl()));// 门头照图片URL
				merchantCache.setAddressPicUrl(picHandler.generateSaveUrl(merchantCache.getAddressPicUrl()));// 地址照图片URL
				merchantCache.setScopeOfOperationPicUrl(picHandler.generateSaveUrl(merchantCache.getScopeOfOperationPicUrl()));// 经营内容图片URL
				// 产品说不要
				// merchantCache.setTicketPicUrl(picHandler.generateSaveUrl(merchantCache.getTicketPicUrl()));//
				// 上传小票图片URL
				merchantCache.setPrinlpEngagementLetterPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpEngagementLetterPicUrl()));// 法人委托书图片URL
				merchantCache.setPrinlpIdHoldingPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdHoldingPicUrl()));// 法人持身份证照图片URL
				
				merchantCache.setOpenLicensePicUrl(picHandler.generateSaveUrl(merchantCache.getOpenLicensePicUrl()));
				merchantCache.setContractNoPicUrl(picHandler.generateSaveUrl(merchantCache.getContractNoPicUrl()));
				merchantCache.setCashierDeskPicUrl(picHandler.generateSaveUrl(merchantCache.getCashierDeskPicUrl()));
				merchantCache.setOperateProductPicUrl(picHandler.generateSaveUrl(merchantCache.getOperateProductPicUrl()));
				merchantCache.setOperateFieldPicUrl(picHandler.generateSaveUrl(merchantCache.getOperateFieldPicUrl()));
				merchantCache.setPrilpBankFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrilpBankFrontPicUrl()));
				merchantCache.setPrilpBankBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrilpBankBackPicUrl()));
				merchantCache.setPrinlpIdCardFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdCardFrontPicUrl()));
				merchantCache.setPrinlpIdCardBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpIdCardBackPicUrl()));
				merchantCache.setPrinlpBankFrontPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpBankFrontPicUrl()));
				merchantCache.setPrinlpBankBackPicUrl(picHandler.generateSaveUrl(merchantCache.getPrinlpBankBackPicUrl()));
				merchantCache.setAdditionalContractNoPicUrl(picHandler.generateSaveUrl(merchantCache.getAdditionalContractNoPicUrl()));
			
		}

		return ApiResponse.createSuccess(merchantCacheService.saveLinkMerchantCache(request.getMerchantCacheList()));
	}

	@ApiOperation(value = "查询营业执照数据", response = Company.class)
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "company", method = RequestMethod.GET)
	public ApiResponse<Void> queryAndCreate(@RequestParam("regNo") String regNo) {
		if (regNo == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		return ApiResponse.createSuccess(merchantCacheService.queryAndCreate(regNo));
	}

}
