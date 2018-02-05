package com.crm.manager.common.enums;

public enum StatusEnum {

	STATUS_NORMAL("00","正常"),
	STATUS_FREEZE("10","冻结");
	
	private String code;
	private String value;
	
	private StatusEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public StatusEnum getStatusEnumByCode(String code){
		for (StatusEnum se : StatusEnum.values()) {  
	        if (se.getCode() == code) {  
	            return se;  
	        }  
	    }  
	    return null; 
	}
	
	public String getValue(String code){
		for (StatusEnum se : StatusEnum.values()) {  
	        if (se.getCode() == code) {  
	            return se.getValue(code);  
	        }  
	    }  
	    return null; 
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
