package com.crm.manager.staff.dto;

import javax.persistence.Transient;

import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.common.enums.AppTableEnum;

public class StaffDTO  extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437193052447152335L;
	
	/**
	 * ID
	 */
    private Long id;	
    
    /**
	 * app平台(A:融侨宝, B:金管家, C:融侨普惠)
	 */
	private String appPlatform;
    
    /**
     * 工作人员编号
     */
	private String staffNo;
	
	/**
     * 工作人员姓名
     */
	private String name;
	
	/**
     * 手机号
     */
    private String phone;
    
    /**
     * 性别(1:男, 2:女)
     */
    private Integer sex;
    
    /**
     * 状态(00:正常,10:冻结)
     */
    private String status;
    
    /**
     * 电子邮件
     */
    private String email;
    
    /**
     * 会员人数
     */
    private Integer memberNum;
    
    /**
     * 工作人员对应表
     */
    @Transient 
    private String staffTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAppPlatform() {
		return appPlatform;
	}

	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public String getStaffTable() {
		return staffTable;
	}

	public void setStaffTable(String staffTable) {
		this.staffTable = AppTableEnum.getStaffTableByType(staffTable);
	}
	
}
