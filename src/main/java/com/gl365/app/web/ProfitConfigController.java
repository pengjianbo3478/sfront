package com.gl365.app.web;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.enum_type.AgentType;
import com.gl365.app.dto.users.AgentLoginDto;
import com.gl365.app.remote.profit.ProfitConfigService;
import com.gl365.app.remote.profit.command.QueryOneCommand;
import com.gl365.app.remote.profit.command.UpdateProfitConfigCommand;
import com.gl365.app.remote.profit.dto.AgentProfitDto;
import com.gl365.app.remote.profit.dto.OneAgentProfitDto;
import com.gl365.app.remote.sales.AgentService;
import com.gl365.app.remote.withdraw.dto.BaseDto;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
@Api(description = "分润相关", tags = "PROFIT")
@RestController
@RequestMapping("/api/profit")
public class ProfitConfigController extends BaseController{

    @Autowired
    private ProfitConfigService profitConfigService;

    @Autowired
    private AgentService agentService;

    @SuppressWarnings("unchecked")
	@ApiOperation("查询单条分润设置")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public ApiResponse<AgentProfitDto> queryOne() {

        AgentLoginDto agent = getCurrentUser();
        QueryOneCommand command = new QueryOneCommand();
        command.setAgentNo(agent.getId());
        command.setAgentType(agent.getAgentType().value() + "");

        OneAgentProfitDto oneAgentProfitDto = profitConfigService.queryOne(command);
        if("000000".equals(oneAgentProfitDto.getResultCode()) && oneAgentProfitDto.getData() != null && agent.getAgentType() != null && agent.getAgentType().value() == AgentType.COUNTY.value()){
        	String upstreamNpAgentIdById = agentService.findUpstreamNpAgentIdById(agent.getId());
        	if(StringUtils.isNotBlank(upstreamNpAgentIdById)){
        		command.setAgentNo(upstreamNpAgentIdById);
        		command.setAgentType(AgentType.CITY.value()+"");
        		OneAgentProfitDto fatherOneAgentProfitDto = profitConfigService.queryOne(command);
	        	if ("000000".equals(fatherOneAgentProfitDto.getResultCode()) && fatherOneAgentProfitDto.getData() != null) {
	        		BigDecimal serviceRate = fatherOneAgentProfitDto.getData().getSubordinateServiceRate();
	        		if(null != serviceRate){
	        			oneAgentProfitDto.getData().setServiceRate(serviceRate);
	        		}
	            } else {
	                return ApiResponse.createResponseWithMessage(fatherOneAgentProfitDto.getResultCode(), fatherOneAgentProfitDto.getResultDesc());
	            }
        	}
        }
        
        if ("000000".equals(oneAgentProfitDto.getResultCode())) {
        	AgentProfitDto data = oneAgentProfitDto.getData();
        	if(data !=null && data.getDevMerchantFirstRate()==null && data.getDevMerchantSecondRate()==null && data.getServiceRate()==null && data.getSubordinateServiceRate()==null){
        		data = null;
        	}
            return ApiResponse.createSuccess(data);
        } else {
            return ApiResponse.createResponseWithMessage(oneAgentProfitDto.getResultCode(), oneAgentProfitDto.getResultDesc());
        }
    }

    @ApiOperation("更新分润设置")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/setProfitConfig", method = RequestMethod.POST)
    public ApiResponse<Void> setProfitConfig(@RequestBody UpdateProfitConfigCommand command) {

        AgentLoginDto agent = getCurrentUser();
        command.setAgentType(agent.getAgentType().value() + "");
        command.setAgentNo(getCurrentLoginUserId());
        command.setModifyBy(getCurrentLoginUserId());
        if(command.getSubordinateServiceRate() == null){
        	command.setSubordinateServiceRate(new BigDecimal(0));
        }
        BaseDto dto = profitConfigService.setProfitConfig(command);
        if("000000".equals(dto.getResultCode())){
            return ApiResponse.createNoErrorResponse("");
        }else{
            return ApiResponse.createResponseWithMessage(dto.getResultCode(), dto.getResultDesc());
        }
    }

}
