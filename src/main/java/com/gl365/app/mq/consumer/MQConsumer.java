package com.gl365.app.mq.consumer;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl365.aliyun.ons.OnsListener;
import com.gl365.app.mq.dto.NotifyProfitProductMQ;
import com.gl365.app.utils.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * @author dfs_518
 *
 *         2017年9月6日上午11:03:52
 */
public abstract class MQConsumer<T> implements OnsListener {

	private static final Logger LOG = LoggerFactory.getLogger(MQConsumer.class);

	@Override
	public void receive(byte[] message) {
		try {
			T command = newInstance(message);
			execute(command);
		} catch (Exception e) {
			LOG.warn("<mq-received> error, exception : {}", e);
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private T newInstance(byte[] message) {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		try {
			return GsonUtils.fromJson2Object(new String(message), entityClass);
		} catch (JsonSyntaxException e) {
			LOG.warn("<mq-received> error : GsonString to Object convert error, message : [{}]", new String(message));
			throw new RuntimeException("<mq-received> error");
		}
	}

	public abstract void execute(T command);

}
