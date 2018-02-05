package com.crm.manager.staff.dto;

import java.util.Date;

import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.common.enums.AppTableEnum;

public class StaffAllotRecordsDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5691634491994036533L;
	
	/**
	 * 记录ID
	 */
	private Long id;
	
	/**
	 * 工作人员ID
	 */
	private String staffNo;
	
	/**
	 * app平台(A:融侨宝, B:金管家, C:融侨普惠)
	 */
	private String appPlatform;
	
	/**
	 * 会员ID
	 */
	private String memberId;
	
	/**
	 * 分配时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date allotTime;
	
	/**
     * 分配记录对应表
     */
    @Transient 
    private String allotRecordsTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	
	public String getAppPlatform() {
		return appPlatform;
	}

	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getAllotTime() {
		return allotTime;
	}

	public void setAllotTime(Date allotTime) {
		this.allotTime = allotTime;
	}
	
	public String getAllotRecordsTable() {
		return allotRecordsTable;
	}

	public void setAllotRecordsTable(String allotRecordsTable) {
		this.allotRecordsTable = AppTableEnum.getAllotRecordsTableByType(allotRecordsTable);
	}

}