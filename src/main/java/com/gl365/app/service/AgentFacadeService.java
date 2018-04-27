package com.gl365.app.service;

import com.gl365.app.dto.AgentIndexDto;
import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.MyRankingDto;
import com.gl365.app.dto.command.AgentRegisterCommand;
import com.gl365.app.dto.command.IndexCommand;

import java.util.List;

/**
 * Created by caoyilong on 2017/6/7.
 */
public interface AgentFacadeService {
	/**
	 */
	ApiResponse registerAgent(AgentRegisterCommand agentRegisterDto);

	/**
	 * 返回首页的数据的接口
	 */
	ApiResponse<List<AgentIndexDto>> agentIndex(IndexCommand indexCommand);

	/**
	 * 获得我的排名
	 */
	ApiResponse<MyRankingDto> getMyRanking(IndexCommand indexCommand);
}
