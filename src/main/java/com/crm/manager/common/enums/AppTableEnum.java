package com.crm.manager.common.enums;

public enum AppTableEnum {
	T_A_APPTABLE("A", "融侨宝", "t_a_member", "t_a_member_code",
			"t_a_staff", "t_a_staff_allot_records", "t_a_investment_records"),
	T_B_APPTABLE("B", "金管家", "t_b_member", "t_b_member_code",
			"t_b_staff", "t_b_staff_allot_records", "t_b_investment_records"),
	T_C_APPTABLE("C", "融侨普惠", "t_c_member", "t_c_member_code",
			"t_c_staff", "t_c_staff_allot_records", "t_c_investment_records");
	
	private String type;
	private String appName;
	private String memberTable;
	private String memberCodeTable;
	private String staffTable;
	private String allotRecordsTable;
	private String investmentRecordsTable;
	
	private AppTableEnum(String type, String appName, String memberTable, String memberCodeTable, String staffTable,
			String allotRecordsTable, String investmentRecordsTable) {
		this.type = type;
		this.appName = appName;
		this.memberTable = memberTable;
		this.memberCodeTable = memberCodeTable;
		this.staffTable = staffTable;
		this.allotRecordsTable = allotRecordsTable;
		this.investmentRecordsTable = investmentRecordsTable;
	}

	public static AppTableEnum getAppTableEnumByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getName(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.appName;  
	        }  
	    }  
	    return null;  
	} 
	
	public static String getMemberTableByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.memberTable;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getMemberCodeTableByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.memberCodeTable;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getStaffTableByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.staffTable;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getAllotRecordsTableByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.allotRecordsTable;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getInvestmentRecordsTableByType(String type) {  
	    for (AppTableEnum mte : AppTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.investmentRecordsTable;  
	        }  
	    }  
	    return null;  
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMemberTable() {
		return memberTable;
	}

	public void setMemberTable(String memberTable) {
		this.memberTable = memberTable;
	}

	public String getMemberCodeTable() {
		return memberCodeTable;
	}

	public void setMemberCodeTable(String memberCodeTable) {
		this.memberCodeTable = memberCodeTable;
	}

	public String getStaffTable() {
		return staffTable;
	}

	public void setStaffTable(String staffTable) {
		this.staffTable = staffTable;
	}

	public String getAllotRecordsTable() {
		return allotRecordsTable;
	}

	public void setAllotRecordsTable(String allotRecordsTable) {
		this.allotRecordsTable = allotRecordsTable;
	}

	public String getInvestmentRecordsTable() {
		return investmentRecordsTable;
	}

	public void setInvestmentRecordsTable(String investmentRecordsTable) {
		this.investmentRecordsTable = investmentRecordsTable;
	}
	
}
