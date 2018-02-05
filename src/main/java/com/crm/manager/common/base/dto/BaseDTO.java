package com.crm.manager.common.base.dto;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -250118731239275742L;
	
	@Transient 
	private Integer pageNumber;
	
	@Transient 
	private Integer pageSize;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}