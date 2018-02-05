package com.test.staff;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.staff.dao.IStaffAllotRecordsDao;
import com.crm.manager.staff.dao.IStaffDao;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.dto.StaffDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StaffAllotRecordsDaoTest {
	
	@Autowired
	private IStaffAllotRecordsDao staffAllotRecordsDao;
	
	@Test
	public void inserTest(){
		StaffAllotRecordsDTO staffAllotRecords = new StaffAllotRecordsDTO();
		staffAllotRecords.setStaffNo("H");
		staffAllotRecords.setMemberId("US10001");
		int result = staffAllotRecordsDao.inserStaffAllotRecords(staffAllotRecords);
		System.out.println(result);
	}
	
	@Test
	public void BatchInsertTest(){
		List<StaffAllotRecordsDTO> list = new ArrayList<StaffAllotRecordsDTO>();
		for(int i= 1; i < 10;i++){
			StaffAllotRecordsDTO staffAllotRecords = new StaffAllotRecordsDTO();
			staffAllotRecords.setStaffNo("G");
			staffAllotRecords.setMemberId("US1000"+i);
			list.add(staffAllotRecords);
		}
		int result = staffAllotRecordsDao.batchInserStaffAllotRecords(list);
		System.out.println(result);
	}
	
	
	@Test 
	public void queryMemberNumTest(){
		int result = staffAllotRecordsDao.queryMemberNum("t_a_staff","H");
			System.out.println(result);
		
	}
	
	@Test 
	public void queryTest(){
		List<StaffAllotRecordsDTO> list = staffAllotRecordsDao.queryStaffAllotRecordsByStaffNo("t_a_staff", "H");
		for(StaffAllotRecordsDTO staffAllotRecords : list){
			System.out.println(staffAllotRecords);
		}
	}
}
