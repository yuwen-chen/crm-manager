package com.crm.manager.investment.dto;

import java.math.BigDecimal;

import javax.persistence.Transient;

import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.common.enums.AppTableEnum;

public class InvestmentRecordsDTO  extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437193052447152335L;
	
	/**
	 * ID
	 */
    private Long id;	
    
    
    /**
	 * app平台(A:融侨宝, B:金管家, C:融侨普惠)
	 */
	private String appPlatform;
    
    /**
     * 会员ID
     */
	private String memberId;
	
	/**
     * 订单编号
     */
	private String orderNo;
	
	/**
     * 订单标题-存储标题类或者短内容
     */
    private String title;
    
    /**
     * 总金额
     */
    private BigDecimal totalPrice;
    
    /**
     * 订单状态
     */
    private String orderStatus;
    
    /**
     * 产品ID
     */
    private String productId;
    
    /**
     * 订单类型(batch_apply-批量申购;apply-单笔申购;withdraw_realtime-实时提现)
     */
    private String orderType;
    
    /**
     * 订单唯一标示token
     */
    private String orderToken;
    
    /**
     * 第三方唯一交易流水号
     */
    private String channelTradeId;
    
    /**
     * 投资时间
     */
    private String investmentTime;
    
    /**
     * 投资记录对应表
     */
    @Transient 
    private String investmentRecordsTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAppPlatform() {
		return appPlatform;
	}

	public void setAppPlatform(String appPlatform) {
		this.appPlatform = appPlatform;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public String getChannelTradeId() {
		return channelTradeId;
	}

	public void setChannelTradeId(String channelTradeId) {
		this.channelTradeId = channelTradeId;
	}

	public String getInvestmentTime() {
		return investmentTime;
	}

	public void setInvestmentTime(String investmentTime) {
		this.investmentTime = investmentTime;
	}

	public String getInvestmentRecordsTable() {
		return investmentRecordsTable;
	}

	public void setInvestmentRecordsTable(String investmentRecordsTable) {
		this.investmentRecordsTable = AppTableEnum.getInvestmentRecordsTableByType(investmentRecordsTable);
	}
	
}
