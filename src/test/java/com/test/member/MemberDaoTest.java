package com.test.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.dto.MemberDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberDaoTest {
	
	@Autowired
	IMemberDao memberMapper;
	
	@Test
	public void TestAdd(){
		for(int i=0;i<7;i++){
			MemberDTO member = new MemberDTO();
			member.setId("UID000"+i);
			member.setPhone("13526455623");
			member.setRealName("张三"+i);
			member.setIndentityType("01");
			member.setIndentityNo("315645894522244");
			member.setSex(1);
			member.setAddress("上海");
			member.setUserStatus("01");
			member.setEmail("123@qq.com");
			member.setAppPlatform("C");
			member.setFinancialLevel(1);
			member.setStaffNo("G");
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			member.setRegisterDate(sdf.format(new Date()));
			member.setMemberTable("B");
			memberMapper.insertMember(member);
		}
	}
	
	@Test
	public void TestUpdate(){
		MemberDTO member = new MemberDTO();
		member.setId("UID0001");
		member.setPhone("4444444444444");
		member.setRealName("张三aaaaaa");
		member.setIndentityType("01");
		member.setIndentityNo("315645894522244");
		member.setSex(1);
		member.setAddress("上海");
		member.setUserStatus("01");
		member.setEmail("123@qq.com");
		member.setAppPlatform("C");
		member.setFinancialLevel(1);
		member.setStaffNo("G");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		member.setRegisterDate(sdf.format(new Date()));
		member.setMemberTable("A");
		int a= memberMapper.updateMember(member);
		System.out.println(a);
	}
	
	@Test
	public void TestQuery(){
		//分页插件: 查询第1页，每页10行 
	    PageHelper.startPage(1, 10);  
	    MemberDTO member = new MemberDTO();
	    member.setMemberTable(AppTableEnum.getMemberTableByType("A"));
	    member.setId("UID0001");
		member.setPhone("13526455623");
		member.setRealName("张三1");
		member.setIndentityType("01");
		member.setIndentityNo("315645894522244");
		member.setSex(1);
		member.setAddress("上海");
		member.setUserStatus("01");
		member.setEmail("123@qq.com");
		//member.setAppPlatform(MemberTableEnum.T_C_MEMBER.getType());
		member.setFinancialLevel(1);
	    PageInfo<MemberDTO> page =new PageInfo<MemberDTO>(memberMapper.queryMember(member));
		List<MemberDTO> list = page.getList();
		for(MemberDTO mem : list){
			System.out.println(mem);
		}
		page.getPageNum();
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getPages());
		
	}
	
	@Test
	public void TestQueryById(){
		MemberDTO member = new MemberDTO();
		member.setMemberTable("A");
	    MemberDTO member1 = memberMapper.queryMemberById(member.getMemberTable(), "UID0000");
		System.out.println(member1);
	}
	
	@Test
	public void TestDeleteById(){
		MemberDTO member = new MemberDTO();
		member.setMemberTable("A");
	    int a = memberMapper.deleteMemberById(member.getMemberTable(), "UID0000");
		System.out.println(a);
	}

}
