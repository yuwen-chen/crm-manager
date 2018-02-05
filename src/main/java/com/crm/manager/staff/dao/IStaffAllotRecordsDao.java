package com.crm.manager.staff.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.crm.manager.staff.dto.StaffAllotRecordsDTO;

@Mapper
public interface IStaffAllotRecordsDao{
    
    /**
     * 批量插入工作人员分配记录
     *
     * @param member
     * @return
     */
    @Insert("<script>" +
    		"insert into ${allotRecordsTable} ( " + 
    		"id, " +
    		"staff_no, " +
    		"member_id, " +
    		"allot_time, " +
    		"create_time, " +
    		"update_time "+
    		") values " +
    		"<foreach collection='list' item='item' separator=',' > " +
    		"	( " +
            "		#{item.id}, "+
    		"		#{item.staffNo}, " +
            "		#{item.memberId}, " +
            "		now(), now(), now()" +
    		"	) " +
            "</foreach>" +
			"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int batchInserStaffAllotRecords(@Param("list") List<StaffAllotRecordsDTO> list);
	
	/**
     * 插入工作人员分配记录
     *
     * @param member
     * @return
     */
    @Insert("<script>" +
    		"insert into ${allotRecordsTable} ( " + 
    		"id, " +
    		"staff_no, " +
    		"member_id, " +
    		"allot_time, " +
    		"create_time, " +
    		"update_time "+
    		") values ( " +
            "#{id}, "+
    		"#{staffNo}, " +
            "#{memberId}, " +
            "now(), now(), now()" +
    		") "+
			"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int inserStaffAllotRecords(StaffAllotRecordsDTO staffAllotRecordsDTO);
    
    
    /**
     * 通过员工ID查询工作人员分配记录
     */
    @Select(" SELECT * FROM ${allotRecordsTable} where staff_no= #{staffNo} ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "allotTime", column = "allot_time")
    })
    public List<StaffAllotRecordsDTO> queryStaffAllotRecordsByStaffNo(@Param(value = "allotRecordsTable") String allotRecordsTable, @Param(value = "staffNo") String staffNo);
    
    /**
     * 查询会员人数
     */
    @Select("SELECT count(*) FROM ${allotRecordsTable} where staff_no= #{staffNo} ")
    public int queryMemberNum(@Param(value = "allotRecordsTable") String allotRecordsTable, @Param(value = "staffNo") String staffNo);

}
