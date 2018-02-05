package com.test.investment;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.crm.manager.investment.service.IInvestmentRecordsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InvestmentRecordsServiceTest {
	
	@Autowired
	private IInvestmentRecordsService investmentRecordsService;
	
	
	@Test
	public void insertTest(){
		InvestmentRecordsDTO investmentRecordsDTO = new InvestmentRecordsDTO();
		investmentRecordsDTO.setMemberId("UID0006");
		investmentRecordsDTO.setOrderNo("OR201707030000027918");
		investmentRecordsDTO.setTitle("余额申购(定期理财)");
		investmentRecordsDTO.setTotalPrice(new BigDecimal("9999"));
		investmentRecordsDTO.setOrderStatus("apply_ma_to_rf_confirm");
		investmentRecordsDTO.setProductId("602000020160330001");
		investmentRecordsDTO.setOrderType("apply_ma_to_rf");
		investmentRecordsDTO.setOrderToken("1e7-b687-8cdcd4b4c61c");
		investmentRecordsDTO.setChannelTradeId("01201707040000001591");
		investmentRecordsDTO.setAppPlatform("A");
		investmentRecordsService.addInvestmentRecords(investmentRecordsDTO);
	}

}
