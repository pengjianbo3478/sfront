package com.gl365.app.remote.gd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.app.remote.gd.dto.AreaDataDto;
import com.gl365.app.utils.TimeOutCache;

@Service
public class GdServiceImpl {

	private static final String GET_ALL_AREA_BY_ID_1 = "GET_ALL_AREA_BY_ID_1";

	@Autowired
	private GdService gdService;

	/**
	 * 查询areaId =1
	 * 
	 * @return
	 */
	public Map<Integer, String> getAllAreaById1() {

		List<AreaDataDto> result = (List<AreaDataDto>) TimeOutCache.getInstance().get(GET_ALL_AREA_BY_ID_1);
		if (CollectionUtils.isEmpty(result)) {
			result = gdService.getAllAreaById(1);
			TimeOutCache.getInstance().put(GET_ALL_AREA_BY_ID_1, result);
		}

		if (CollectionUtils.isEmpty(result)) {
			return new HashMap<>();
		}
		Map<Integer, String> rltMap = new HashMap<>();
		result.forEach((rlt) -> rltMap.put(rlt.getId(), rlt.getName()));
		return rltMap;
	}

}
