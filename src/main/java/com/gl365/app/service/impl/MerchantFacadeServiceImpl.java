package com.gl365.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.MerchantApplication;
import com.gl365.app.dto.MerchantHistory;
import com.gl365.app.dto.MerchantIndustry;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.MerchantAddPosCommand;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.merchant.GeiLeMerchantService;
import com.gl365.app.remote.merchant.dto.MerchantIndustryDto;
import com.gl365.app.remote.sales.MerchantService;
import com.gl365.app.service.MerchantFacadeService;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.TimeOutCache;

/**
 * Created by caoyilong on 2017/6/9.
 */
@Service
public class MerchantFacadeServiceImpl implements MerchantFacadeService {

	private static final String GEILE_MERCHANT_SERVICE_QUERY = "GEILE_MERCHANT_SERVICE_QUERY";
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private PicHandler picHandler;
	@Autowired
	private GeiLeMerchantService geiLeMerchantService;
	@Autowired
	private ValidatorFacadeService validatorFacadeService;

	@Value("${validator.uncheck}")
	private String UN_CHECK;

	@Override
	public MerchantHistory queryByRegNo(String regNo) {
		MerchantHistory history = merchantService.queryByRegNo(regNo);
		// 图片转换,增加前缀
		history.setTaxRegPicUrl(picHandler.addPrefixUrl(history.getTaxRegPicUrl()));// 税务登记证
		history.setLicensePicUrl(picHandler.addPrefixUrl(history.getLicensePicUrl()));// 营业执照图片URL
		history.setLegalPersonIdCardFrontPicUrl(picHandler.addPrefixUrl(history.getLegalPersonIdCardFrontPicUrl()));// 法人身份证正面图片URL
		history.setLegalPersonIdCardBackPicUrl(picHandler.addPrefixUrl(history.getLegalPersonIdCardBackPicUrl()));// 法人身份证背面图片URL
		history.setOrgCodePicUrl(picHandler.addPrefixUrl(history.getOrgCodePicUrl()));// 组织机构代码证图片URL
		history.setContractPicUrl(picHandler.addPrefixUrl(history.getContractPicUrl()));// 协议照片URL
		history.setFacadeOfShopPicUrl(picHandler.addPrefixUrl(history.getFacadeOfShopPicUrl()));// 门头照图片URL
		history.setAddressPicUrl(picHandler.addPrefixUrl(history.getAddressPicUrl()));// 地址照图片URL
		history.setScopeOfOperationPicUrl(picHandler.addPrefixUrl(history.getScopeOfOperationPicUrl()));// 经营内容图片URL
		history.setTicketPicUrl(picHandler.addPrefixUrl(history.getTaxRegPicUrl()));// 上传小票图片URL
		history.setPrinlpEngagementLetterPicUrl(picHandler.addPrefixUrl(history.getPrinlpEngagementLetterPicUrl()));// 法人委托书图片URL
		history.setPrinlpIdHoldingPicUrl(picHandler.addPrefixUrl(history.getPrinlpIdHoldingPicUrl()));// 法人持身份证照图片URL

		return history;
	}

