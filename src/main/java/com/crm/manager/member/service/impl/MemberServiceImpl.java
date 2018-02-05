package com.crm.manager.member.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.common.utils.MemberUtils;
import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberCodeService;
import com.crm.manager.member.service.IMemberService;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.dto.StaffDTO;
import com.crm.manager.staff.service.IStaffAllotRecordsService;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemberServiceImpl implements IMemberService{
	
	@Autowired
	private IMemberDao 	memberDao;
	
	@Autowired
	private IStaffService staffService;
	
	@Autowired
	private IMemberCodeService memberCodeService;
	
	@Autowired
	private IStaffAllotRecordsService staffAllotRecordsService;

	@Override
	@Transactional
	public boolean addMember(MemberDTO member) {
		if(member == null || StringUtils.isBlank(member.getId()) || StringUtils.isBlank(member.getAppPlatform())){
			return false;
		}
		//查询会员人数最少的工作人员编号进行分配
		StaffDTO staff = staffService.queryMinMemberNumStaff(AppTableEnum.getStaffTableByType(member.getAppPlatform()));
		if(staff == null || StringUtils.isBlank(staff.getStaffNo())){
			return false;
		}
		member.setStaffNo(staff.getStaffNo());
		StaffAllotRecordsDTO staffAllotRecordsDTO = new StaffAllotRecordsDTO();
		//新增分配记录
		staffAllotRecordsDTO.setStaffNo(staff.getStaffNo());
		staffAllotRecordsDTO.setMemberId(member.getId());
		staffAllotRecordsDTO.setAllotRecordsTable(member.getAppPlatform());

		boolean result = staffAllotRecordsService.addStaffAllotRecords(staffAllotRecordsDTO);
		if(result){
			result = memberDao.insertMember(member) > 0;
		}
		//更新工作人员表
		staff.setMemberNum(staff.getMemberNum()+1);
		staff.setStaffTable(member.getAppPlatform());
		if(result){
			result = staffService.editStaff(staff);
		}
		if(!result){
			return false;
		}
		//新增会员编码表
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCodeTable(member.getAppPlatform());
		memberCode.setAppPlatform(member.getAppPlatform());
		memberCode.setStaffNo(staff.getStaffNo());
		if(StringUtils.isBlank(member.getIndentityType())){
			return false;
		}
		//组装会员编码信息
		this.assemblyMemberCodeInfo(memberCode, member);
		
		//组装编码
		String code = MemberUtils.assemblyMemberCode(memberCode);
		if(StringUtils.isBlank(code)){
			return false;
		}
		memberCode.setMemberCode(code);
		//新增会员编码表
		result = memberCodeService.addMemberCode(memberCode);
		
		return result;
	}
	
	@Override
	public MemberDTO queryMemberById(String appPlatform, String id) {
		if(StringUtils.isBlank(appPlatform) ||  StringUtils.isBlank(id)){
			return null;
		}
		String memberTable = AppTableEnum.getMemberTableByType(appPlatform);
		if(StringUtils.isBlank(memberTable)){
			return null;
		}
		return memberDao.queryMemberById(memberTable, id);
	}
	
	@Override
	public boolean updateMember(MemberDTO member) {
		if(member == null){
			return false;
		}
		return memberDao.updateMember(member) > 0;
	}
	
	@Override
	public boolean removeMemberById(MemberDTO member) {
		if(member == null || StringUtils.isBlank(member.getMemberTable()) ||  StringUtils.isBlank(member.getId())){
			return false;
		}
		return memberDao.deleteMemberById(member.getMemberTable(), member.getId()) > 0;
	}

	@Override
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO) {
		if(memberDTO == null){
			return null;
		}
		PageHelper.startPage(memberDTO.getPageNumber(), memberDTO.getPageSize());  
		return new PageInfo<MemberDTO>(memberDao.queryMember(memberDTO));
	}
	
	
	private void assemblyMemberCodeInfo(MemberCodeDTO memberCode, MemberDTO member){
		if(StringUtils.isBlank(member.getIndentityType()) || StringUtils.isBlank(member.getIndentityNo())){
			return;
		}
		//身份证号
		String indentityNo = member.getIndentityNo();
		String indentityType = member.getIndentityType();
		String birthdate = "";
		String province = "";
		if("01".equals(indentityType)){
			birthdate = indentityNo.substring(8, 14);
			if(indentityNo.length() != 18){
				birthdate = indentityNo.substring(6, 12);
			}
			province = indentityNo.substring(0, 2);
		}
		
		//查询最后一位客户编号
		Integer num = memberCodeService.queryLastCodeNo(memberCode.getMemberCodeTable());
		if(num != null){
			memberCode.setCodeNo(String.format("%08d", num + 1));
		} else {
			memberCode.setCodeNo(String.format("%08d", 1));
		}
		memberCode.setMemberId(member.getId());
		memberCode.setProvince(province);
		memberCode.setFinancialLevel(member.getFinancialLevel());
		memberCode.setInvestmentAmount("0000");
		memberCode.setIsInvestment(0);
		memberCode.setRegisterDate(member.getRegisterDate());
		memberCode.setBirthdate(birthdate);
		memberCode.setSex(member.getSex());
	}

}
