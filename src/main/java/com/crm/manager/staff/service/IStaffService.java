package com.crm.manager.staff.service;

import com.crm.manager.staff.dto.StaffDTO;
import com.github.pagehelper.PageInfo;

public interface IStaffService {
	
	/**
	 * 修改工作人员信息
	 * @param staffDTO
	 */
	public boolean editStaff(StaffDTO staffDTO);
	
	/**
	 * 分页查询工作人员信息
	 * @param memberDTO
	 * @return
	 */
	public PageInfo<StaffDTO> queryStaff(StaffDTO staffDTO);
	
	/**
	 * 通过员工编号查询工作人员信息
	 * @param staffNo
	 * @return
	 */
	public StaffDTO queryStaffByStaffNo(StaffDTO staffDTO);
	
	
	/**
	 * 删除工作人员信息
	 * @param staffNo
	 */
	public boolean removeStaff(StaffDTO staffDTO);
	
	/**
	 * 查询工作人员会员人数最少的一个
	 * @param staffNo
	 */
	public StaffDTO queryMinMemberNumStaff(String staffTable);
}