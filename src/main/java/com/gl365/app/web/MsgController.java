package com.gl365.app.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.remote.member.ResultDto;
import com.gl365.app.remote.message.MessageService;
import com.gl365.app.remote.message.dto.MsgReq;
import com.gl365.app.remote.message.dto.PageDto;
import com.gl365.app.utils.JsonUtils;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@RestController
@RequestMapping("/api/msg")
public class MsgController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(MsgController.class);
	
	@Autowired
	private MessageService messageService;	
	
	@ApiOperation("批量删除消息")
	@PostMapping("/delete")
	public Object delete(@RequestBody MsgReq msgReq) {
		LOG.info("msg delete begin \n param={}", JsonUtils.toJsonString(msgReq));
		Long beginTime = System.currentTimeMillis();
		ApiResponse<Void> rlt = ApiResponse.createNoErrorResponse(null);
		try {
			List<Long> req = msgReq.getIdList();
			if (req != null && (!req.isEmpty())) {
				Integer count = 20;
				if(req.size() > count){
					return ApiResponse.createNoErrorResponse("删除条数不可超过"+count+"条");
				}
				for (Long recordId : req) {
					MsgReq target = new MsgReq();
					target.setId(recordId);
					ResultDto<?> result = messageService.deleteMsgById(target);
					LOG.info("msg [id={}] result={}", recordId, JsonUtils.toJsonString(result));
				}
			}
		} catch (Exception e) {
			LOG.error("msg delete exception e:{}",e);
			rlt = ApiResponse.createErrorResponse(null);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg delete end \n rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation("设置此条消息已读")
	@PostMapping("/readMsg")
	public Object read(@RequestBody MsgReq req) {
		LOG.info("msg read begin \n param={}", JsonUtils.toJsonString(req));
		Long beginTime = System.currentTimeMillis();
		ApiResponse<Void> rlt = null;
		try {
			if (req.getId() == null) {
				return ApiResponse.createErrorResponse(ReturnCode.System.PARAM_NULL.getMsg());
			}
			MsgReq target = new MsgReq();
			target.setId(req.getId());
			target.setMessageRead("01");
			ResultDto<?> result = null;
			if("01".equals(req.getMessageRead())){
				result = new ResultDto<>(ReturnCode.System.SUCCESS);
			}else{
				result = messageService.updateMsgById(target);
			}
			rlt = ApiResponse.createResponseWithPayload(result.getData(), result.getResult(), result.getDescription());
		} catch (Exception e) {
			LOG.error("msg read exception e:{}",e);
			rlt = ApiResponse.createErrorResponse(null);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg read end \n rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation("查询消息列表")
	@PostMapping("/getMsg")
	public Object getInfoByCondition(HttpServletRequest request, @RequestBody MsgReq req) {
		LOG.info("msg getInfoByCondition begin \n param={}", JsonUtils.toJsonString(req));
		Long beginTime = System.currentTimeMillis();
		ApiResponse<Void> rlt = null;
		try {
			String alias = isLogin(request);
			if (StringUtils.isBlank(alias)) {
				return ApiResponse.createResponseWithMessage(ReturnCode.System.NEED_LOGIN);
			}
			req.setAlias(alias);
			req.setMessageDel("00");
			req.setAppType("sfront");
			ResultDto<PageDto<MsgReq>> result = messageService.getInfoByCondition(req);
			rlt = ApiResponse.createResponseWithPayload(result.getData(), result.getResult(), result.getDescription());
		} catch (Exception e) {
			LOG.error("msg getInfoByCondition exception e:{}",e);
			rlt = ApiResponse.createErrorResponse(null);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg getInfoByCondition end \n rlt={},time={}ms",JsonUtils.toJsonString(rlt),(endTime-beginTime));
		return rlt;
	}
	
	private String isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object userId = session.getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
			return userId == null ? null : (String) userId;
		}
		return null;
	}
}
