package com.gl365.app.remote.sales;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.Agent;
import com.gl365.app.dto.AgentIndexDto;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.MyAgentPartnerDto;
import com.gl365.app.dto.MyRankingDto;
import com.gl365.app.dto.ResultForSDto;
import com.gl365.app.dto.command.AgentIndexQueryConditions;
import com.gl365.app.dto.command.FindMyAgentPartnerCommand;
import com.gl365.app.dto.command.ForgotPasswordCommand;
import com.gl365.app.dto.command.HasSubAgentCommand;
import com.gl365.app.dto.command.UpdateMobileCommand;
import com.gl365.app.dto.command.UpdateNickNameCommand;
import com.gl365.app.dto.command.UpdatePasswordCommand;

/**
 * Created by caoyilong on 2017/6/1.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface AgentService {

	@RequestMapping(value = "/api/agent/findByMobile", method = RequestMethod.POST)
	Agent findByMobile(@RequestBody String mobile);
	
	@RequestMapping(value = "/api/agent/findByMobile/quit", method = RequestMethod.POST)
	Agent findQuitByMobile(@RequestBody String mobile);

	@RequestMapping(value = "/api/agent/findById", method = RequestMethod.POST)
	Agent findById(@RequestBody String id);
	
	@RequestMapping(value = "/api/agent/findUpstreamNpAgentIdById", method = RequestMethod.POST)
	String findUpstreamNpAgentIdById(@RequestBody String id);

	@RequestMapping(value = "/api/agent/findWithRefById", method = RequestMethod.POST)
	Agent findWithRefById(@RequestBody String id);

	@RequestMapping(value = "/api/agent/findByIdCardNo", method = RequestMethod.POST)
	Agent findByIdCardNo(@RequestBody String idCardNo);

	@RequestMapping(value = "/api/agent/create", method = RequestMethod.POST)
	void create(@RequestBody Agent agent);

	@RequestMapping(value = "/api/agent/updatePassword", method = RequestMethod.POST)
	public ApiResponse<?> updatePassword(@RequestBody UpdatePasswordCommand command);

	@RequestMapping(value = "/api/agent/updateNickName", method = RequestMethod.POST)
	void updateNickName(@RequestBody UpdateNickNameCommand command);

	@RequestMapping(value = "/api/agent/updateMobile", method = RequestMethod.POST)
	void updateMobile(@RequestBody UpdateMobileCommand command);

	@RequestMapping(value = "/api/agent/findMyAgentPartner", method = RequestMethod.POST)
	public MyAgentPartnerDto findMyAgentPartner(@RequestBody FindMyAgentPartnerCommand request,
												@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
												@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

	@RequestMapping(value = "/api/agent/findMyAgentPartnerList", method = RequestMethod.POST)
	public List<Agent> findMyAgentPartnerList(@RequestBody FindMyAgentPartnerCommand request);

	@RequestMapping(value = "/api/agent/findMyAgentPartnerCount", method = RequestMethod.POST)
	public Integer findMyAgentPartnerCount(@RequestBody FindMyAgentPartnerCommand request);

	@RequestMapping(value = "/api/agent/getHasSubAgent", method = RequestMethod.POST)
	public MyAgentPartnerDto getHasSubAgent(@RequestBody HasSubAgentCommand conditions,
											@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
											@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

	@RequestMapping(value = "/api/agent/getHasSubAgentNum", method = RequestMethod.POST)
	public Integer getHasSubAgentNum(@RequestBody HasSubAgentCommand conditions);

	@RequestMapping(value = "/api/agent/findPersonalPartnerOfAgent", method = RequestMethod.POST)
	public MyAgentPartnerDto findPersonalPartnerOfAgent(@RequestBody FindMyAgentPartnerCommand conditions,
														@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
														@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);

	@RequestMapping(value = "/api/agent/getIndex", method = RequestMethod.POST)
	List<AgentIndexDto> getIndex(@RequestBody AgentIndexQueryConditions conditions);

	@RequestMapping(value = "/api/agent/getMyRanking", method = RequestMethod.POST)
	MyRankingDto getMyRanking(@RequestBody AgentIndexQueryConditions conditions);

	@RequestMapping(value = "/api/agent/fogotPassword", method = RequestMethod.POST)
	ApiResponse<?> fogotPassword(@RequestBody ForgotPasswordCommand command);

	@RequestMapping(value = "/api/agent/updatePhoto", method = RequestMethod.POST)
	ResultForSDto<?> updatePhoto(@RequestParam("avatarUrl") String avatarUrl, @RequestParam("agentId") String agentId);
	
	@RequestMapping(value = "/api/agent/findMyAgentByUpstreamAgentIdList", method = RequestMethod.POST)
	public List<Agent> findMyAgentByUpstreamAgentIdList(@RequestBody FindMyAgentPartnerCommand request);
}
