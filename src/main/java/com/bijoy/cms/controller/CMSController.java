package com.bijoy.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bijoy.cms.bean.CowCollar;
import com.bijoy.cms.bean.CowRequest;
import com.bijoy.cms.service.CowService;

@RestController
public class CMSController {

	@Autowired
	private CowService service;
	
	@PostMapping("/cows")
	public Object addCow(@RequestBody CowRequest cow) {
		return service.save(cow);
	}

    @GetMapping("/cows")
    public List<CowCollar> findAllCows() {
        return service.getAllCows();
    }

    @PutMapping("/cows/{id}")
    public Object updateCow(@PathVariable("id") String id, @RequestBody CowRequest cow) {
        return service.upateCow(cow, id);
    }
}
