package com.gl365.app.mq.producer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gl365.aliyun.ons.OnsProducer;
import com.gl365.app.mq.dto.PushMQ;
import com.gl365.app.utils.JsonUtils;

@Component
public class JPushProducer {

	private static final Logger LOG = LoggerFactory.getLogger(JPushProducer.class);

	@Lazy
	@Resource(name = "gl365-jpush-producer")
	private OnsProducer jpushProducer;

	public void send(PushMQ pushMQ) {
		String message = JsonUtils.toJsonString(pushMQ);
		LOG.info("<mq-send>推送通知APP》》》入参：{}", message);
		try {
			jpushProducer.send(message);
		} catch (Exception e) {
			LOG.error("<mq-send>推送通知APP》》》错误：{}", e);
		}
	}
}

