package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FinancialNonBanking;

/**
 *
 * @author rizkykojek
 */
public interface FinancialNonBankingDao extends IDAO<FinancialNonBanking> {

	public List<FinancialNonBanking> getAllDataByFinancialService(String financialService);
	
}
