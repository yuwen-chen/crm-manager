package com.crm.manager.staff.service;

import java.util.List;

import com.crm.manager.staff.dto.StaffAllotRecordsDTO;

public interface IStaffAllotRecordsService {
	
	/**
	 * 新增工作人员分配记录
	 * @param staffDTO
	 */
	public boolean addStaffAllotRecords(StaffAllotRecordsDTO staffAllotRecordsDTO);
	
	/**
	 * 通过员工ID查询工作人员分配记录
	 * @param staffNo
	 */
	public List<StaffAllotRecordsDTO> queryStaffAllotRecordsByStaffNo(String allotRecordsTable, String staffNo);
	
	/**
	 * 查询会员人数
	 * @param staffNo
	 * @return
	 */
	public int queryMemberNum(String allotRecordsTable, String staffNo);
}