package com.crm.manager.send.enums;

public enum SendTypeEnum {
	SINGLE("single"), MULTIPLE("multiple");
	
	private String value;

	private SendTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
