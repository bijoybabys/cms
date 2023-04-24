package com.bijoy.cms.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Collar {

	@JsonProperty("id")
    private String id;
	@JsonProperty("serialNumber")
    private String serialNumber;
	@JsonProperty("lat")
    private String latitude;
	@JsonProperty("lng")
    private String longitude;
	@JsonProperty("healthy")
    private boolean healthy;
	@JsonProperty("timestamp")
    private Timestamp timestamp;
}
