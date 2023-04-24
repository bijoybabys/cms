package com.bijoy.cms.bean;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@RedisHash("CowCollar")
public class CowCollar implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;
	String collarId;
	String cowNumber;
	String collarStatus;
	@JsonProperty("lastLocation")
	CowLocation location;
}
