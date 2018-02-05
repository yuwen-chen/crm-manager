package com.test.staff;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.staff.dao.IStaffAllotRecordsDao;
import com.crm.manager.staff.dao.IStaffDao;
import com.crm.manager.staff.dto.StaffDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StaffDaoTest {
	
	@Autowired
	private IStaffDao staffDao;
	
	@Autowired
	private IStaffAllotRecordsDao staffAllotRecordsDao;
	
	@Test
	public void inserTest(){
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffNo("H");
		staffDTO.setName("zhangsan");
		staffDTO.setPhone("110");
		staffDTO.setSex(1);
		staffDTO.setStatus("01");
		staffDTO.setEmail("1110@qq.com");
		staffDTO.setMemberNum(staffAllotRecordsDao.queryMemberNum("t_a_staff", "G"));
		int result = staffDao.insertStaff(staffDTO );
		System.out.println(result);
	}
	
	@Test
	public void updateTest(){
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffNo("G");
		staffDTO.setName("zhangsan1111");
		staffDTO.setPhone("110aaa");
		staffDTO.setSex(2);
		staffDTO.setStatus("00");
		staffDTO.setEmail("1110aaaaaaa@qq.com");
		staffDTO.setMemberNum(staffAllotRecordsDao.queryMemberNum("t_a_staff", "G")+2);
		int result = staffDao.updateStaff(staffDTO );
		System.out.println(result);
	}
	
	
	@Test 
	public void queryAllTest(){
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffNo("H");
		staffDTO.setName("zhangsan");
		staffDTO.setPhone("110");
		staffDTO.setSex(1);
		staffDTO.setStatus("01");
		staffDTO.setEmail("1110@qq.com");
		staffDTO.setMemberNum(staffAllotRecordsDao.queryMemberNum("t_a_staff", "G"));
		List<StaffDTO> list = staffDao.queryAllStaff("t_a_staff");
		for(StaffDTO staff : list){
			System.out.println(staff);
		}
		
	}
	
	@Test 
	public void queryTest(){
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffNo("H");
		staffDTO.setName("zhangsan");
		staffDTO.setPhone("110");
		staffDTO.setSex(1);
		staffDTO.setStatus("01");
		staffDTO.setEmail("1110@qq.com");
		staffDTO.setMemberNum(staffAllotRecordsDao.queryMemberNum("t_a_staff", "G"));
		List<StaffDTO> list = staffDao.queryStaff(staffDTO);
		for(StaffDTO staff : list){
			System.out.println(staff);
		}
	}
	
	
	@Test 
	public void queryTestByStaffNo(){
		StaffDTO staff = staffDao.queryStaffByStaffNo("t_a_staff", "G");
		System.out.println(staff);
	}
	
	@Test 
	public void queryTestOrderBYmemberNum(){
		StaffDTO staff = staffDao.queryMinMemberNumStaff("t_a_staff");
		System.out.println(staff.getStaffNo());
	}
	
	@Test 
	public void deleteTest(){
		int trsult = staffDao.deleteStaffByStaffNo("t_a_staff", "G");
		System.out.println(trsult);
	}

}
