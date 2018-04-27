package com.gl365.app.web;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ApiRequest;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.MyPosesDto;
import com.gl365.app.dto.PosDetailDto;
import com.gl365.app.dto.ResultForSDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.CheckPosCommand;
import com.gl365.app.dto.command.InstallPosCommand;
import com.gl365.app.dto.command.PosDetailCommand;
import com.gl365.app.remote.sales.PosService;
import com.gl365.app.service.MerchantFacadeService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by qiuchaojie on 2017/6/12.
 */
@Api(description = "机具管理相关", tags = "POS")
@RestController
@RequestMapping("/api/pos")
public class PosController extends BaseController{

    @Autowired
    private PosService posService;

    @Autowired
    private MerchantFacadeService merchantFacadeService;
    
    private static final Logger LOG = LoggerFactory.getLogger(PosController.class);


    @ApiOperation("我的POS机列表")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/myList", method = RequestMethod.POST)
    public ApiResponse<MyPosesDto> myList(@RequestBody ApiRequest<Void> command){
        return ApiResponse.createSuccess(posService.myList(getCurrentLoginUserId(), command.getPageNo(), command.getPageSize()));
    }


    @ApiOperation("装机详情")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ApiResponse<PosDetailDto> detail(@RequestBody PosDetailCommand command){

        command.setAgentId(getCurrentLoginUserId());
        PosDetailDto dto = posService.detail(command);
        Map<Integer, String> industryMap = merchantFacadeService.getIndustriesIdMap();
        dto.setMerchantIndustryName(industryMap.get(dto.getMerchantIndustry()));

        return ApiResponse.createSuccess(dto);
    }


    @ApiOperation("新建装机申请")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/installApplication", method = RequestMethod.POST)
    public ApiResponse<Void> installApplication(@RequestBody InstallPosCommand command){
        command.setAgentId(getCurrentLoginUserId());
        posService.installApplication(command);
        return ApiResponse.createNoErrorResponse("");
    }


    @ApiOperation(value = "解绑", hidden = true)
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/uninstallPos/{posId}", method = RequestMethod.GET)
    public ApiResponse<Void> uninstallPos(@PathVariable("posId") String posId){
        InstallPosCommand command = new InstallPosCommand();
        command.setAgentId(getCurrentLoginUserId());
        command.setPosId(posId);
        posService.uninstallPos(command);
        return ApiResponse.createNoErrorResponse("");
    }


    @ApiOperation("根据SN号和终端号检查pos机")
    @Permission(permission = PermissionConstant.LOGIN)
    @ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/checkPos", method = RequestMethod.POST)
    public Object checkPos(@RequestBody CheckPosCommand command) {
    	LOG.info("根据SN号和终端号检查pos机 checkPos begin, reqParam={}",JsonUtils.toJsonString(command));
    	Long beginTime = System.currentTimeMillis();
    	ResultForSDto<PosDetailDto> rlt = null;
		try {
			if (StringUtils.isBlank(command.getDeviceId()) || StringUtils.isBlank(command.getPosSn())) {
				return new ResultForSDto<>(ReturnCode.System.PARAM_NULL);
			}
			rlt = posService.checkPos(command);
		} catch (Exception e) {
			LOG.error("根据SN号和终端号检查pos机 checkPos invoking sales project exception  ===> posService.checkPos exception,e:{}",e);
			rlt = new ResultForSDto<>(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("根据SN号和终端号检查pos机 checkPos end,rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
    }
}
