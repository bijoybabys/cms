package com.bijoy.cms.bean;

public enum CollarStatus {
	HEALTHY("Healthy"), BROKEN("Broken");

	String value;

	CollarStatus(String value) {
		this.value = value;
	}
	
    public String value() {
        return this.value;
    }

}
