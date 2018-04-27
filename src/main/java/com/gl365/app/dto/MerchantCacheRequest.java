package com.gl365.app.dto;

import java.util.List;

public class MerchantCacheRequest {
	private List<MerchantCacheDto> merchantCacheList;

	public List<MerchantCacheDto> getMerchantCacheList() {
		return merchantCacheList;
	}

	public void setMerchantCacheList(List<MerchantCacheDto> merchantCacheList) {
		this.merchantCacheList = merchantCacheList;
	}

}
