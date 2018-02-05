package com.crm.manager.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.crm.manager.member.dto.MemberCodeDTO;

@Mapper
public interface IMemberCodeDao{
	
	/**
     * 插入会员编码信息
     *
     * @param memberCodeDto
     * @return
     */
    @Insert("<script>" +
    		"insert IGNORE into ${memberCodeTable} ( " + 
    		"id, " +
    		"member_code, " +
    		"member_id, " +
    		"province, " +
		    "	<if test='isInvestment != null'>" + 
		    "		is_investment, " +
	        "	</if>" +
		    "	<if test='financialLevel != null'>" + 
		    "		financial_level, " +
	        "	</if>" +
		    "	<if test='investmentAmount != null'>" + 
		    "		investment_amount, " +
	        "	</if>" +
    		"register_date, " +
    		"birthdate, " +
    		"sex, " +
    		"staff_no, " +
    		"	<if test='codeNo != null'>" + 
		    "		code_no, " +
	        "	</if>" +
    		"create_time, " +
    		"update_time "+
    		") values ( " +
            "#{id}, "+
    		"#{memberCode}, " +
            "#{memberId}, " +
    		"#{province}, " +
		    "	<if test='isInvestment != null'>" + 
            "		#{isInvestment}, " +
	        "	</if>" +
		    "	<if test='financialLevel != null'>" + 
            "		#{financialLevel}, " +
	        "	</if>" +
		    "	<if test='investmentAmount != null'>" + 
		    "		#{investmentAmount}, " +
	        "	</if>" +
    		"#{registerDate}, " +
    		"#{birthdate}, " +
    		"#{sex}, " +
    		"#{staffNo}, " +
    		"	<if test='codeNo != null'>" + 
		    "		#{codeNo}, " +
	        "	</if>" +
            "now(), now()" +
    		") "+
			"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertMemberCode(MemberCodeDTO memberCodeDto);
    
    
    /**
     * 通过会员ID更新会员编码信息
     *
     * @param memberCodeDto
     * @return
     */
    @Update("<script>" +
    		"update ${memberCodeTable} " + 
    		"<set> " +
		    "	<if test='memberCode != null'>" + 
		    "		member_code = #{memberCode}, " +
	        "	</if>" +
		    "	<if test='province != null'>" + 
		    "		province = #{province}, " +
	        "	</if>" +
		    "	<if test='isInvestment != null'>" + 
		    "		is_investment = #{isInvestment}, " +
	        "	</if>" +
		    "	<if test='financialLevel != null'>" + 
		    "		financial_level = #{financialLevel}, " +
	        "	</if>" +
		    "	<if test='investmentAmount != null'>" + 
		    "		investment_amount = #{investmentAmount}, " +
	        "	</if>" +
		    "	<if test='registerDate != null'>" + 
		    "		register_date = #{registerDate}, " +
	        "	</if>" +
		    "	<if test='birthdate != null'>" + 
		    "		birthdate = #{birthdate}, " +
	        "	</if>" +
		    "	<if test='sex != null'>" + 
		    "		sex = #{sex}, " +
	        "	</if>" +
		    "	<if test='staffNo != null'>" + 
		    "		staff_no = #{staffNo}, " +
	        "	</if>" +
		    "	<if test='codeNo != null'>" + 
		    "		code_no = #{codeNo}, " +
	        "	</if>" +
    		"	update_time = now() "+
	        "</set>" +
    		"where member_id = #{memberId} " +
			"</script>")
    public int updateMemberCodeByMemberId(MemberCodeDTO memberCodeDto);
    
    /**
     * 查询会员编码信息
     */
    @Select(" SELECT * FROM ${memberCodeTable}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "memberCode", column = "member_code"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "province", column = "province"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "isInvestment", column = "is_investment"),
        @Result(property = "financialLevel", column = "financial_level"),
        @Result(property = "investmentAmount", column = "investment_amount"),
        @Result(property = "registerDate", column = "register_date"),
        @Result(property = "birthdate", column = "birthdate"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "codeNo", column = "code_no")
    })
    public List<MemberCodeDTO> queryAllMemberCode(@Param(value = "memberCodeTable") String memberCodeTable);
    
