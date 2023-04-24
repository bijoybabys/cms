package com.bijoy.cms.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.bijoy.cms.bean.Collar;

@Component
public class CollarRestService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${cms.collar.url}")
	private String collarUrl;

	public Collar[] getCollar(String collarId) throws HttpServerErrorException {
		try {
			Collar[] collars = restTemplate.getForObject(collarUrl, Collar[].class, collarId);
			return collars;
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
