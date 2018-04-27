package com.gl365.app.remote;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chen, Zhuhui
 */
@Configuration
public class ClientConfig {

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(10 * 1000, 10 * 1000);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
