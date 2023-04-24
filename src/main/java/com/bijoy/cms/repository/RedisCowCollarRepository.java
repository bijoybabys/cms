package com.bijoy.cms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bijoy.cms.bean.CowCollar;

@Repository
public interface RedisCowCollarRepository extends CrudRepository<CowCollar, String> {
	
	List<CowCollar> findAll();
	List<CowCollar> findByCowNumber(String id);
}

