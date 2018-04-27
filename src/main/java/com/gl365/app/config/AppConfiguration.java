package com.gl365.app.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gl365.app.json.ValuedEnumModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by caoyilong on 2017/6/6.
 */
@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("*");
	}

	@Primary
	@Bean(name = "defaultObjectMapper")
	public Jackson2ObjectMapperFactoryBean defaultObjectMapper() throws Exception {
		Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();
		factoryBean.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		factoryBean.setTimeZone(TimeZone.getTimeZone("GMT+0800"));

		List<Module> modules = new ArrayList<>();
		modules.add(new ValuedEnumModule());
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		modules.add(javaTimeModule);

		factoryBean.setModules(modules);

		factoryBean.setFeaturesToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		factoryBean.afterPropertiesSet();
		return factoryBean;
	}

	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 *
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(new BufferedImageHttpMessageConverter());
	}
}
