package com.crm.manager.common.enums;

public enum SexEnum {

	SEX_MALE(1,"男"),
	SEX_FEMALE(2,"女");
	
	private int code;
	private String value;
	
	private SexEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public SexEnum getSexEnumByCode(int code){
		for (SexEnum iie : SexEnum.values()) {  
	        if (iie.getCode() == code) {  
	            return iie;  
	        }  
	    }  
	    return null; 
	}
	
	public String getValue(int code){
		for (SexEnum iie : SexEnum.values()) {  
	        if (iie.getCode() == code) {  
	            return iie.getValue(code);  
	        }  
	    }  
	    return null; 
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
