package com.bijoy.cms.restservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.bijoy.cms.bean.Collar;
import com.bijoy.cms.restservice.CollarRestService;

@SpringBootTest
public class CollarRestServiceTest {
	
	@Autowired
	CollarRestService restService;
	
	@MockBean
	RestTemplate restTemplate;
	
	@Test
	void testRestService() {
		Collar[] collar = new Collar[2];
		collar[0]= createCollar();
		collar[1]= createCollar();
		Mockito.when(restTemplate.getForObject(Mockito.anyString(),Mockito.any(),Mockito.anyString())).thenReturn(collar);
		Collar[] collarResp = restService.getCollar("1");
		assertNotNull(collarResp);
		assertTrue(collarResp[0].isHealthy());
		assertEquals("2", collarResp[0].getSerialNumber());
	}
	
	private Collar createCollar() {
		Collar collar = new Collar();
		collar.setId("123456");
		collar.setHealthy(true);
		collar.setLatitude("-73.2983");
		collar.setLongitude("114.8794");
		collar.setSerialNumber("2");
		collar.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return collar;
	}

}
