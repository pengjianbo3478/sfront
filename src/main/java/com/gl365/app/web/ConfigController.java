package com.gl365.app.web;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.dto.ResultForSDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by xty on 2017/6/28.
 */
@Api(description = "获取系统配置", tags = "CONFIG")
@RestController
@RequestMapping("/api/config")
public class ConfigController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigController.class);
	
	@ApiOperation("获取当天信息")
	@Permission(permission = PermissionConstant.LOGIN)
	@RequestMapping(value = "/getCurDayInfo", method = RequestMethod.GET)
	public Object getCurDayInfo() {
		LOG.info("getCurTime begin, reqParam=无");
    	Long beginTime = System.currentTimeMillis();
    	ResultForSDto<?> rlt = null;
		try {
			LocalDate time = LocalDate.now();
			String date = time.toString();
			String dayOfWeek = String.valueOf(time.getDayOfWeek().getValue());
			Map<String,String> rltMap = new HashMap<>();
			rltMap.put("date", date);
			rltMap.put("dayOfWeek", dayOfWeek);
			rlt = new ResultForSDto<>(ReturnCode.System.SUCCESS,rltMap);
		} catch (Exception e) {
			LOG.error("getCurTime exception,e:{}",e);
			rlt = new ResultForSDto<>(ReturnCode.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("getCurTime begin end,rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
}
