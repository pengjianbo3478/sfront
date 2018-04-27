package com.gl365.app.handler;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by caoyilong on 2017/6/9.
 * 图片url处理
 */
@Component
@Lazy(false)
public class PicHandler implements InitializingBean, DisposableBean {

	@Value("${aliyun.ossUrl}")
	private String PREFIX_URL;

	private static PicHandler picHandler;

	/**
	 * 生成保存图片的url
	 * 去除前缀
	 */
	public String generateSaveUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		String saveUrl = null;
		if (url.contains(PREFIX_URL)) {
			saveUrl = url.replaceFirst(PREFIX_URL, "");
		}
		return saveUrl == null ? url : saveUrl;
	}

	/**
	 * 加上图片的访问的url
	 * 加上前缀
	 */

	public String addPrefixUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		String newUrl = PREFIX_URL + url;
		return newUrl;

	}


	public static String addPrefixUrlStatic(String url) {
		return picHandler.addPrefixUrl(url);

	}

	@Override
	public void destroy() throws Exception {
		picHandler = null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		picHandler = this;
	}
}
