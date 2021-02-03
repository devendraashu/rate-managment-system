package com.dev.rms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.okhttp.OkHttpClient;

@Configuration
public class MyClientConfiguration {
	
	@Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}
