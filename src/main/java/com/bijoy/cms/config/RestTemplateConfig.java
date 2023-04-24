package com.bijoy.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestTemplateConfig {
	
	private ObjectMapper createObjectMapper() {

	     ObjectMapper objectMapper = new ObjectMapper();
	     objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	     objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

	     return objectMapper;
	}
	private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter() {

	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setObjectMapper(createObjectMapper());
	    return converter;
	}
	
	@Bean
	public RestTemplate createRestTemplate() {

	     RestTemplate restTemplate = new RestTemplate();
	     restTemplate.getMessageConverters().add(0, createMappingJacksonHttpMessageConverter());
	     return restTemplate;
	}

}
