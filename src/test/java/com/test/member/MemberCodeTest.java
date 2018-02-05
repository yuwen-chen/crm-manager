package com.test.member;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.member.dao.IMemberCodeDao;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberCodeTest {
	
	@Autowired
	IMemberCodeDao memberCodeMapper;
	
	@Test
	public void TestAdd(){
		for(int i=7;i<9;i++){
			MemberCodeDTO memberCode = new MemberCodeDTO();
			memberCode.setMemberCode("");
			memberCode.setMemberId("1000"+i);
			memberCode.setProvince("31");
			memberCode.setFinancialLevel(0);
			//memberCode.setInvestmentAmount("0002");
			memberCode.setIsInvestment(1);
			memberCode.setRegisterDate("1801");
			memberCode.setBirthdate("900303");
			memberCode.setSex(1);
			memberCode.setStaffNo("0001");
			memberCode.setCodeNo("0001");
			memberCode.setMemberCodeTable("A");
			memberCodeMapper.insertMemberCode(memberCode);
		}
	}
	
	@Test
	public void TestUpdate(){
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCode("");
		memberCode.setMemberId("10001");
		memberCode.setProvince("31");
		memberCode.setFinancialLevel(0);
		memberCode.setInvestmentAmount("0001");
		memberCode.setIsInvestment(1);
		memberCode.setRegisterDate("1801");
		memberCode.setBirthdate("900303");
		memberCode.setSex(1);
		memberCode.setStaffNo("0001");
		memberCode.setCodeNo("0001");
		memberCode.setMemberCodeTable("A");
		int a= memberCodeMapper.updateMemberCodeByMemberId(memberCode);
		System.out.println(a);
	}
	
	@Test
	public void TestQuery(){
		//分页插件: 查询第1页，每页10行 
	    PageHelper.startPage(1, 10);  
	    MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCode("");
		//memberCode.setMemberId("10001");
		memberCode.setProvince("31");
		memberCode.setFinancialLevel(0);
		memberCode.setInvestmentAmount("0002");
		memberCode.setIsInvestment(1);
		memberCode.setRegisterDate("1801");
		memberCode.setBirthdate("900303");
		memberCode.setSex(1);
		memberCode.setStaffNo("0001");
		memberCode.setCodeNo("0001");
		memberCode.setMemberCodeTable("A");
	    PageInfo<MemberCodeDTO> page =new PageInfo<MemberCodeDTO>(memberCodeMapper.queryMemberCode(memberCode));
		List<MemberCodeDTO> list = page.getList();
		for(MemberCodeDTO mem : list){
			System.out.println(mem);
		}
		page.getPageNum();
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getPages());
		
	}
	
	@Test
	public void TestQueryById(){
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCodeTable("A");
	    MemberCodeDTO member1 = memberCodeMapper.queryMemberCodeByMemberId(memberCode.getMemberCodeTable(), "10001");
		System.out.println(member1);
	}
	
	@Test
	public void TestDeleteById(){
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCodeTable("A");
	    int a = memberCodeMapper.deleteMemberCodeByMemberId(memberCode.getMemberCodeTable(), "UID0000");
		System.out.println(a);
	}
	
	@Test
	public void TestLstCodeNo(){
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCodeTable("B");
	    Integer a = memberCodeMapper.queryLastCodeNo(memberCode.getMemberCodeTable());
		System.out.println(a);
	}

}
