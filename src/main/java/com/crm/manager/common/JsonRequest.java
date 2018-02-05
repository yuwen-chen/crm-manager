package com.crm.manager.common;

import java.io.Serializable;

public class JsonRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337145110550263349L;
	
	private String data_body; 
	
	private String data_sign;

	public String getData_body() {
		return data_body;
	}

	public void setData_body(String data_body) {
		this.data_body = data_body;
	}

	public String getData_sign() {
		return data_sign;
	}

	public void setData_sign(String data_sign) {
		this.data_sign = data_sign;
	} 
	
}
