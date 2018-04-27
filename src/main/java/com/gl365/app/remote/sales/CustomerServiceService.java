package com.gl365.app.remote.sales;

import com.gl365.app.dto.CustomerServiceDetailDto;
import com.gl365.app.dto.MyCustomerServiceListDto;
import com.gl365.app.dto.NumberOfMyRequestDto;
import com.gl365.app.dto.command.FindMyCustomerServiceCommand;
import com.gl365.app.dto.command.InsertCustomerServiceCommand;
import com.gl365.app.dto.command.UpdateCustomerServiceStatusCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by caoyilong on 2017/6/1.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface CustomerServiceService {


	@RequestMapping(value = "/customerService/addRequest", method = RequestMethod.POST)
	void addRequest(@RequestBody InsertCustomerServiceCommand command);


	@RequestMapping(value = "/customerService/detail/{requestId}", method = RequestMethod.POST)
	CustomerServiceDetailDto detail(@PathVariable("requestId") String requestId);


	@RequestMapping(value = "/customerService/myList", method = RequestMethod.POST)
	MyCustomerServiceListDto findMyList(@RequestBody FindMyCustomerServiceCommand command,
										@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
										@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize);


	@RequestMapping(value = "/customerService/updateServiceStatus", method = RequestMethod.POST)
	void updateServiceStatus(@RequestBody UpdateCustomerServiceStatusCommand command);

	@RequestMapping(value = "/customerService/count/{agentId}", method = RequestMethod.POST)
	NumberOfMyRequestDto count(@PathVariable("agentId") String agentId);

}
