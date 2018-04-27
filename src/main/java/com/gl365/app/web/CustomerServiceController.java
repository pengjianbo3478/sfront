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
import com.gl365.app.dto.CustomerServiceDetailDto;
import com.gl365.app.dto.MyCustomerServiceListDto;
import com.gl365.app.dto.NumberOfMyRequestDto;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.dto.command.FindMyCustomerServiceCommand;
import com.gl365.app.dto.command.InsertCustomerServiceCommand;
import com.gl365.app.dto.command.UpdateCustomerServiceStatusCommand;
import com.gl365.app.dto.enum_type.CustomerServiceRequestType;
import com.gl365.app.handler.PicHandler;
import com.gl365.app.remote.gd.GdServiceImpl;
import com.gl365.app.remote.sales.CustomerServiceService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.web.permission.Permission;
import com.gl365.app.web.permission.PermissionConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * Created by qiuchaojie on 2017/6/1.
 */
@Api(description = "APP售后服务相关", tags = "APP_CUSTOMER_SERVICE_REQUEST")
@RestController
@RequestMapping("/api/customerService")
public class CustomerServiceController extends BaseController {

	@Autowired
	private PicHandler picHandler;

	@Autowired
	private CustomerServiceService customerServiceService;

    @Autowired
	private GdServiceImpl gdServiceImpl;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceController.class);

	@ApiOperation("新建售后服务")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/addRequest", method = RequestMethod.POST)
	public ApiResponse<Void> addRequest(@RequestBody InsertCustomerServiceCommand command) {

		if(command.getType() == CustomerServiceRequestType.CHANGE_LEGAL_PERSON_ACCOUNT){
			command.setLicenseUrl(picHandler.generateSaveUrl(command.getLicenseUrl()));
			command.setIdCardFrontUrl(picHandler.generateSaveUrl(command.getIdCardFrontUrl()));
			command.setIdCardBackUrl(picHandler.generateSaveUrl(command.getIdCardBackUrl()));
			command.setDepositCardFrontUrl(picHandler.generateSaveUrl(command.getDepositCardFrontUrl()));
			command.setDepositCardBackUrl(picHandler.generateSaveUrl(command.getDepositCardBackUrl()));
			command.setApplicationFormUrl(picHandler.generateSaveUrl(command.getApplicationFormUrl()));
		}else if(command.getType() == CustomerServiceRequestType.CHANGE_NON_LEGAL_PERSON_ACCOUNT){
			command.setLicenseUrl(picHandler.generateSaveUrl(command.getLicenseUrl()));
			command.setIdCardFrontUrl(picHandler.generateSaveUrl(command.getIdCardFrontUrl()));
			command.setIdCardBackUrl(picHandler.generateSaveUrl(command.getIdCardBackUrl()));
			command.setNlpIdCardFrontUrl(picHandler.generateSaveUrl(command.getNlpIdCardFrontUrl()));
			command.setNlpIdCardBackUrl(picHandler.generateSaveUrl(command.getNlpIdCardBackUrl()));
			command.setEngagementLetterUrl(picHandler.generateSaveUrl(command.getEngagementLetterUrl()));
			command.setDepositCardFrontUrl(picHandler.generateSaveUrl(command.getDepositCardFrontUrl()));
			command.setDepositCardBackUrl(picHandler.generateSaveUrl(command.getDepositCardBackUrl()));
			command.setApplicationFormUrl(picHandler.generateSaveUrl(command.getApplicationFormUrl()));
		}else if(command.getType() == CustomerServiceRequestType.POS_MALFUNCTION){
			command.setFixingListUrl(picHandler.generateSaveUrl(command.getFixingListUrl()));
		}else{
			command.setFixingListUrl(picHandler.generateSaveUrl(command.getFixingListUrl()));
//			command.setApplicationFormUrl(picHandler.generateSaveUrl(command.getApplicationFormUrl()));
//			command.setContractUrl(picHandler.generateSaveUrl(command.getContractUrl()));
		}

		command.setAgentId(getCurrentLoginUserId());
		customerServiceService.addRequest(command);
		return ApiResponse.createNoErrorResponse("");
	}


	@ApiOperation("售后服务详情")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
    @RequestMapping(value = "/detail/{requestId}", method = RequestMethod.GET)
    public ApiResponse<CustomerServiceDetailDto> detail(@PathVariable("requestId") String requestId) {
		Map<Integer, String> mapping = gdServiceImpl.getAllAreaById1();

        CustomerServiceDetailDto dto = customerServiceService.detail(requestId);
        dto.setMerchantProvinceDesc(mapping.get(dto.getMerchantProvince()));
        dto.setMerchantCityDesc(mapping.get(dto.getMerchantCity()));
        dto.setMerchantDistrictDesc(mapping.get(dto.getMerchantDistrict()));
		dto.setMerchantProvince(null);
		dto.setMerchantCity(null);
		dto.setMerchantDistrict(null);

        return ApiResponse.createSuccess(dto);
    }


	@ApiOperation("我的售后服务")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/myList", method = RequestMethod.POST)
	public ApiResponse<MyCustomerServiceListDto> myList(@RequestBody ApiRequest<FindMyCustomerServiceCommand> command) {
		command.getData().setAgentId(getCurrentLoginUserId());
		MyCustomerServiceListDto dto = customerServiceService.findMyList(command.getData(), command.getPageNo(), command.getPageSize());
		return ApiResponse.createSuccess(dto);
	}


	@ApiOperation("更新售后服务状态")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/updateServiceStatus", method = RequestMethod.POST)
	public ApiResponse<Void> updateServiceStatus(@RequestBody UpdateCustomerServiceStatusCommand command) {

		LOG.info("更新售后服务状态  CustomerServiceController.updateServiceStatus begin \n param={}",JsonUtils.toJsonString(command));
		if(StringUtils.isBlank(command.getRequestId())){
			return ApiResponse.createResponseWithMessage(ReturnCode.System.PARAM_NULL);
		}
		if (StringUtils.isNotBlank(command.getLicenseUrl()))
			command.setLicenseUrl(picHandler.generateSaveUrl(command.getLicenseUrl()));
		if (StringUtils.isNotBlank(command.getIdCardFrontUrl()))
			command.setIdCardFrontUrl(picHandler.generateSaveUrl(command.getIdCardFrontUrl()));
		if (StringUtils.isNotBlank(command.getIdCardBackUrl()))
			command.setIdCardBackUrl(picHandler.generateSaveUrl(command.getIdCardBackUrl()));
		if (StringUtils.isNotBlank(command.getNlpIdCardFrontUrl()))
			command.setNlpIdCardFrontUrl(picHandler.generateSaveUrl(command.getNlpIdCardFrontUrl()));
		if (StringUtils.isNotBlank(command.getNlpIdCardBackUrl()))
			command.setNlpIdCardBackUrl(picHandler.generateSaveUrl(command.getNlpIdCardBackUrl()));
		if (StringUtils.isNotBlank(command.getDepositCardFrontUrl()))
			command.setDepositCardFrontUrl(picHandler.generateSaveUrl(command.getDepositCardFrontUrl()));
		if (StringUtils.isNotBlank(command.getDepositCardBackUrl()))
			command.setDepositCardBackUrl(picHandler.generateSaveUrl(command.getDepositCardBackUrl()));
		if (StringUtils.isNotBlank(command.getApplicationFormUrl()))
			command.setApplicationFormUrl(picHandler.generateSaveUrl(command.getApplicationFormUrl()));
		if (StringUtils.isNotBlank(command.getEngagementLetterUrl()))
			command.setEngagementLetterUrl(picHandler.generateSaveUrl(command.getEngagementLetterUrl()));
		if (StringUtils.isNotBlank(command.getFixingListUrl()))
			command.setFixingListUrl(picHandler.generateSaveUrl(command.getFixingListUrl()));
		if (StringUtils.isNotBlank(command.getContractUrl()))
			command.setContractUrl(picHandler.generateSaveUrl(command.getContractUrl()));

		command.setAgentId(getCurrentLoginUserId());
		LOG.info("更新售后服务状态  customerServiceService.updateServiceStatus \n param={}",JsonUtils.toJsonString(command));
		customerServiceService.updateServiceStatus(command);
		ApiResponse<Void> rlt = ApiResponse.createNoErrorResponse("");
		LOG.info("更新售后服务状态  CustomerServiceController.updateServiceStatus end \n rlt={}",JsonUtils.toJsonString(rlt));
		return rlt;
	}


	@ApiOperation("查询业务员各个状态的售后流程数量")
	@Permission(permission = PermissionConstant.LOGIN)
	@ApiImplicitParams(@ApiImplicitParam(name = "GL_TOKEN", dataType = "string", paramType = "header", value = "GL_TOKEN 令牌值"))
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public ApiResponse<NumberOfMyRequestDto> count() {
		return ApiResponse.createSuccess(customerServiceService.count(getCurrentLoginUserId()));
	}
}
