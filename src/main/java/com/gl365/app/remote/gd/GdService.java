package com.gl365.app.remote.gd;

import com.gl365.app.dto.command.QueryBankInfoCommand;
import com.gl365.app.remote.gd.dto.AreaBankDto;
import com.gl365.app.remote.gd.dto.AreaDataDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by caoyilong on 2017/6/8.
 * gd系统
 */
//后面需要删除url
@FeignClient(name="gd",url="${${env:}.url.gd:}")
public interface GdService {

	/**
	 *  根据ID获取下级单层行政区域
	 */
	@RequestMapping(value = "/gd/areaInfo/singleLevel/{areaId}", method = RequestMethod.GET)
	List<AreaDataDto> getAreaInfoById(@PathVariable("areaId") Integer areaId);

	/**
	 * 根据ID获取下级所有行政区域
	 */
	@RequestMapping(value = "/gd/areaInfo/allLevel/{areaId}", method = RequestMethod.GET)
	List<AreaDataDto> getAllAreaById(@PathVariable("areaId") Integer areaId);

	/**
	 * 根据查询银行
	 */
	@RequestMapping(value = "/gd/bankInfo/city", method = RequestMethod.GET)
	AreaBankDto queryBankInfo(@RequestBody QueryBankInfoCommand command);
}
