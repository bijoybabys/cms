package com.bijoy.cms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bijoy.cms.entity.Cow;

@Repository
public interface CowRepository extends JpaRepository<Cow, UUID>{
	
	List<Cow> findByCowNumber(String id);


}
