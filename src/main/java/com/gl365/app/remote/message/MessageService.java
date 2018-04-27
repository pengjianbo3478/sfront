package com.gl365.app.remote.message;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.app.remote.member.ResultDto;
import com.gl365.app.remote.message.dto.MsgReq;
import com.gl365.app.remote.message.dto.PageDto;

@FeignClient(name = "message", url = "${${env:}.url.message:}")
public interface MessageService {

	/**
	 * 添加一条推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/addMsg", method = RequestMethod.POST)
	public ResultDto<?> addMsg(@RequestBody MsgReq req);
	
	/**
	 * 删除一条推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/deleteMsgById", method = RequestMethod.POST)
	public ResultDto<?> deleteMsgById(@RequestBody MsgReq req);
	
	/**
	 * 更新一条推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/updateMsgById", method = RequestMethod.POST)
	public ResultDto<?> updateMsgById(@RequestBody MsgReq req);

	/**
	 * 根据id,alias,appType,messageType,messageDel查询推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/getInfoByCondition", method = RequestMethod.POST)
	public ResultDto<PageDto<MsgReq>> getInfoByCondition(@RequestBody MsgReq req);

}
