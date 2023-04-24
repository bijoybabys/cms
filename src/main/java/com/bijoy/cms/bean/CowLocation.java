package com.bijoy.cms.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CowLocation  implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	int id;
	String lat;
	String lng;
	

}
