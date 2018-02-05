package com.crm.manager.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.crm.manager.member.dto.MemberDTO;

@Mapper
public interface IMemberDao{
	
	/**
     * 插入会员信息
     *
     * @param member
     * @return
     */
    @Insert("<script>" +
    		"insert IGNORE into ${memberTable} ( " + 
    		"id, " +
    		"real_name, " +
    		"phone, " +
    		"indentity_type, " +
    		"indentity_no, " +
    		"sex, " +
    		"address, " +
		    "	<if test='userStatus != null'>" + 
		    "		user_status, " +
	        "	</if>" +
    		"email, " +
		    "	<if test='financialLevel != null'>" + 
		    "		financial_level, " +
	        "	</if>" +
    		"register_date, " +
    		"staff_no, " +
    		"create_time, " +
    		"update_time "+
    		") values ( " +
            "#{id}, "+
    		"#{realName}, " +
            "#{phone}, " +
    		"#{indentityType}, " +
            "#{indentityNo}, " +
    		"#{sex}, " +
            "#{address}, " +
		    "	<if test='userStatus != null'>" + 
            "		#{userStatus}, " +
	        "	</if>" +
            "#{email}, " +
		    "	<if test='financialLevel != null'>" + 
            "		#{financialLevel}, " +
	        "	</if>" +
    		"#{registerDate}, " +
    		"#{staffNo}, " +
            "now(), now()" +
    		") "+
			"</script>")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertMember(MemberDTO memberDto);
    
    
	/**
     * 更新会员信息
     *
     * @param member
     * @return
     */
    @Update("<script>" +
    		"update ${memberTable} " + 
    		"<set> " +
		    "	<if test='realName != null'>" + 
		    "		real_name = #{realName}, " +
	        "	</if>" +
		    "	<if test='phone != null'>" + 
		    "		phone = #{phone}, " +
	        "	</if>" +
		    "	<if test='indentityType != null'>" + 
		    "		indentity_type = #{indentityType}, " +
	        "	</if>" +
		    "	<if test='indentityNo != null'>" + 
		    "		indentity_no = #{indentityNo}, " +
	        "	</if>" +
		    "	<if test='sex != null'>" + 
		    "		sex = #{sex}, " +
	        "	</if>" +
		    "	<if test='address != null'>" + 
		    "		address = #{address}, " +
	        "	</if>" +
		    "	<if test='userStatus != null'>" + 
		    "		user_status = #{userStatus}, " +
	        "	</if>" +
		    "	<if test='email != null'>" + 
		    "		email = #{email}, " +
	        "	</if>" +
		    "	<if test='financialLevel != null'>" + 
		    "		financial_level = #{financialLevel}, " +
	        "	</if>" +
		    "	<if test='registerDate != null'>" + 
		    "		register_date = #{registerDate}, " +
	        "	</if>" +
		    "	<if test='staffNo != null'>" + 
		    "		staff_no = #{staffNo}, " +
	        "	</if>" +
    		"	update_time = now() "+
	        "</set>" +
    		"where id = #{id} " +
			"</script>")
    public int updateMember(MemberDTO memberDto);
    
    /**
     * 查询会员信息
     */
    @Select(" SELECT * FROM ${memberTable} ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "realName", column = "real_name"),
        @Result(property = "indentityType", column = "indentity_type"),
        @Result(property = "indentityNo", column = "indentity_no"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "address", column = "address"),
        @Result(property = "userStatus", column = "user_status"),
        @Result(property = "mail", column = "mail"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "financialLevel", column = "financial_level"),
        @Result(property = "registerDate", column = "register_date"),
        @Result(property = "staffNo", column = "staff_no")
    })
    public List<MemberDTO> queryAllMember(@Param(value = "memberTable") String memberTable);
    
    /**
     * 查询会员信息
     */
    @Select("<script>" +
    		"SELECT * FROM ${memberTable} " +
		    "<where> " +
		    "	<if test='id != null'>" + 
	        "		and id = #{id} "+
	        "	</if>" +
	        "	<if test='realName != null'>" + 
	        "		and real_name = #{realName} "+
	        "	</if>" +
	        "	<if test='phone != null'>" + 
	        "		and phone = #{phone} "+
	        "	</if>" +
	        "	<if test='indentityType != null'>" + 
	        "		and indentity_type = #{indentityType} "+
	        "	</if>" +
	        "	<if test='indentityNo != null'>" + 
	        "		and indentity_no = #{indentityNo} "+
	        "	</if>" +
	        "	<if test='sex != null'>" + 
	        "		and sex = #{sex} "+
	        "	</if>" +
	        "	<if test='indentityNo != null'>" + 
	        "		and indentity_no = #{indentityNo} "+
	        "	</if>" +
	        "	<if test='sex != null'>" + 
	        "		and sex = #{sex} "+
	        "	</if>" +
	        "	<if test='userStatus != null'>" + 
	        "		and user_status = #{userStatus} "+
	        "	</if>" +
	        "	<if test='email != null'>" + 
	        "		and email = #{email} "+
	        "	</if>" +
	        "	<if test='appPlatform != null'>" + 
	        "		and app_platform = #{appPlatform} "+
	        "	</if>" +
	        "	<if test='financialLevel != null'>" + 
	        "		and financial_level = #{financialLevel} "+
	        "	</if>" +
	        "	<if test='registerDate != null'>" + 
	        "		and register_date = #{registerDate} "+
	        "	</if>" +
	        "	<if test='staffNo != null'>" + 
	        "		and staff_no = #{staffNo} "+
	        "	</if>" +
	        "</where>" +
		    "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "indentityType", column = "indentity_type"),
            @Result(property = "indentityNo", column = "indentity_no"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "email", column = "email"),
            @Result(property = "appPlatform", column = "app_platform"),
            @Result(property = "financialLevel", column = "financial_level"),
            @Result(property = "registerDate", column = "register_date"),
            @Result(property = "staffNo", column = "staff_no")
    })
    public List<MemberDTO> queryMember(MemberDTO memberDTO);
    
    /**
     * 通过ID查询会员信息
     */
    @Select("<script>" +
    		"SELECT * FROM ${memberTable} " +
		    "where id = #{id} " +
		    "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "indentityType", column = "indentity_type"),
            @Result(property = "indentityNo", column = "indentity_no"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "email", column = "email"),
            @Result(property = "appPlatform", column = "app_platform"),
            @Result(property = "financialLevel", column = "financial_level"),
            @Result(property = "registerDate", column = "register_date"),
            @Result(property = "staffNo", column = "staff_no")
    })
    public MemberDTO queryMemberById(@Param(value = "memberTable") String memberTable, @Param(value = "id") String id);
    
    /**
     * 通过ID删除会员信息
     */
    @Delete("<script>" +
    		"DELETE FROM ${memberTable} " +
		    "where id = #{id} " +
		    "</script>")
    public int deleteMemberById(@Param(value = "memberTable") String memberTable, @Param(value = "id") String id);

}
