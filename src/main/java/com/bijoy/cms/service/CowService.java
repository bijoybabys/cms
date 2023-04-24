package com.bijoy.cms.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.bijoy.cms.bean.Collar;
import com.bijoy.cms.bean.CollarStatus;
import com.bijoy.cms.bean.CowCollar;
import com.bijoy.cms.bean.CowRequest;
import com.bijoy.cms.entity.Cow;
import com.bijoy.cms.entity.Location;
import com.bijoy.cms.exception.CustomExceptionHandler;
import com.bijoy.cms.repository.CowRepository;
import com.bijoy.cms.repository.RedisCowCollarRepository;
import com.bijoy.cms.restservice.CollarRestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CowService {

	@Autowired
	private CowRepository repository;

	@Autowired
	private CollarRestService collarRestService;

	@Autowired
	RedisCowCollarRepository redisCowCollarRepository;

	@Autowired
	ObjectMapper objectMapper;

	public Object save(CowRequest cowReq) {

		if (isExist(cowReq.getCowNumber())) {
			return CustomExceptionHandler.createErrorResponse(HttpStatus.BAD_REQUEST,
					"There were multiple instances of data with identical IDs and numbers");
		}
		Collar[] collars;
		try {
			collars = collarRestService.getCollar(cowReq.getCollarId());

		} catch (HttpServerErrorException e) {
			return CustomExceptionHandler.createErrorResponse(HttpStatus.NOT_FOUND, "Collar does not exist");
		}
		if (null == collars || collars.length == 0) {
			return CustomExceptionHandler.createErrorResponse(HttpStatus.NOT_FOUND, "Collar does not exist");
		}

		Cow cow = createCow(cowReq, collars);
		Cow cowResp = repository.save(cow);
		cache(cowResp);
		return cowResp;

	}

	public List<CowCollar> getAllCows() {
		return redisCowCollarRepository.findAll();
	}

	public Object upateCow(CowRequest cowReq, String id) {
		Cow cow = repository.findById(UUID.fromString(id)).orElse(null);
		if (null == cow) {
			return CustomExceptionHandler.createErrorResponse(HttpStatus.NOT_FOUND, "Cow with id does not exist");
		}
		Collar[] collars = collarRestService.getCollar(cowReq.getCollarId());
		if (null == collars || collars.length == 0) {
			return CustomExceptionHandler.createErrorResponse(HttpStatus.NOT_FOUND, "Collar does not exist");
		}
		Cow updateCow = updateCow(cow, cowReq, collars);
		Cow cowResp = repository.save(updateCow);
		cache(cowResp);
		return cowResp;
	}

	private void cache(Cow cowResp) {
		CowCollar cowCollar;

		cowCollar = objectMapper.convertValue(cowResp, CowCollar.class);
		redisCowCollarRepository.save(cowCollar);

	}

	public CowCollar getCowCollar(String id) {

		List<CowCollar> cowcollar = redisCowCollarRepository.findByCowNumber(id);
		return cowcollar.isEmpty() ? cowcollar.get(0) : null;
	}

	public boolean isExist(String id) {

		return getCowByCowNumber(id) != null;
	}

	private Object getCowByCowNumber(String id) {
		List<Cow> cow = repository.findByCowNumber(id);
		return !cow.isEmpty() ? cow.get(0) : null;
	}

	private String getCollarStatus(boolean healthy) {
		return healthy ? CollarStatus.HEALTHY.value() : CollarStatus.BROKEN.value();
	}

	private Cow createCow(CowRequest cowReq, Collar[] collars) {

		Cow cow = new Cow();
		Collar collar = Arrays.stream(collars).max(Comparator.comparing(Collar::getTimestamp)).orElse(null);
		cow.setCowNumber(cowReq.getCowNumber());
		cow.setCollarId(cowReq.getCollarId());
		String status = getCollarStatus(collar.isHealthy());
		cow.setCollarStatus(status);
		Location location = new Location();
		location.setLat(collar.getLatitude());
		location.setLng(collar.getLongitude());
		cow.setLocation(location);
		return cow;
	}

	private Cow updateCow(Cow cow, CowRequest cowReq, Collar[] collars) {

		Collar collar = Arrays.stream(collars).max(Comparator.comparing(Collar::getTimestamp)).orElse(null);
		cow.setCowNumber(cowReq.getCowNumber());
		cow.setCollarId(cowReq.getCollarId());
		String status = getCollarStatus(collar.isHealthy());
		cow.setCollarStatus(status);
		Location location = new Location();
		location.setLat(collar.getLatitude());
		location.setLng(collar.getLongitude());
		cow.setLocation(location);
		return cow;
	}

}
