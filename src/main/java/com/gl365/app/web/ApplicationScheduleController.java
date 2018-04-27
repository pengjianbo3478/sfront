package com.gl365.app.web;

import com.gl365.app.aspect.Log;
import com.gl365.app.dto.*;
import com.gl365.app.dto.command.*;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.enum_type.ApplicationFlowStatus;
import com.gl365.app.dto.enum_type.ApplicationStatus;
import com.gl365.app.dto.merchant.NoBenefitMerchantApplicationDto;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.gd.GdServiceImpl;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.ApiRequest.ApplicationScheduleQueryConditions;
import com.gl365.app.remote.sales.ApiRequest.FindStatusNumByConditions;
import com.gl365.app.remote.sales.ApplicationScheduleService;
import com.gl365.app.service.MerchantFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by wangmeihua on 2017/6/2. 申请进度查询
 */
@Api(description = "进度查询相关", tags = "SCHEDULE")
@RestController
@RequestMapping("/api/applicationSchedule")
public class ApplicationScheduleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationScheduleController.class);
	@Autowired
	private ApplicationScheduleService applicationScheduleService;

	@Autowired
	private AgentService agentService;
	@Autowired
	private GdServiceImpl gdServiceImpl;
	@Autowired
	private MerchantFacadeService merchantFacadeService;
	@Autowired
	private PicHandler picHandler;

	//@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("查询当前用户申请进度列表")
	@RequestMapping(value = "/findAgentApplications", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<FindAgentApplicationsListDto> findAgentApplications(
			@RequestBody ApiRequest<ApplicationScheduleQueryCommand> request) {
		logger.info("查询当前用户申请进度列表:request={}", JsonUtils.toJsonString(request));
		Map<Integer, String> mapping = gdServiceImpl.getAllAreaById1();

		AgentLoginDto agentLoginDto = getCurrentUser();
		String userId = agentLoginDto.getId();
		ApplicationScheduleQueryCommand command = request.getData();
		ApplicationScheduleQueryConditions conditions = new ApplicationScheduleQueryConditions();
		// conditions.setApplicantIds(getApplicantIdByAgent(userId,
		// command.getTeam()));
		if (command.getTeam()) {
			Agent currentUser = agentService.findById(userId);
			if (currentUser != null && currentUser.getAgentType() != AgentType.PERSONAL) {
				conditions.setUpstreamAgentId(userId);
			}
		}

		if (StringUtils.isBlank(conditions.getUpstreamAgentId())) {
			conditions.setApplicantIds(new String[] { userId });
		}

		conditions.setKeyword(command.getKeyword());
		// 前端对应：全部：0；待审核：1；审核中：2；通过：3；拒绝：4；撤回：5。
		if (command.getStatus() == 1) {
			conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.PERSONAL_ASSESSMENT_PENDING });
		} else if (command.getStatus() == 2) {
			if (agentLoginDto.getAgentType() != AgentType.CITY) {
				conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.NEW,
						ApplicationStatus.MP_RISK_ASSESSMENT_PASSED, ApplicationStatus.MP_OPERATOR_ASSESSMENT_PASSED,
						ApplicationStatus.PERSONAL_ASSESSMENT_PENDING });
			} else {
				conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.NEW,
						ApplicationStatus.MP_RISK_ASSESSMENT_PASSED, ApplicationStatus.MP_OPERATOR_ASSESSMENT_PASSED });
			}
		} else if (command.getStatus() == 3) {
			conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.PASSED,
					ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_PASSED });
		} else if (command.getStatus() == 4) {
			conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.PERSONAL_ASSESSMENT_REJECTED,
					ApplicationStatus.MP_RISK_ASSESSMENT_REJECTED, ApplicationStatus.MP_OPERATOR_ASSESSMENT_REJECTED,
					ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_REJECTED, ApplicationStatus.REJECTED });
		} else if (command.getStatus() == 5) {
			conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.REVOKED });
		}
		if (StringUtils.isNotBlank(command.getLinkMerchantId())) {
			//conditions = new ApplicationScheduleQueryConditions();
			conditions.setLinkType(1);
			conditions.setLinkMerchantId(command.getLinkMerchantId());
		} else {
			conditions.setLinkType(0);
		}
		FindAgentApplicationsListDto data = applicationScheduleService.findAgentApplications(conditions,
				request.getPageNo(), request.getPageSize());

		for (ApplicationSimple simple : data.getList()) {
			AgentSimple as = simple.getAgent();
			if (null != as) {
				as.setCityDesc(mapping.get(as.getCity()));
				as.setProvinceDesc(mapping.get(as.getProvince()));
				as.setDistrictDesc(mapping.get(as.getDistrict()));
				as.setAvatarUrl(picHandler.addPrefixUrl(as.getAvatarUrl()));
				simple.setAgent(as);
			}

			MerchantApplicationSimple mas = simple.getMerchantApplicationDetails();
			if (null != mas) {
				MerchantSimple ms = simple.getMerchantApplicationDetails().getMerchant();
				if (null != ms) {
					ms.setCityDesc(mapping.get(ms.getCity()));
					ms.setProvinceDesc(mapping.get(ms.getProvince()));
					ms.setDistrictDesc(mapping.get(ms.getDistrict()));
					mas.setMerchant(ms);
					simple.setMerchantApplicationDetails(mas);
				}

			}
			
			NoBenefitMerchantApplicationDto bm = simple.getNoBenefitMerchant();
			if (null != bm) {
				bm.setCityDesc(mapping.get(bm.getCity()));
				bm.setProvinceDesc(mapping.get(bm.getProvince()));
				bm.setDistrictDesc(mapping.get(bm.getDistrict()));
			}

		}
		return ApiPageResponse.createSuccess(data);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("查询是否有待审核记录，是否通知")
	@RequestMapping(value = "/hasApplicationMessage", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<Map<String, Boolean>> hasApplicationMessage(@RequestBody ApplicationMsgDto request) {
		logger.info("查询是否有待审核记录，是否通知:request={}", JsonUtils.toJsonString(request));
		AgentLoginDto agentLoginDto = getCurrentUser();
		String userId = agentLoginDto.getId();
		boolean flag = false;
		if (agentLoginDto.getAgentType() == AgentType.CITY) {
			ApplicationScheduleQueryConditions conditions = new ApplicationScheduleQueryConditions();
			conditions.setUpstreamAgentId(userId);
			conditions.setStatuses(new ApplicationStatus[] { ApplicationStatus.PERSONAL_ASSESSMENT_PENDING });

			FindAgentApplicationsListDto data = applicationScheduleService.findAgentApplications(conditions, 1, 1);
			if (null != request && null != request.getTime()) {
				if (CollectionUtils.isNotEmpty(data.getList())
						&& request.getTime().isBefore(data.getList().get(0).getCreationTime())) {
					flag = true;
				}
			} else {
				flag = CollectionUtils.isNotEmpty(data.getList());
			}

			if (!flag) {
				ApplicationScheduleQueryConditions conditionsT = new ApplicationScheduleQueryConditions();
				conditionsT.setApplicantIds(new String[] { userId });
				conditionsT.setStatuses(new ApplicationStatus[] { ApplicationStatus.PERSONAL_ASSESSMENT_PENDING });

				FindAgentApplicationsListDto dataT = applicationScheduleService.findAgentApplications(conditionsT, 1,
						1);
				if (null != request && null != request.getTime()) {
					if (CollectionUtils.isNotEmpty(dataT.getList())
							&& request.getTime().isBefore(dataT.getList().get(0).getCreationTime())) {
						flag = true;
					}
				} else {
					flag = CollectionUtils.isNotEmpty(dataT.getList());
				}
			}
		}

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("hasMsg", flag);
		return ApiPageResponse.createTypedSuccess(map);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("查询不同状态的申请数量")
	@RequestMapping(value = "/findStatsNumByConditions", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<List<ApplicationStatusCountDto>> findStatsNumByConditions(
			@RequestBody FindStatsNumByCommand request) {
		AgentLoginDto agentLoginDto = getCurrentUser();
		String userId = agentLoginDto.getId();
		FindStatusNumByConditions conditions = new FindStatusNumByConditions();
		// conditions.setApplicantIds(getApplicantIdByAgent(userId,
		// request.getTeam()));
		if (request.getTeam()) {
			Agent currentUser = agentService.findById(userId);
			if (currentUser != null && currentUser.getAgentType() != AgentType.PERSONAL) {
				conditions.setUpstreamAgentId(userId);
			}
		}

		if (StringUtils.isBlank(conditions.getUpstreamAgentId())) {
			conditions.setApplicantIds(new String[] { userId });
		}

		Map<String, Integer> data = applicationScheduleService.findStatsNumByConditions(conditions);

		// 根据状态判断做对应状态输出调整
		List<ApplicationStatusCountDto> counts = new ArrayList<>(5);
		counts.add(new ApplicationStatusCountDto(1));
		counts.add(new ApplicationStatusCountDto(2));
		counts.add(new ApplicationStatusCountDto(3));
		counts.add(new ApplicationStatusCountDto(4));
		counts.add(new ApplicationStatusCountDto(5));
		// 前端对应：全部：0；待审核：1；审核中：2；通过：3；拒绝：4；撤回：5。
		for (Map.Entry<String, Integer> statusCount : data.entrySet()) {
			String status = statusCount.getKey();
			Integer count = statusCount.getValue();
			switch (ApplicationStatus.valueOf(status)) {
			case PERSONAL_ASSESSMENT_PENDING:
				counts.get(0).setCount(counts.get(0).getCount() + count);
				break;
			case NEW:
			case MP_RISK_ASSESSMENT_PASSED:
			case MP_OPERATOR_ASSESSMENT_PASSED:
				counts.get(1).setCount(counts.get(1).getCount() + count);
				break;
			case PASSED:
			case MP_PAYMENT_COMPANY_ASSESSMENT_PASSED:
				counts.get(2).setCount(counts.get(2).getCount() + count);
				break;
			case PERSONAL_ASSESSMENT_REJECTED:
			case MP_RISK_ASSESSMENT_REJECTED:
			case MP_OPERATOR_ASSESSMENT_REJECTED:
			case MP_PAYMENT_COMPANY_ASSESSMENT_REJECTED:
			case REJECTED:
				counts.get(3).setCount(counts.get(3).getCount() + count);
				break;
			case REVOKED:
				counts.get(4).setCount(counts.get(4).getCount() + count);
				break;
			}
		}

		if (agentLoginDto.getAgentType() != AgentType.CITY) {
			counts.get(1).setCount(counts.get(1).getCount() + counts.get(0).getCount());
			counts.get(0).setCount(0);
		}
		return ApiPageResponse.createSuccess(counts);
	}

	private String[] getApplicantIdByAgent(String applicantId, Boolean team) {
		if (team == null || !team) {
			if (applicantId != null) {
				return new String[] { applicantId };
			}
		} else {
			if (applicantId != null) {
				Agent currentUser = agentService.findById(applicantId);
				// 业务员不允许查询团队成员
				if (currentUser != null && currentUser.getAgentType() != AgentType.PERSONAL) {
					FindMyAgentPartnerCommand findMyAgentPartnerCommand = new FindMyAgentPartnerCommand();
					findMyAgentPartnerCommand.setAgentType(AgentType.PERSONAL);
					findMyAgentPartnerCommand.setUpstreamAgentId(applicantId);
					List<Agent> agents = agentService.findMyAgentPartnerList(findMyAgentPartnerCommand);
					if (agents != null && !agents.isEmpty()) {
						String[] applicantIds = new String[agents.size()];
						for (int i = 0, size = agents.size(); i < size; i++) {
							applicantIds[i] = agents.get(i).getId();
						}
						return applicantIds;
					}
				} else {
					return new String[] { applicantId };
				}
			}
		}
		return null;
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("申请通过操作")
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<Void> approveApplication(@RequestBody ApplicationApproveRequest request) {
		String agentId = getCurrentLoginUserId();
		request.setAgentId(agentId);
		
		if(CollectionUtils.isNotEmpty(request.getPartnerContractPicUrls())){
			List<String>  picUrls= new ArrayList<String>();
			for (String string : request.getPartnerContractPicUrls()) {
				picUrls.add(picHandler.generateSaveUrl(string));
			}
			request.setPartnerContractPicUrls(picUrls);
		}
		
		request.setPartnerContractPicUrl(picHandler.generateSaveUrl(request.getPartnerContractPicUrl()));
		
		applicationScheduleService.approveApplication(request);
		return ApiResponse.createNoErrorResponse(null);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("申请拒绝操作")
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<Void> rejectApplication(@RequestBody ApplicationApproveRequest request) {
		String agentId = getCurrentLoginUserId();
		request.setAgentId(agentId);
		applicationScheduleService.rejectApplication(request);
		return ApiResponse.createNoErrorResponse(null);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("申请撤回操作")
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<Void> withdrawApplication(@RequestBody ApplicationApproveRequest request) {
		String agentId = getCurrentLoginUserId();
		request.setAgentId(agentId);
		applicationScheduleService.withdrawApplication(request);
		return ApiResponse.createNoErrorResponse(null);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("获取商户申请（机具申请）详情，用于再次进件和重新申请")
	@RequestMapping(value = "/getMerchantApplicationDetail", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<MerchantApplicationFullDetailsDto> getMerchantApplicationDetail(
			@RequestBody GetMerchantApplicationDetailRequest request) {
		String agentId = getCurrentLoginUserId();
		request.setAgentId(agentId);
		MerchantApplicationFullDetailsDto data = applicationScheduleService.getMerchantApplicationDetail(request);
		if (null != data) {
			if (null != data.getMerchant()) {
				Merchant m = data.getMerchant();
				Map<Integer, String> mapping = merchantFacadeService.getIndustriesIdMap();
				if (null != data.getMerchant().getIndustry()) {
					m.setIndustryName(mapping.get(data.getMerchant().getIndustry()));
				}

				if (null != data.getMerchant().getSubIndustry()) {
					m.setSubIndustryName(mapping.get(data.getMerchant().getSubIndustry()));
				}
				// 图片转换,增加前缀
				data.setTaxRegPicUrl(picHandler.addPrefixUrl(data.getTaxRegPicUrl()));
				data.setLicensePicUrl(picHandler.addPrefixUrl(data.getLicensePicUrl()));
				data.setLegalPersonIdCardFrontPicUrl(picHandler.addPrefixUrl(data.getLegalPersonIdCardFrontPicUrl()));
				data.setLegalPersonIdCardBackPicUrl(picHandler.addPrefixUrl(data.getLegalPersonIdCardBackPicUrl()));
				data.setOrgCodePicUrl(picHandler.addPrefixUrl(data.getOrgCodePicUrl()));
				data.setContractPicUrl(picHandler.addPrefixUrl(data.getContractPicUrl()));
				data.setFacadeOfShopPicUrl(picHandler.addPrefixUrl(data.getFacadeOfShopPicUrl()));
				data.setAddressPicUrl(picHandler.addPrefixUrl(data.getAddressPicUrl()));
				data.setScopeOfOperationPicUrl(picHandler.addPrefixUrl(data.getScopeOfOperationPicUrl()));
				data.setTicketPicUrl(picHandler.addPrefixUrl(data.getTicketPicUrl()));
				data.setPrinlpEngagementLetterPicUrl(picHandler.addPrefixUrl(data.getPrinlpEngagementLetterPicUrl()));
				data.setPrinlpIdHoldingPicUrl(picHandler.addPrefixUrl(data.getPrinlpIdHoldingPicUrl()));

				data.setOpenLicensePicUrl(picHandler.addPrefixUrl(data.getOpenLicensePicUrl()));
				data.setContractNoPicUrl(picHandler.addPrefixUrl(data.getContractNoPicUrl()));
				data.setCashierDeskPicUrl(picHandler.addPrefixUrl(data.getCashierDeskPicUrl()));
				data.setOperateProductPicUrl(picHandler.addPrefixUrl(data.getOperateProductPicUrl()));
				data.setOperateFieldPicUrl(picHandler.addPrefixUrl(data.getOperateFieldPicUrl()));
				data.setPrilpBankFrontPicUrl(picHandler.addPrefixUrl(data.getPrilpBankFrontPicUrl()));
				data.setPrilpBankBackPicUrl(picHandler.addPrefixUrl(data.getPrilpBankBackPicUrl()));
				data.setPrinlpIdCardFrontPicUrl(picHandler.addPrefixUrl(data.getPrinlpIdCardFrontPicUrl()));
				data.setPrinlpIdCardBackPicUrl(picHandler.addPrefixUrl(data.getPrinlpIdCardBackPicUrl()));
				data.setPrinlpBankFrontPicUrl(picHandler.addPrefixUrl(data.getPrinlpBankFrontPicUrl()));
				data.setPrinlpBankBackPicUrl(picHandler.addPrefixUrl(data.getPrinlpBankBackPicUrl()));
				data.setAdditionalContractNoPicUrl(picHandler.addPrefixUrl(data.getAdditionalContractNoPicUrl()));
				// data.setAdditionalContractNoPicUrl(picHandler.generateSaveUrl(data.getAdditionalContractNoPicUrl()));
				if (CollectionUtils.isNotEmpty(data.getBarCodes())) {
					data.setBarCode(data.getBarCodes().get(0));
				}

			}

		}
		return ApiResponse.createSuccess(data);

	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation(value = "机具申请详细进度")
	@RequestMapping(value = "/getMerchantScheduleFlow", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<List<ApplicationFlowDto>> getMerchantScheduleFlow(
			@RequestBody GetMerchantApplicationDetailRequest request) {
		List<ApplicationFlowDto> result = new LinkedList<ApplicationFlowDto>();
		List<MerchantScheduleFlow> data = applicationScheduleService.getMerchantScheduleFlow(request);

		// 在新申请中增加pos机详情
		ApplicationPosDto posDetail = applicationScheduleService.getPosDetailForApply(request);

		ApplicationFlowDto passDto = new ApplicationFlowDto();
		ApplicationFlowDto applyDto = new ApplicationFlowDto();
		ApplicationFlowDto auditDto = new ApplicationFlowDto();

		if (CollectionUtils.isNotEmpty(data)) {
			MerchantScheduleFlow applyflow = data.get(0);
			applyDto.setStatus(ApplicationFlowStatus.APPLY);
			applyDto.setCreationTime(applyflow.getCreationTime());
			applyDto.setAgentMobile(applyflow.getAgentMobile());
			applyDto.setAgentName(applyflow.getAgentName());
			applyDto.setMerchantSn(applyflow.getMerchantSn());
			// 在新申请中增加pos机详情
			applyDto.setCount(posDetail.getCount());
			applyDto.setPosDetilList(posDetail.getPosDetilList());
			applyDto.setMerchantPayAccounts(posDetail.getMerchantPayAccounts());
			applyDto.setBarCodeList(posDetail.getBarCodeList());
			MerchantScheduleFlow flow = data.get(data.size() - 1);
			ApplicationStatus currentStatus = flow.getCurrentStatus();
			if (currentStatus == ApplicationStatus.PERSONAL_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_RISK_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_OPERATOR_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_PASSED
					|| currentStatus == ApplicationStatus.PASSED || currentStatus == ApplicationStatus.REJECTED
					|| currentStatus == ApplicationStatus.REVOKED) {

				passDto.setCreationTime(flow.getCreationTime());
				if (currentStatus == ApplicationStatus.PASSED
						|| currentStatus == ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_PASSED) {
					passDto.setStatus(ApplicationFlowStatus.PASS);
				} else if (currentStatus == ApplicationStatus.REVOKED) {
					passDto.setStatus(ApplicationFlowStatus.WITHDRAW);
				} else {
					passDto.setComments(flow.getComments());
					passDto.setStatus(ApplicationFlowStatus.REFUSE);
				}

				auditDto.setStatus(ApplicationFlowStatus.AUDIT);
				if (data.size() > 2) {
					auditDto.setCreationTime(data.get(data.size() - 2).getCreationTime());
				} else {
					auditDto.setCreationTime(flow.getCreationTime());
				}

			} else if (data.size() > 1) {
				auditDto.setStatus(ApplicationFlowStatus.AUDIT);
				auditDto.setCreationTime(flow.getCreationTime());

				passDto.setStatus(ApplicationFlowStatus.AUDIT);
				passDto.setCreationTime(flow.getCreationTime());
			}

			if (null != applyDto.getStatus()) {
				result.add(applyDto);
			}

			if (null != auditDto.getStatus()) {
				result.add(auditDto);
			}

			if (null != passDto.getStatus()) {
				result.add(passDto);
			}
		}

		return ApiResponse.createSuccess(result);
	}

	@Permission(permission = PermissionConstant.LOGIN)
	@ApiOperation("业务员或代理商申请详细进度")
	@RequestMapping(value = "/getAgentScheduleFlow", method = RequestMethod.POST)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	public ApiResponse<List<ApplicationFlowDto>> getAgentScheduleFlow(
			@RequestBody GetMerchantApplicationDetailRequest request) {
		List<ApplicationFlowDto> result = new LinkedList<ApplicationFlowDto>();
		List<AgentScheduleFlow> data = applicationScheduleService.getAgentScheduleFlow(request);
		ApplicationFlowDto applyDto = new ApplicationFlowDto();
		ApplicationFlowDto auditDto = new ApplicationFlowDto();
		ApplicationFlowDto passDto = new ApplicationFlowDto();

		if (CollectionUtils.isNotEmpty(data)) {
			AgentScheduleFlow applyflow = data.get(0);
			applyDto.setStatus(ApplicationFlowStatus.APPLY);
			applyDto.setCreationTime(applyflow.getCreationTime());
			applyDto.setAgentMobile(applyflow.getAgentMobile());
			applyDto.setAgentName(applyflow.getAgentName());
			applyDto.setUpstreamAgentName(applyflow.getUpstreamAgentName());
			applyDto.setPartnerContractPicUrl(picHandler.addPrefixUrl(applyflow.getPartnerContractPicUrl()));
			List<String> picUrlList = new ArrayList<String>();
			if(StringUtils.isNotBlank(applyflow.getPartnerContractPicUrl())){
				picUrlList.add(picHandler.addPrefixUrl(applyflow.getPartnerContractPicUrl()));
			}
			if(StringUtils.isNotBlank(applyflow.getPartnerContractPicUrl1())){
				picUrlList.add(picHandler.addPrefixUrl(applyflow.getPartnerContractPicUrl1()));
			}
			if(StringUtils.isNotBlank(applyflow.getPartnerContractPicUrl2())){
				picUrlList.add(picHandler.addPrefixUrl(applyflow.getPartnerContractPicUrl2()));
			}
			if(StringUtils.isNotBlank(applyflow.getPartnerContractPicUrl3())){
				picUrlList.add(picHandler.addPrefixUrl(applyflow.getPartnerContractPicUrl3()));
			}

			applyDto.setPartnerContractPicUrls(picUrlList);
			AgentScheduleFlow flow = data.get(data.size() - 1);
			ApplicationStatus currentStatus = flow.getCurrentStatus();
			if (currentStatus == ApplicationStatus.PERSONAL_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_RISK_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_OPERATOR_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.MP_PAYMENT_COMPANY_ASSESSMENT_REJECTED
					|| currentStatus == ApplicationStatus.PASSED || currentStatus == ApplicationStatus.REJECTED
					|| currentStatus == ApplicationStatus.REVOKED) {

				passDto.setCreationTime(flow.getCreationTime());
				if (currentStatus == ApplicationStatus.PASSED) {
					passDto.setStatus(ApplicationFlowStatus.PASS);
				} else if (currentStatus == ApplicationStatus.REVOKED) {
					passDto.setStatus(ApplicationFlowStatus.WITHDRAW);
				} else {
					passDto.setComments(flow.getComments());
					passDto.setStatus(ApplicationFlowStatus.REFUSE);
				}

				auditDto.setStatus(ApplicationFlowStatus.AUDIT);
				if (data.size() > 2) {
					auditDto.setCreationTime(data.get(data.size() - 2).getCreationTime());
				} else {
					auditDto.setCreationTime(flow.getCreationTime());
				}

			} else if (data.size() > 1) {
				auditDto.setStatus(ApplicationFlowStatus.AUDIT);
				auditDto.setCreationTime(flow.getCreationTime());
			}
		}

		if (null != applyDto && null != applyDto.getStatus()) {
			result.add(applyDto);
		}
		if (null != auditDto && null != auditDto.getStatus()) {
			result.add(auditDto);
		}
		if (null != passDto && null != passDto.getStatus()) {
			result.add(passDto);
		}
		return ApiResponse.createSuccess(result);
	}

	@GetMapping("progress/detail")
	@ApiOperation("查询进度明细")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public ApiResponse getMerchantProgressDetail(@RequestParam("applicationId") String applicationId) {
		Map<Integer, String> mapping = gdServiceImpl.getAllAreaById1();
		GetMerchantApplicationDetailRequest request = new GetMerchantApplicationDetailRequest();
		String agentId = getCurrentLoginUserId();
		request.setAgentId(agentId);
		request.setApplicationId(applicationId);
		MerchantApplicationFullDetailsDto progress = applicationScheduleService.getMerchantApplicationDetail(request);
		if (progress == null) {
			return ApiResponse.createErrorResponse("进度信息不存在");
		}
		Merchant merchant = progress.getMerchant();
		if (merchant == null) {
			return ApiResponse.createErrorResponse("商户不存在");
		}
		MerchantProgressDetail detail = new MerchantProgressDetail();
		detail.setMerchantName(merchant.getName());
		detail.setMerchantSn(merchant.getMerchantSn());
		detail.setProvinceDesc(mapping.get(merchant.getProvince()));
		detail.setCityDesc(mapping.get(merchant.getCity()));
		detail.setDistrictDesc(merchant.getDetailedAddress());
		detail.setContactMobile(merchant.getContactMobile());
		detail.setContactName(merchant.getContactName());
		detail.setApplyForQrcode(progress.getApplyForQrcode());
		detail.setNumImmobileDevice(progress.getNumImmobileDevice());
		detail.setNumMobileDevice(progress.getNumMobileDevice());
		detail.setNumSmartDevice(progress.getNumSmartDevice());
		detail.setAvatarUrl(picHandler.addPrefixUrl(merchant.getAgent().getAvatarUrl()));
		return ApiResponse.createSuccess(detail);
	}

	@GetMapping("findLinkMerchantByAgentId")
	@ApiOperation("查询连锁商家列表")
	@Permission(permission = PermissionConstant.LOGIN)
	@Log
	public ApiResponse<LinkMerchantListDto> getLinkMerchant(
			@RequestParam(value = "merchantStatus", required = false) Integer merchantStatus,
			@RequestParam(value = "linkType", required = false) Integer linkType ,
			@RequestParam(value = "keyword", required = false) String keyword ,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		return ApiResponse.createSuccess(applicationScheduleService.findLinkMerchantByAgentId(
				new LinkMerchantDto(getCurrentLoginUserId(), merchantStatus,keyword,linkType), pageNo, pageSize));
	}

}
