package com.crm.manager.investment.dao;
import java.math.BigDecimal;
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

import com.crm.manager.investment.dto.InvestmentRecordsDTO;

@Mapper
public interface IInvestmentRecordsDao{
	
	/**
     * 插入投资记录
     *
     * @param staffDTO
     * @return
     */
    @Insert("<script>" +
    		"insert IGNORE into ${investmentRecordsTable} ( " + 
    		"id, " +
    		"member_id, " +
    		"order_no, " +
    		"title, " +
		    "	<if test='totalPrice != null'>" + 
		    "		total_price, " +
	        "	</if>" +
    		"order_status, " +
    		"product_id, " +
    		"order_type, " +
    		"order_token, " +
    		"channel_trade_id, " +
    		"investment_time, " +
    		"create_time, " +
    		"update_time "+
    		") values ( " +
            "#{id}, "+
    		"#{memberId}, " +
    		"#{orderNo}, " +
            "#{title}, " +
		    "	<if test='totalPrice != null'>" + 
            "		#{totalPrice}, " +
	        "	</if>" +
	        "#{orderStatus}, " +
	        "#{productId}, " +
            "#{orderType}, " +
            "#{orderToken}, " +
    		"#{channelTradeId}, " +
            "now(), now(), now()" +
    		") "+
			"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO);
    
    
	/**
     * 更新投资记录
     *
     * @param staffDTO
     * @return
     */
    @Update("<script>" +
    		"update ${investmentRecordsTable} " + 
    		"<set> " +
		    "	<if test='memberId != null'>" + 
		    "		member_id = #{memberId}, " +
	        "	</if>" +
		    "	<if test='title != null'>" + 
		    "		title = #{title}, " +
	        "	</if>" +
		    "	<if test='totalPrice != null'>" + 
		    "		total_price = #{totalPrice}, " +
	        "	</if>" +
		    "	<if test='orderStatus != null'>" + 
		    "		order_status = #{orderStatus}, " +
	        "	</if>" +
		    "	<if test='productId != null'>" + 
		    "		product_id = #{productId}, " +
	        "	</if>" +
		    "	<if test='orderType != null'>" + 
		    "		order_type = #{orderType}, " +
	        "	</if>" +
		    "	<if test='orderToken != null'>" + 
		    "		order_token = #{orderToken}, " +
	        "	</if>" +
		    "	<if test='channelTradeId != null'>" + 
		    "		channel_trade_id = #{channelTradeId}, " +
	        "	</if>" +
    		"	update_time = now() "+
	        "</set>" +
    		"where order_no = #{orderNo} and app_platform = #{appPlatform} " +
			"</script>")
    public int updateInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO);
    
    /**
     * 查询所有投资记录
     */
    @Select(" SELECT * FROM ${investmentRecordsTable} ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "orderNo", column = "order_no"),
        @Result(property = "title", column = "title"),
        @Result(property = "totalPrice", column = "total_price"),
        @Result(property = "orderStatus", column = "order_status"),
        @Result(property = "productId", column = "product_id"),
        @Result(property = "orderType", column = "order_type"),
        @Result(property = "orderToken", column = "order_token"),
        @Result(property = "channelTradeId", column = "channel_trade_id"),
        @Result(property = "investmentTime", column = "investment_time")
    })
    public List<InvestmentRecordsDTO> queryAllInvestmentRecords(@Param(value = "investmentRecordsTable") String investmentRecordsTable);
    
    /**
     * 通过会员ID查询总投资金额
     */
    @Select(" SELECT Sum(totalPrice) as totalPrice FROM ${investmentRecordsTable} WHERE member_id = #{memberId} ")
    public BigDecimal queryTotalInvestmentAmountByMemberId(@Param(value = "investmentRecordsTable") String investmentRecordsTable, @Param(value = "memberId") String memberId);
    
    /**
     * 查询投资记录
     */
    @Select("<script>" +
    		"SELECT * FROM ${investmentRecordsTable} " +
		    "<where> " +
		    "	<if test='id != null'>" + 
	        "		and id = #{id} "+
	        "	</if>" +
			"	<if test='appPlatform != null'>" + 
			"		app_platform = #{appPlatform}, " +
			"	</if>" +
	        "	<if test='memberId != null'>" + 
	        "		and member_id = #{memberId} "+
	        "	</if>" +
	        "	<if test='orderNo != null'>" + 
	        "		and order_no = #{orderNo} "+
	        "	</if>" +
	        "	<if test='title != null'>" + 
	        "		and title = #{title} "+
	        "	</if>" +
	        "	<if test='totalPrice != null'>" + 
	        "		and total_price = #{totalPrice} "+
	        "	</if>" +
	        "	<if test='orderStatus != null'>" + 
	        "		and order_status = #{orderStatus} "+
	        "	</if>" +
	        "	<if test='productId != null'>" + 
	        "		and product_id = #{productId} "+
	        "	</if>" +
	        "	<if test='orderType != null'>" + 
	        "		and order_type = #{orderType} "+
	        "	</if>" +
	        "	<if test='orderToken != null'>" + 
	        "		and order_token = #{orderToken} "+
	        "	</if>" +
	        "	<if test='channelTradeId != null'>" + 
	        "		and channel_trade_id = #{channelTradeId} "+
	        "	</if>" +
	        "</where>" +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "orderNo", column = "order_no"),
        @Result(property = "title", column = "title"),
        @Result(property = "totalPrice", column = "total_price"),
        @Result(property = "orderStatus", column = "order_status"),
        @Result(property = "productId", column = "product_id"),
        @Result(property = "orderType", column = "order_type"),
        @Result(property = "orderToken", column = "order_token"),
        @Result(property = "channelTradeId", column = "channel_trade_id"),
        @Result(property = "investmentTime", column = "investment_time")
    })
    public List<InvestmentRecordsDTO> queryInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO);
    
    /**
     * 通过订单号查询投资记录
     */
    @Select("<script>" +
    		"SELECT * FROM ${investmentRecordsTable} " +
		    "where order_no = #{orderNo}" +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "appPlatform", column = "app_platform"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "orderNo", column = "order_no"),
        @Result(property = "title", column = "title"),
        @Result(property = "totalPrice", column = "total_price"),
        @Result(property = "orderStatus", column = "order_status"),
        @Result(property = "productId", column = "product_id"),
        @Result(property = "orderType", column = "order_type"),
        @Result(property = "orderToken", column = "order_token"),
        @Result(property = "channelTradeId", column = "channel_trade_id"),
        @Result(property = "investmentTime", column = "investment_time")
    })
    public InvestmentRecordsDTO queryInvestmentRecordsByOrderNo(@Param(value = "investmentRecordsTable") String investmentRecordsTable, @Param(value = "orderNo") String orderNo);
    
    /**
     * 通过订单号删除投资记录
     */
    @Delete("<script>" +
    		"DELETE FROM ${investmentRecordsTable} " +
		    "where order_no = #{orderNo} " +
		    "</script>")
    public int InvestmentRecordsByOrderNo(@Param(value = "investmentRecordsTable") String investmentRecordsTable, @Param(value = "orderNo") String orderNo);

}