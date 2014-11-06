package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FinancialNonBanking;

/**
 *
 * @author rizkykojek
 */
public interface FinancialNonBankingService extends IService<FinancialNonBankingService> {

	public List<FinancialNonBanking> getAllDataByFinancialService(String financialService) throws Exception;
	
}
