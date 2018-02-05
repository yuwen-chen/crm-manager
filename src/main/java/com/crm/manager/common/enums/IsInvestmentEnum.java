package com.crm.manager.common.enums;

public enum IsInvestmentEnum {
	
	IS_INVESTMENT_YES(0,"否"),
	IS_INVESTMENT_NO(1,"是");
	
	private int code;
	private String value;
	
	private IsInvestmentEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public IsInvestmentEnum getIsInvestmentEnumByCode(int code){
		for (IsInvestmentEnum iie : IsInvestmentEnum.values()) {  
	        if (iie.getCode() == code) {  
	            return iie;  
	        }  
	    }  
	    return null; 
	}
	
	public String getValue(int code){
		for (IsInvestmentEnum iie : IsInvestmentEnum.values()) {  
	        if (iie.getCode() == code) {  
	            return iie.value;  
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
