package com.bijoy.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bijoy.cms.bean.Collar;
import com.bijoy.cms.bean.CowCollar;
import com.bijoy.cms.bean.CowLocation;
import com.bijoy.cms.bean.CowRequest;
import com.bijoy.cms.entity.Cow;
import com.bijoy.cms.entity.Location;
import com.bijoy.cms.repository.CowRepository;
import com.bijoy.cms.repository.RedisCowCollarRepository;
import com.bijoy.cms.restservice.CollarRestService;
import com.bijoy.cms.service.CowService;

@SpringBootTest
public class CMSServiceTest {
	
	@Autowired
	CowService service;
	
	@MockBean
	CollarRestService collarRestService;
	
	@MockBean
	RedisCowCollarRepository redisCowCollarRepository;
	
	@MockBean
	CowRepository repository;
	
	@Test
	void testAddCow() {
		Collar[] collar = new Collar[2];
		collar[0]= createCollar();
		collar[1]= createCollar();
		Mockito.when(collarRestService.getCollar(Mockito.anyString())).thenReturn(collar);
		Mockito.when(redisCowCollarRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(repository.save(createCow())).thenReturn(createCow());
		Cow cowResp = (Cow)service.save(createCowRequest());
		assertNotNull(cowResp);
		assertEquals("10",cowResp.getCollarId());
		assertEquals("Healthy", cowResp.getCollarStatus());
		
	}
	@Test
	void testUpdateCow() {
		Collar[] collar = new Collar[2];
		collar[0]= createCollar();
		collar[1]= createCollar();
		Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(createCow()));
		Mockito.when(collarRestService.getCollar(Mockito.anyString())).thenReturn(collar);
		Mockito.when(redisCowCollarRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(repository.save(createCow())).thenReturn(createCow());
		Cow cow = (Cow)service.upateCow(createCowRequest(), "5a98fb37-f2cb-4a18-a584-8a1bf7ab7f36");
		assertNotNull(cow);
		assertEquals("10",cow.getCollarId());
		assertEquals("Healthy", cow.getCollarStatus());
	}
	
	@Test
	void getAllCowTest() {
		List<CowCollar> lst = new ArrayList<>();
		lst.add(createCowCollar());
		Mockito.when(redisCowCollarRepository.findAll()).thenReturn(lst);
		List<CowCollar> cow = service.getAllCows();
		assertNotNull(cow);
		assertEquals("10",cow.get(0).getCollarId());
		assertEquals("Healthy", cow.get(0).getCollarStatus());
	}
	
	private CowRequest createCowRequest() {
		CowRequest cowReq= new CowRequest();
		cowReq.setCollarId("10");
		cowReq.setCowNumber("10");
		return cowReq;
	}
	
	private Cow createCow() {
		Cow cow= new Cow();
		cow.setCollarId("10");
		cow.setCowNumber("10");
		cow.setCollarStatus("Healthy");
		Location loc = new Location();
		loc.setLat("-73.2983");
		loc.setLng("114.8794");
		cow.setLocation(loc);
		return cow;
	}
	
	private CowCollar createCowCollar() {
		CowCollar cow= new CowCollar();
		cow.setCollarId("10");
		cow.setCowNumber("10");
		cow.setCollarStatus("Healthy");
		CowLocation loc = new CowLocation();
		loc.setLat("-73.2983");
		loc.setLng("114.8794");
		cow.setLocation(loc);
		return cow;
	}
	
	private Collar createCollar() {
		Collar collar = new Collar();
		collar.setId("dddd");
		collar.setHealthy(true);
		collar.setLatitude("-73.2983");
		collar.setLongitude("114.8794");
		collar.setSerialNumber("2");
		collar.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return collar;
	}

}
