package com.gl365.app.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.app.dto.Agent;
import com.gl365.app.dto.AgentIndexDto;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.MyRankingDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.AgentIndexQueryConditions;
import com.gl365.app.dto.command.AgentRegisterCommand;
import com.gl365.app.dto.command.IndexCommand;
import com.gl365.app.dto.enum_type.AgentStatus;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.enum_type.InvitePartnerType;
import com.gl365.app.dto.sms.SendSMSType;
import com.gl365.app.dto.sms.VerifySMSReq;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.sales.SmsService;
import com.gl365.app.service.AgentFacadeService;
import com.gl365.app.service.ValidatorFacadeService;
import com.gl365.app.utils.JsonUtils;

/**
 * 代理注册相关 Created by caoyilong on 2017/6/7.
 */
@Service
public class AgentFacadeServiceImpl implements AgentFacadeService {
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AgentService agentService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private ValidatorFacadeService validatorFacadeService;

	@Override
	public ApiResponse registerAgent(AgentRegisterCommand agentRegisterDto) {
		LOG.info("registerAgent > > >agentRegisterDto:{}", JsonUtils.toJsonString(agentRegisterDto));
		// 1:进行验证码验证
		VerifySMSReq verifySMSReq = new VerifySMSReq();
		// 类型为注册
		verifySMSReq.setType(SendSMSType.REGISTER);
		verifySMSReq.setMobileNo(agentRegisterDto.getMobile());
		verifySMSReq.setVerifyCode(agentRegisterDto.getVerifyCode());
		// 验证验证码
		ApiResponse apiResponse1 = smsService.verifyCode(verifySMSReq);
		if (!apiResponse1.getCode().equals(ReturnCode.System.SUCCESS.getCode())) {
			return apiResponse1;
		}
		// 2:进行实名认证
		if (!validatorFacadeService.validIdCard(agentRegisterDto.getIdCardNo(), agentRegisterDto.getName())) {
			LOG.error("实名认证不通过, idCardNo:" + agentRegisterDto.getIdCardNo() + "name: " + agentRegisterDto.getName());
			// 实名认证不通过
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_ID_CARD_ERROR);
		}
		// 3:验证手机号
		if (!validatorFacadeService.validMobile(agentRegisterDto.getMobile(), agentRegisterDto.getIdCardNo(),
				agentRegisterDto.getName())) {
			// TODO 手机验证不通过,存哪里？？？
			LOG.warn("手机认证未通过 param={}", JsonUtils.toJsonString(agentRegisterDto));
			// return
			// ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.VALID_MOBILE_ERROR);
		}
		Agent agent = agentService.findByMobile(agentRegisterDto.getMobile());
		if (agent != null && agent.getStatus() != AgentStatus.UNPASS) {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.PHONE_ALREADY_REGISTER_ERROR);
		}

		/*
		 * Agent agent1 =
		 * agentService.findByIdCardNo(agentRegisterDto.getIdCardNo()); if
		 * (agent1 != null && agent.getStatus() == AgentStatus.APPROVED ) {
		 * return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.
		 * ID_CARD_ALREADY_REGISTER_ERROR); }
		 */
		// 查找推广的代理人
		Agent upstreamAgent = agentService.findById(agentRegisterDto.getUpstreamAgentId());
		if (upstreamAgent == null) {
			return ApiResponse.createResponseWithMessage(ReturnCode.AgentUser.NO_AGENT_INFO_ERROR);
		}
		// 进行合法性判断
		ApiResponse apiResponse = assertAgent(upstreamAgent, agentRegisterDto);
		if (null != apiResponse) {
			return apiResponse;
		}
		Agent newAgent = formatAgent(agentRegisterDto, upstreamAgent);
		agentService.create(newAgent);
		return ApiResponse.createNoErrorResponse(null);
	}

	/**
	 * 判断上级代理是否合理和参数是否合法 TODO:上级可以发展的用户是否合法
	 */
	private ApiResponse assertAgent(Agent upstreamAgent, AgentRegisterCommand agentRegisterDto) {
		
		// 不能发展用户
		if (InvitePartnerType.USER.equals(agentRegisterDto.getRegisterType())) {
			return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
		}
		// 注册用户为县代理的时候必须要市代理
		else if (InvitePartnerType.COUNTY.equals(agentRegisterDto.getRegisterType())) {
			// 非市级代理不能发展县级代理
			if (!upstreamAgent.getAgentType().equals(AgentType.CITY)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NO_PERMISSION);
			}
			// 发展县级代理需要代理区域id
			if (agentRegisterDto.getActingAreaId() == null) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
			}
		}
		return null;
	}

	private Agent formatAgent(AgentRegisterCommand agentRegisterDto, Agent upstreamAgent) {
		Agent agent = new Agent();
		agent.setCompanyName(agentRegisterDto.getCompanyName());
		agent.setLicenseNo(agentRegisterDto.getLicenseNo());
		agent.setTaxRegNo(agentRegisterDto.getTaxRegNo());
		agent.setOrgCodeNo(agentRegisterDto.getOrgCodeNo());
		agent.setGrantNoteNo(agentRegisterDto.getGrantNoteNo());
		agent.setName(agentRegisterDto.getName());
		agent.setPassword(agentRegisterDto.getPassword());
		agent.setIdCardNo(agentRegisterDto.getIdCardNo());
		agent.setMobile(agentRegisterDto.getMobile());
		agent.setProvince(agentRegisterDto.getProvince());
		agent.setCity(agentRegisterDto.getCity());
		agent.setDistrict(agentRegisterDto.getDistrict());
		agent.setActingAreaId(agentRegisterDto.getActingAreaId());
		agent.setDetailedAddress(agentRegisterDto.getDetailedAddress());
		agent.setUpstreamAgent(new Agent(upstreamAgent.getId()));
		switch (agentRegisterDto.getRegisterType()) {
		// 县代理
		case COUNTY:
			agent.setAgentType(AgentType.COUNTY);
			break;
		case PERSONAL:
			// 业务员
			agent.setAgentType(AgentType.PERSONAL);
			break;
		}
		return agent;

	}

	/**
	 * 返回首页的数据的接口
	 */
	@Override
	public ApiResponse<List<AgentIndexDto>> agentIndex(IndexCommand indexCommand) {
		Agent currentUser = indexCommand.getCurrentUser();
		AgentIndexQueryConditions queryConditions = buildConditions(indexCommand);
		List<AgentIndexDto> data = agentService.getIndex(queryConditions);
		if (CollectionUtils.isNotEmpty(data)) {
			data.forEach(agentIndexDto -> {
				agentIndexDto.setAvatarUrl(PicHandler.addPrefixUrlStatic(agentIndexDto.getAvatarUrl()));
				if (agentIndexDto.getId().equals(currentUser.getId())) {
					agentIndexDto.setMyself(true);
				}
			});
		}
		if (data == null) {
			data = new ArrayList<AgentIndexDto>();
		}
		return ApiResponse.createSuccess(data);
	}

	/**
	 * 获得我的排名
	 *
	 * @param indexCommand
	 */
	@Override
	public ApiResponse<MyRankingDto> getMyRanking(IndexCommand indexCommand) {
		AgentIndexQueryConditions queryConditions = buildConditions(indexCommand);
		queryConditions.setAgentId(indexCommand.getCurrentUser().getId());
		MyRankingDto myRankingDto = agentService.getMyRanking(queryConditions);
		if (myRankingDto != null) {
			myRankingDto.setAvatarUrl(PicHandler.addPrefixUrlStatic(myRankingDto.getAvatarUrl()));
		}
		return ApiResponse.createSuccess(myRankingDto);
	}

	private AgentIndexQueryConditions buildConditions(IndexCommand indexCommand) {
		Agent currentUser = indexCommand.getCurrentUser();
		AgentIndexQueryConditions queryConditions = new AgentIndexQueryConditions();
		queryConditions.setCity(currentUser.getCity());
		switch (indexCommand.getType()) {
		// 上月
		case 2:
			queryConditions.setType(2);
			break;
		case 3:
			queryConditions.setType(3);
			break;
		// 今天
		default:
			queryConditions.setType(1);
			queryConditions.setStartTime(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
			queryConditions.setEndTime(LocalDateTime.now());
			break;
		}
		return queryConditions;
	}
}
