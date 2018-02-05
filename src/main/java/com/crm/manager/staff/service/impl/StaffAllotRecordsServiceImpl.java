package com.crm.manager.staff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.staff.dao.IStaffAllotRecordsDao;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.service.IStaffAllotRecordsService;

@Service
public class StaffAllotRecordsServiceImpl implements IStaffAllotRecordsService{
	
	@Autowired
	private IStaffAllotRecordsDao staffAllotRecordsDao;

	@Override
	public boolean addStaffAllotRecords(StaffAllotRecordsDTO staffAllotRecordsDTO) {
		if(staffAllotRecordsDTO == null){
			return false;
		}
		return staffAllotRecordsDao.inserStaffAllotRecords(staffAllotRecordsDTO) > 0;
	}

	@Override
	public List<StaffAllotRecordsDTO> queryStaffAllotRecordsByStaffNo(String allotRecordsTable, String staffNo) {
		return staffAllotRecordsDao.queryStaffAllotRecordsByStaffNo(allotRecordsTable, staffNo);
	}

	@Override
	public int queryMemberNum(String allotRecordsTable, String staffNo) {
		return staffAllotRecordsDao.queryMemberNum(allotRecordsTable, staffNo);
	}


}
