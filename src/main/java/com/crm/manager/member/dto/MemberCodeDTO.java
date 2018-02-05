package com.crm.manager.member.dto;

import javax.persistence.Transient;

import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.common.enums.AppTableEnum;

public class MemberCodeDTO   extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5691634491994036533L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 会员编码
	 */
	private String memberCode;
	
	/**
	 * 会员ID
	 */
	private String memberId;
	
	/**
	 * 省份(身份证对应数字)
	 */
	private String province;
	
	/**
	 * app平台(A:融侨宝, B:金管家, C:融侨普惠)
	 */
	private String appPlatform;
	
	/**
	 * 是否投资理财(0:否，1::是)
	 */
	private Integer isInvestment;
	
	/**
	 * 理财等级(0-5)
	 */
	private Integer financialLevel;
	
	/**
	 * 投资金额(1-9999元起算1w=0001,逢1w进1)
	 */
	private String investmentAmount;
	
	/**
	 * 注册日期(年月:1801)
	 */
	private String registerDate;
	
	/**
	 * 出生日期(年月日:920623)
	 */
	private String birthdate;
	
	/**
	 * '性别(1:男, 2:女)
	 */
	private Integer sex;
	
	/**
	 * 工作人员编号(从G-Z)
	 */
	private String staffNo;
	
	/**
	 * 编号
	 */
	private String codeNo;
	
	/**
     * 会员编码对应表
     */
    @Transient 
    private String memberCodeTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAppPlatform() {
		return appPlatform;
	}

	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	public Integer getIsInvestment() {
		return isInvestment;
	}

	public void setIsInvestment(Integer isInvestment) {
		this.isInvestment = isInvestment;
	}

	public Integer getFinancialLevel() {
		return financialLevel;
	}

	public void setFinancialLevel(Integer financialLevel) {
		this.financialLevel = financialLevel;
	}

	public String getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	
	public String getMemberCodeTable() {
		return memberCodeTable;
	}

	public void setMemberCodeTable(String memberCodeTable) {
		this.memberCodeTable = AppTableEnum.getMemberCodeTableByType(memberCodeTable);
	}

	@Override
	public String toString() {
		return "MemberCodeDTO [id=" + id + ", memberCode=" + memberCode + ", memberId=" + memberId + ", province="
				+ province + ", appPlatform=" + appPlatform + ", isInvestment=" + isInvestment + ", financialLevel="
				+ financialLevel + ", investmentAmount=" + investmentAmount + ", registerDate=" + registerDate
				+ ", birthdate=" + birthdate + ", sex=" + sex + ", staffNo=" + staffNo + ", codeNo=" + codeNo + "]";
	}
	
}
