package com.crm.manager.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.crm.manager.member.dto.MemberCodeDTO;

public class MemberUtils {
	
	public static String assemblyMemberCode(MemberCodeDTO memberCode){
		if(memberCode == null){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getAppPlatform())){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getProvince())){
			return null;
		}
		if(memberCode.getIsInvestment() == null){
			return null;
		}
		if(memberCode.getFinancialLevel() == null){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getInvestmentAmount())){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getRegisterDate())){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getBirthdate())){
			return null;
		}
		if(memberCode.getSex() == null ){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getStaffNo())){
			return null;
		}
		if(StringUtils.isBlank(memberCode.getCodeNo())){
			return null;
		}
		StringBuffer code = new StringBuffer(memberCode.getProvince()).
				append(memberCode.getAppPlatform()).append(memberCode.getIsInvestment()).append(memberCode.getFinancialLevel())
				.append(memberCode.getInvestmentAmount()).append(memberCode.getRegisterDate()).append(memberCode.getBirthdate())
				.append(memberCode.getSex()).append(memberCode.getStaffNo()).append(memberCode.getCodeNo());
		return code.toString();
	}
	
	
	public static void main(String[] args) {
		
	}

}
