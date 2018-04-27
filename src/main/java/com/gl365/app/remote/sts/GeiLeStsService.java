package com.gl365.app.remote.sts;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 阿里oss服务
 * Created by caoyilong on 2017/5/24.
 */
@FeignClient(name="sts",url="${${env:}.url.sts:}")
public interface GeiLeStsService {

	/**
	 * 获取阿里云图片上传token
	 *
	 * @return
	 */
	@RequestMapping(value = "/sts", method = RequestMethod.GET)
	public Object generateOssKey();
}
