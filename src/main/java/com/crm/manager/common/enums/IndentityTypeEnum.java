package com.crm.manager.common.enums;

public enum IndentityTypeEnum {

	IDCARD("01", "身份证"), 
	PRIVATE_PASSPORT("02", "因私护照"), 
	PUBLIC_PASSPORT("03", "因公护照"), 
	HONG_KONG_IDCARD("04", "香港永久性居民身份证"), 
	MACAU_IDCARD("05", "澳门永久性居民身份证"), 
	HONG_KONG_MACAU_PASS("06", "港澳居民来往内地通行证"), 
	TAIWAN_PASS("07", "台湾居民来往大陆通行证"), 
	FOREIGN_RESIDENCE_PERMIT("08", "外国人永久居住证"), 
	OTHER_CREDENTIALS("09", "其他证件");

	private String code;
	private String value;

	private IndentityTypeEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static IndentityTypeEnum getIndentityTypeEnumByCode(String code){
		for (IndentityTypeEnum idte : IndentityTypeEnum.values()) {  
	        if (idte.getCode().equals(code)) {  
	            return idte;  
	        }  
	    }  
	    return null; 
	}
	
	public static String getValue(String code){
		for (IndentityTypeEnum idte : IndentityTypeEnum.values()) {  
	        if (idte.getCode().equals(code)) {  
	            return idte.value;  
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