	@Override
	public ApiResponse<?> addPos(MerchantAddPosCommand merchantDto) {
		LOG.info("addPos:req:{}", JsonUtils.toJsonString(merchantDto.getMerchantHistory()));
		MerchantHistory history = merchantDto.getMerchantHistory();

		String legalPersonName = history.getLegalPersonName();// 法人名称
		String legalPersoncardId = history.getPublpIdCardNo();// 法人身份证
		String msg = "对公法人";
		if (StringUtils.isBlank(legalPersoncardId)) {
			msg = "对私法人";
			legalPersoncardId = history.getPrilpIdCardNo();
			if (StringUtils.isBlank(legalPersoncardId)) {
				msg = "对私非法人";
				legalPersoncardId = history.getPrinlpIdCardNo();
			}
		}
		
		//若税务登记号为空,则默认赋值营业执照编号,仅限对公用户 ,开户性质:1:对私法人，2对私非法人，3对公
		if (history.getProperty() != null && history.getProperty().intValue() == 3
				&& StringUtils.isBlank(history.getTaxRegCerNo())) {
			history.setTaxRegCerNo(history.getLicenseNo());
		}

		LOG.info("validIdCard {}  card={},name={}", msg, legalPersoncardId, legalPersonName);
		if (StringUtils.isNotBlank(legalPersoncardId) && StringUtils.isNotBlank(legalPersonName)
				&& !validatorFacadeService.validIdCard(legalPersoncardId, legalPersonName)) {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
					"法人" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
		}

		String cardId = history.getPublpIdCardNo();
		String name = history.getPublpAccountName();
		String bankId = history.getPublpAccountNo();
		String isPublic = "1";
		// 0:默认验证所有,1:不验证对公法人,2:不验证对私法人,3:不验证对私非法人
		if (StringUtils.isBlank(bankId)) {
			isPublic = "2";
			cardId = history.getPrilpIdCardNo();
			name = history.getPrilpAccountName();
			bankId = history.getPrilpAccountNo();
			if (StringUtils.isBlank(bankId)) {
				isPublic = "3";
				cardId = history.getPrinlpIdCardNo2();
				name = history.getPrinlpAccountName();
				bankId = history.getPrinlpAccountNo();
			}
		}
		LOG.info("addPos.validator {} begin cardId={}, name={}, bankId={}", msg, cardId, name, bankId);
		if ((StringUtils.isBlank(UN_CHECK)
				|| !UN_CHECK.contains(isPublic)) && StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)) {
			LOG.info("bankId is not public");
			// 验证身份证
			if (!validatorFacadeService.validIdCard(cardId, name)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR);
			}
			// 银行卡身份证认证
			if (StringUtils.isNotBlank(bankId) &&!validatorFacadeService.validBankCard(bankId, cardId, name)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_BANK_ERROR);
			}
		}
		LOG.info("addPos.validator end success");

		// 图片转换
		history.setTaxRegPicUrl(picHandler.generateSaveUrl(history.getTaxRegPicUrl()));// 税务登记证
		history.setLicensePicUrl(picHandler.generateSaveUrl(history.getLicensePicUrl()));// 营业执照图片URL
		history.setLegalPersonIdCardFrontPicUrl(picHandler.generateSaveUrl(history.getLegalPersonIdCardFrontPicUrl()));// 法人身份证正面图片URL
		history.setLegalPersonIdCardBackPicUrl(picHandler.generateSaveUrl(history.getLegalPersonIdCardBackPicUrl()));// 法人身份证背面图片URL
		history.setOrgCodePicUrl(picHandler.generateSaveUrl(history.getOrgCodePicUrl()));// 组织机构代码证图片URL
		history.setContractPicUrl(picHandler.generateSaveUrl(history.getContractPicUrl()));// 协议照片URL
		history.setFacadeOfShopPicUrl(picHandler.generateSaveUrl(history.getFacadeOfShopPicUrl()));// 门头照图片URL
		history.setAddressPicUrl(picHandler.generateSaveUrl(history.getAddressPicUrl()));// 地址照图片URL
		history.setScopeOfOperationPicUrl(picHandler.generateSaveUrl(history.getScopeOfOperationPicUrl()));// 经营内容图片URL
		// 产品说不要
		// history.setTicketPicUrl(picHandler.generateSaveUrl(history.getTicketPicUrl()));//
		// 上传小票图片URL
		history.setPrinlpEngagementLetterPicUrl(picHandler.generateSaveUrl(history.getPrinlpEngagementLetterPicUrl()));// 法人委托书图片URL
		history.setPrinlpIdHoldingPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdHoldingPicUrl()));// 法人持身份证照图片URL

		history.setOpenLicensePicUrl(picHandler.generateSaveUrl(history.getOpenLicensePicUrl()));
		history.setContractNoPicUrl(picHandler.generateSaveUrl(history.getContractNoPicUrl()));
		history.setCashierDeskPicUrl(picHandler.generateSaveUrl(history.getCashierDeskPicUrl()));
		history.setOperateProductPicUrl(picHandler.generateSaveUrl(history.getOperateProductPicUrl()));
		history.setOperateFieldPicUrl(picHandler.generateSaveUrl(history.getOperateFieldPicUrl()));
		history.setPrilpBankFrontPicUrl(picHandler.generateSaveUrl(history.getPrilpBankFrontPicUrl()));
		history.setPrilpBankBackPicUrl(picHandler.generateSaveUrl(history.getPrilpBankBackPicUrl()));
		history.setPrinlpIdCardFrontPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdCardFrontPicUrl()));
		history.setPrinlpIdCardBackPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdCardBackPicUrl()));
		history.setPrinlpBankFrontPicUrl(picHandler.generateSaveUrl(history.getPrinlpBankFrontPicUrl()));
		history.setPrinlpBankBackPicUrl(picHandler.generateSaveUrl(history.getPrinlpBankBackPicUrl()));
		history.setAdditionalContractNoPicUrl(picHandler.generateSaveUrl(history.getAdditionalContractNoPicUrl()));
		
		MerchantApplication application = new MerchantApplication();
		BeanUtils.copyProperties(merchantDto.getApplicationDetail(), application);
		merchantDto.setMerchantApplication(application);
		if(StringUtils.isNotBlank(merchantDto.getMerchantHistory().getBarCode())){
			merchantDto.getMerchantHistory().setBarCodes(Arrays.asList(merchantDto.getMerchantHistory().getBarCode()));
		}
		
		merchantService.addPos(merchantDto);
		return ApiResponse.createSuccess(null);
	}
	
	@Override
	public ApiResponse<?> addLinkMerchant(MerchantAddPosCommand merchantDto) {
		LOG.info("addLinkMerchant:req:{}", JsonUtils.toJsonString(merchantDto));
		boolean ischeck = true;
		for (MerchantHistory history : merchantDto.getMerchantHistoryList()) {
			if (ischeck || 2 == merchantDto.getLinkType()) {
				ischeck = false;

				String cardId = null;
				String name = null;
				// 开户性质:1:对私法人，2对私非法人，3对公
				if (history.getProperty() != null) {
					if (history.getProperty().intValue() == 1) {
						// 开户性质:1:对私法人
						cardId = history.getPrilpIdCardNo();
						name = history.getPrilpAccountName();
						LOG.info("validIdCard 对私法人开户性质:1:对私法人，2对私非法人，3对公{}  card={},name={}", history.getProperty(),
								cardId, name);
						if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)
								&& !validatorFacadeService.validIdCard(cardId, name)) {
							return ApiResponse.createResponseWithMessage(
									ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
									"对私法人" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
						}
					} else if (history.getProperty().intValue() == 2) {
						// 开户性质:2对私非法人
						cardId = history.getPrinlpIdCardNo2();
						name = history.getPrinlpAccountName();
						LOG.info("validIdCard 对私非法人开户性质:1:对私法人，2对私非法人，3对公{}  card={},name={}", history.getProperty(),
								cardId, name);
						if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)
								&& !validatorFacadeService.validIdCard(cardId, name)) {
							return ApiResponse.createResponseWithMessage(
									ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
									"对私非法人" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
						}

						cardId = history.getPrinlpIdCardNo();
						name = history.getLegalPersonName();
						LOG.info("validIdCard 对私法人开户性质:1:对私法人，2对私非法人，3对公{}  card={},name={}", history.getProperty(),
								cardId, name);
						if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)
								&& !validatorFacadeService.validIdCard(cardId, name)) {
							return ApiResponse.createResponseWithMessage(
									ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
									"对私法人" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
						}
					} else if (history.getProperty().intValue() == 3) {
						// 开户性质:3对公
						cardId = history.getBusContactsPerNo();
						name = history.getBusContacts();
						LOG.info("validIdCard 企业联系人 开户性质:1:对私法人，2对私非法人，3对公{}  card={},name={}", history.getProperty(),
								cardId, name);
						if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)
								&& !validatorFacadeService.validIdCard(cardId, name)) {
							return ApiResponse.createResponseWithMessage(
									ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
									"企业联系人" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
						}

						cardId = history.getPublpIdCardNo();
						name = history.getLegalPersonName();
						LOG.info("validIdCard 企业法人代表 开户性质:1:对私法人，2对私非法人，3对公{}  card={},name={}", history.getProperty(),
								cardId, name);
						if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(name)
								&& !validatorFacadeService.validIdCard(cardId, name)) {
							return ApiResponse.createResponseWithMessage(
									ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getCode(),
									"企业法人代表" + ReturnCode.AgentUser.VALID_ID_CARD_ERROR.getMsg());
						}
					}
				}
			}
			LOG.info("addLinkMerchant.validator end success");

			// 图片转换
			history.setTaxRegPicUrl(picHandler.generateSaveUrl(history.getTaxRegPicUrl()));// 税务登记证
			history.setLicensePicUrl(picHandler.generateSaveUrl(history.getLicensePicUrl()));// 营业执照图片URL
			history.setLegalPersonIdCardFrontPicUrl(picHandler.generateSaveUrl(history.getLegalPersonIdCardFrontPicUrl()));// 法人身份证正面图片URL
			history.setLegalPersonIdCardBackPicUrl(picHandler.generateSaveUrl(history.getLegalPersonIdCardBackPicUrl()));// 法人身份证背面图片URL
			history.setOrgCodePicUrl(picHandler.generateSaveUrl(history.getOrgCodePicUrl()));// 组织机构代码证图片URL
			history.setContractPicUrl(picHandler.generateSaveUrl(history.getContractPicUrl()));// 协议照片URL
			history.setFacadeOfShopPicUrl(picHandler.generateSaveUrl(history.getFacadeOfShopPicUrl()));// 门头照图片URL
			history.setAddressPicUrl(picHandler.generateSaveUrl(history.getAddressPicUrl()));// 地址照图片URL
			history.setScopeOfOperationPicUrl(picHandler.generateSaveUrl(history.getScopeOfOperationPicUrl()));// 经营内容图片URL
			// 产品说不要
			// history.setTicketPicUrl(picHandler.generateSaveUrl(history.getTicketPicUrl()));//
			// 上传小票图片URL
			history.setPrinlpEngagementLetterPicUrl(picHandler.generateSaveUrl(history.getPrinlpEngagementLetterPicUrl()));// 法人委托书图片URL
			history.setPrinlpIdHoldingPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdHoldingPicUrl()));// 法人持身份证照图片URL

			history.setOpenLicensePicUrl(picHandler.generateSaveUrl(history.getOpenLicensePicUrl()));
			history.setContractNoPicUrl(picHandler.generateSaveUrl(history.getContractNoPicUrl()));
			history.setCashierDeskPicUrl(picHandler.generateSaveUrl(history.getCashierDeskPicUrl()));
			history.setOperateProductPicUrl(picHandler.generateSaveUrl(history.getOperateProductPicUrl()));
			history.setOperateFieldPicUrl(picHandler.generateSaveUrl(history.getOperateFieldPicUrl()));
			history.setPrilpBankFrontPicUrl(picHandler.generateSaveUrl(history.getPrilpBankFrontPicUrl()));
			history.setPrilpBankBackPicUrl(picHandler.generateSaveUrl(history.getPrilpBankBackPicUrl()));
			history.setPrinlpIdCardFrontPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdCardFrontPicUrl()));
			history.setPrinlpIdCardBackPicUrl(picHandler.generateSaveUrl(history.getPrinlpIdCardBackPicUrl()));
			history.setPrinlpBankFrontPicUrl(picHandler.generateSaveUrl(history.getPrinlpBankFrontPicUrl()));
			history.setPrinlpBankBackPicUrl(picHandler.generateSaveUrl(history.getPrinlpBankBackPicUrl()));
			history.setAdditionalContractNoPicUrl(picHandler.generateSaveUrl(history.getAdditionalContractNoPicUrl()));
			
			MerchantApplication application = new MerchantApplication();
			BeanUtils.copyProperties(merchantDto.getApplicationDetail(), application);
			merchantDto.setMerchantApplication(application);
		}

		merchantService.addLinkMerchant(merchantDto);
		return ApiResponse.createSuccess(null);
	}

	@Override
	public List<MerchantIndustry> queryMerchantIndustry() {
		List<MerchantIndustryDto.MerchantIndustryResp> areas = getMerchantIndustryResp();

		areas.sort((r1, r2) -> {
			if (r1.getCategoryEvel().equals(r2.getCategoryEvel())) {
				return r1.getCategoryId() - r2.getCategoryId();
			} else {
				return r1.getCategoryEvel() - r2.getCategoryEvel();
			}
		});
		HashMap<Integer, MerchantIndustry> parentMap = new HashMap<>(areas.size());
		List<MerchantIndustry> level1Industry = areas.stream().map(r -> {
			MerchantIndustry industry = new MerchantIndustry();
			industry.setCategoryId(r.getCategoryId());
			industry.setCategoryName(r.getCategoryName());
			
			industry.setMinGlFeeRate(r.getMinGlFeeRate());
			industry.setMaxGlFeeRate(r.getMaxGlFeeRate());
			
			industry.setMinSaleRate(r.getMinSaleRate());
			industry.setMaxSaleRate(r.getMaxSaleRate());
			
			if (r.getCategoryEvel() > 1) {
				MerchantIndustry parent = parentMap.get(r.getParentCategoryId());
				parent.getSubCategory().add(industry);
			}
			parentMap.put(r.getCategoryId(), industry);

			industry = r.getCategoryEvel() == 1 ? industry : null;
			return industry;
		}).filter(Objects::nonNull).collect(Collectors.toList());
		return level1Industry;
	}

	@Override
	public Map<Integer, String> getIndustriesIdMap() {
		List<MerchantIndustryDto.MerchantIndustryResp> areas = getMerchantIndustryResp();
		Map<Integer, String> mapping = areas.stream()
				.collect(Collectors.toMap(MerchantIndustryDto.MerchantIndustryResp::getCategoryId,
						MerchantIndustryDto.MerchantIndustryResp::getCategoryName));
		return mapping;
	}

	private List<MerchantIndustryDto.MerchantIndustryResp> getMerchantIndustryResp() {
		List<MerchantIndustryDto.MerchantIndustryResp> areas = (List<MerchantIndustryDto.MerchantIndustryResp>) TimeOutCache
				.getInstance().get(GEILE_MERCHANT_SERVICE_QUERY);
		if (CollectionUtils.isEmpty(areas)) {
			MerchantIndustryDto response = geiLeMerchantService.query();
			if (response != null) {
				areas = response.getData();
				TimeOutCache.getInstance().put(GEILE_MERCHANT_SERVICE_QUERY, areas);
			}
		}
		return areas == null ? new ArrayList<>() : areas;
	}
}
