package com.crm.manager.common.enums;

public enum OrderTypeEnum {

	ORDER_TYPE_BATCH_APPLY("batch_apply","批量申购"),
	ORDER_TYPE_APPLY("apply","单笔申购");
	
	private String code;
	private String value;
	
	private OrderTypeEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public OrderTypeEnum getOrderTypeEnumByCode(String code){
		for (OrderTypeEnum ote : OrderTypeEnum.values()) {  
	        if (ote.getCode() == code) {  
	            return ote;  
	        }  
	    }  
	    return null; 
	}
	
	public String getValue(String code){
		for (OrderTypeEnum ote : OrderTypeEnum.values()) {  
	        if (ote.getCode() == code) {  
	            return ote.value;  
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
