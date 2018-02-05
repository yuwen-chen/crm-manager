package com.crm.manager.member.service;

import com.crm.manager.member.dto.MemberDTO;
import com.github.pagehelper.PageInfo;

public interface IMemberService {
	
	/**
	 * 新增会员
	 * @param memberDTO
	 * @return
	 */
	public boolean addMember(MemberDTO memberDTO);
	
	/**
	 * 通过ID查询会员
	 * @param id
	 * @return
	 */
	public MemberDTO queryMemberById(String memberTable, String id);
	
	/**
	 * 更新会员
	 * @param memberDTO
	 * @return
	 */
	public boolean updateMember(MemberDTO memberDTO);
	
	/**
	 * 通过ID删除会员
	 * @param memberDTO
	 * @return
	 */
	public boolean removeMemberById(MemberDTO memberDTO);
	
	/**
	 * 分页查询会员
	 * @param memberDTO
	 * @return
	 */
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO);
}
