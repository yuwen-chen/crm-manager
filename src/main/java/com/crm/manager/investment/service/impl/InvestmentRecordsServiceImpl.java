package com.crm.manager.investment.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.investment.dao.IInvestmentRecordsDao;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.crm.manager.investment.service.IInvestmentRecordsService;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.service.IMemberCodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InvestmentRecordsServiceImpl implements IInvestmentRecordsService{
	
	@Autowired
	private IInvestmentRecordsDao investmentRecordsDao;
	
	@Autowired
	private IMemberCodeService memberCodeService;

	@Override
	@Transactional
	public boolean addInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO) {
		if(investmentRecordsDTO == null || StringUtils.isBlank(investmentRecordsDTO.getMemberId())
				|| StringUtils.isBlank(investmentRecordsDTO.getAppPlatform())){
			return false;
		}
		
		investmentRecordsDTO.setInvestmentRecordsTable(investmentRecordsDTO.getAppPlatform());
		//更新编码表是投资理财
		if(investmentRecordsDao.insertInvestmentRecords(investmentRecordsDTO)> 0) {
			MemberCodeDTO memberCodeDTO = new MemberCodeDTO();
			memberCodeDTO.setMemberId(investmentRecordsDTO.getMemberId());
			memberCodeDTO.setMemberCodeTable(investmentRecordsDTO.getAppPlatform());
			MemberCodeDTO memberCode = memberCodeService.queryMemberCodeByMemberId(memberCodeDTO);
			int investmentAmount = 0;
			if(memberCode != null){
				String amount = memberCode.getInvestmentAmount();
				investmentAmount = Integer.parseInt(amount != null ? amount : "0");
			}
			memberCode.setIsInvestment(1);
			memberCode.setMemberCodeTable(memberCode.getAppPlatform());
			BigDecimal totalAmount = investmentRecordsDTO.getTotalPrice();
			if (totalAmount != null) {
				//投资金额(1-9999元起算1w=0001,逢1w进1)
				investmentAmount = investmentAmount + getInvestmentAmount(totalAmount);
				//格式化"0000"
				memberCode.setInvestmentAmount(String.format("%04d", investmentAmount));
			}
			boolean b = memberCodeService.updateMemberCode(memberCode);
			return  b;
		} else {
			System.err.println("订单号重复");
			return false;
		}
	}

	@Override
	public PageInfo<InvestmentRecordsDTO> queryInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO) {
		if(investmentRecordsDTO == null){
			return null;
		}
		PageHelper.startPage(investmentRecordsDTO.getPageNumber(), investmentRecordsDTO.getPageSize());  
		return new PageInfo<InvestmentRecordsDTO>(investmentRecordsDao.queryInvestmentRecords(investmentRecordsDTO));
	}
	
	
	/**
	 * 根据投资金额计算出多少W
	 * @param totalAmount
	 * 投资金额(1-9999元起算1w=0001,逢1w进1)
	 * @return
	 */
	private int getInvestmentAmount(BigDecimal totalAmount){
		BigDecimal minAmount = BigDecimal.valueOf(1); 
		BigDecimal maxAmount = BigDecimal.valueOf(9999); 
		BigDecimal multipleNum = BigDecimal.valueOf(10000); 
		if(totalAmount.compareTo(minAmount) >= 0 && totalAmount.compareTo(maxAmount) <= 0){
			return 1;
		} else if(totalAmount.compareTo(maxAmount) >0 ){
			int multiple =totalAmount.divide(multipleNum).intValue();
			BigDecimal remainder =totalAmount.subtract(multipleNum.multiply(new BigDecimal(multiple)));
			if (multiple == 0){
				return 1;
			}
			if((remainder.compareTo(minAmount) >= 0 && remainder.compareTo(maxAmount) <= 0)){
				return multiple + 1;
			} else {
				return multiple;
			}
		}
		return 0;
	}

}