    /**
     * 查询会员编码信息
     */
    @Select("<script>" +
    		"SELECT * FROM ${memberCodeTable} " +
		    "<where> " +
		    "	<if test='id != null'>" + 
	        "		and id = #{id} "+
	        "	</if>" +
	        "	<if test='memberCode != null'>" + 
	        "		and member_code = #{memberCode} "+
	        "	</if>" +
	        "	<if test='memberId != null'>" + 
	        "		and member_id = #{memberId} "+
	        "	</if>" +
	        "	<if test='province != null'>" + 
	        "		and province = #{province} "+
	        "	</if>" +
	        "	<if test='appPlatform != null'>" + 
	        "		and app_platform = #{appPlatform} "+
	        "	</if>" +
	        "	<if test='isInvestment != null'>" + 
	        "		and is_investment = #{isInvestment} "+
	        "	</if>" +
	        "	<if test='financialLevel != null'>" + 
	        "		and financial_level = #{financialLevel} "+
	        "	</if>" +
	        "	<if test='investmentAmount != null'>" + 
	        "		and investment_amount = #{investmentAmount} "+
	        "	</if>" +
	        "	<if test='registerDate != null'>" + 
	        "		and register_date = #{registerDate} "+
	        "	</if>" +
	        "	<if test='birthdate != null'>" + 
	        "		and birthdate = #{birthdate} "+
	        "	</if>" +
	        "	<if test='sex != null'>" + 
	        "		and sex = #{sex} "+
	        "	</if>" +
	        "	<if test='staffNo != null'>" + 
	        "		and staff_no = #{staffNo} "+
	        "	</if>" +
	        "	<if test='codeNo != null'>" + 
	        "		and code_no = #{codeNo} "+
	        "	</if>" +
	        "</where>" +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "memberCode", column = "member_code"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "province", column = "province"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "isInvestment", column = "is_investment"),
        @Result(property = "financialLevel", column = "financial_level"),
        @Result(property = "investmentAmount", column = "investment_amount"),
        @Result(property = "registerDate", column = "register_date"),
        @Result(property = "birthdate", column = "birthdate"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "codeNo", column = "code_no")
    })
    public List<MemberCodeDTO> queryMemberCode(MemberCodeDTO memberCodeDTO);
    
    /**
     * 通过会员ID查询会员编码信息
     */
    @Select("<script>" +
    		"SELECT * FROM ${memberCodeTable} " +
		    "where member_id = #{memberId}" +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "memberCode", column = "member_code"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "province", column = "province"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "isInvestment", column = "is_investment"),
        @Result(property = "financialLevel", column = "financial_level"),
        @Result(property = "investmentAmount", column = "investment_amount"),
        @Result(property = "registerDate", column = "register_date"),
        @Result(property = "birthdate", column = "birthdate"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "codeNo", column = "code_no")
    })
    public MemberCodeDTO queryMemberCodeByMemberId(@Param(value = "memberCodeTable") String memberCodeTable, @Param(value = "memberId") String memberId);
    
    /**
     * 查询最后一个客户编号
     */
    @Select("<script>" +
    		"SELECT code_no FROM ${memberCodeTable} order by code_no desc limit 1" +
		    "</script>")
    public Integer queryLastCodeNo(@Param(value = "memberCodeTable") String memberCodeTable);
    
    /**
     * 通过会员ID删除会员编码信息
     */
    @Delete("<script>" +
    		"DELETE FROM ${memberCodeTable} " +
		    "where member_id = #{memberId}" +
		    "</script>")
    public int deleteMemberCodeByMemberId(@Param(value = "memberCodeTable") String memberCodeTable, @Param(value = "memberId") String memberId);

}
