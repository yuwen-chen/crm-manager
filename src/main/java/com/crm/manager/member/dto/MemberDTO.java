package com.crm.manager.member.dto;

import javax.persistence.Transient;

import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.common.enums.AppTableEnum;

public class MemberDTO  extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437193052447152335L;
	
	/**
	 * 会员ID
	 */
    private String id;	
    
    /**
     * 真实姓名
     */
	private String realName;
	
	/**
     * 手机号
     */
    private String phone;
    
    /**
     * 证件类型
     */
    private String indentityType;
    
    /**
     * 证件号
     */
    private String indentityNo;
    
    /**
     * 性别(1:男, 2:女)
     */
    private Integer sex;
    
    /**
     * 用户状态(00:正常,10:冻结)
     */
    private String userStatus;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 电子邮件
     */
    private String email;
    
    /**
     * app平台
     */
    private String appPlatform;
    
    /**
     * 理财等级(0-5)
     */
    private Integer financialLevel;
    
    /**
     * 注册日期(年月:1801)
     */
    private String registerDate;
    
    /**
     * 工作人员编号
     */
    private String staffNo;
    
    /**
     * 会员对应表
     */
    @Transient 
    private String memberTable;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndentityType() {
		return indentityType;
	}

	public void setIndentityType(String indentityType) {
		this.indentityType = indentityType;
	}

	public String getIndentityNo() {
		return indentityNo;
	}

	public void setIndentityNo(String indentityNo) {
		this.indentityNo = indentityNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppPlatform() {
		return appPlatform;
	}

	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	public Integer getFinancialLevel() {
		return financialLevel;
	}

	public void setFinancialLevel(Integer financialLevel) {
		this.financialLevel = financialLevel;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getMemberTable() {
		return memberTable;
	}

	public void setMemberTable(String memberTable) {
		this.memberTable = AppTableEnum.getMemberTableByType(memberTable);
		//this.memberTable = memberTable;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", realName=" + realName + ", phone=" + phone + ", indentityType="
				+ indentityType + ", indentityNo=" + indentityNo + ", sex=" + sex + ", userStatus=" + userStatus
				+ ", address=" + address + ", email=" + email + ", appPlatform=" + appPlatform + ", financialLevel="
				+ financialLevel + ", registerDate=" + registerDate + ", staffNo=" + staffNo + ", memberTable="
				+ memberTable + "]";
	}
	
}
