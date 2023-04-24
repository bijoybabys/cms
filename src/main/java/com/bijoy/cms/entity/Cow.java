package com.bijoy.cms.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "cow")
public class Cow {
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    UUID id;
	String collarId;
	String cowNumber;
	String collarStatus;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locationId", referencedColumnName = "id")
	@JsonProperty("lastLocation")
	Location location;

}
