package com.gl365.app.web;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.command.QueryBankInfoCommand;
import com.gl365.app.remote.gd.GdService;
import com.gl365.app.remote.gd.dto.AreaBankDto;
import com.gl365.app.utils.TimeOutCache;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by qiuchaojie on 2017/6/9.
 */
@Api(description = "银行相关", tags = "BANK")
@RestController
@RequestMapping("/api/bank")
public class GdController extends BaseController {

	@Autowired
	private GdService gdService;

	@ApiOperation("查询银行")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/queryBankInfo", method = RequestMethod.POST)
	public ApiResponse<AreaBankDto> queryBankInfo(@RequestBody QueryBankInfoCommand command) {
		return ApiResponse.createSuccess(gdService.queryBankInfo(command));
	}
}
